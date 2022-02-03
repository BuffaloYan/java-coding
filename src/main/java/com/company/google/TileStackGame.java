package com.company.google;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TileStackGame {
    public static class Stack implements Cloneable {
        int height;
        int color;

        public Stack clone()  {
            try {
                return (Stack) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String toString() {
            return height + "," + color;
        }

    }

    public static class Node {
        Node parent;
        List<Node> children;
        String key; // string representation of stacks
        List<Stack> stacks;
        int depth;
        boolean onLosingPath;
        boolean onWiningPath;

        public Node(List<Stack> stacks, Node parent, int depth) {
            this.stacks = stacks;
            this.parent = parent;
            this.depth = depth;

            this.children = new ArrayList<>();
        }

        public String toString() {
            if (key == null) {
                key = stackToString(stacks);
            }

            return depth + ":" + key;
        }
    }

    public static String stackToString(List<Stack> stacks) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<stacks.size(); i++) {
            list.add(stacks.get(i).toString());
        }

        return String.join(";", list);
    }

    /**
     * [ [height, color], [height, color], ...]
     * @return
     */
    public int winner(int[][] stacks) {
        List<Stack> list = new ArrayList<>();
        for(int i=0; i<stacks.length; i++) {
            Stack stack = new Stack();
            stack.height = stacks[i][0];
            stack.color = stacks[i][1];

            list.add(stack);
        }

        Node root = new Node(list, null, 0);
        root.toString();

        buildTree(root, new HashMap<>());

        List<Node> winningPath = new ArrayList<>();
        boolean find = findWinningPath(root, winningPath);
        if (winningPath.size()>0) {
            Node node = winningPath.get(0);
            while(node != null) {
                System.out.println(node.key);
                node = node.parent;
            }
        }

        return find? 0 : 1;

    }

    private boolean findWinningPath(Node root, List<Node> path) {
        if (root.children.size() == 0 ) {
            if (root.onWiningPath) {
                path.add(root);
            }
            return root.onWiningPath;
        }

        boolean winning = false;

        if (root.depth%2 == 1) {
            // player 2's move, checking for losing state
            for (Node node : root.children) {
                if (node.onLosingPath && node.children.size() == 0) {
                    // fail fast
                    return false;
                }
            }
        }

        for(Node node: root.children) {
            if (node.onWiningPath ) {
                // must be on winning path for all

                winning = findWinningPath(node, path);
                if (winning) return true;

            }

        }

        return false;
    }

    private void trimLosingPath(Node root, List<Node> losingStates) {
        if (losingStates.size()==0) {
            return;
        }

        losingStates = losingStates.stream().map(node -> {
            while(node.parent != root) {
                node = node.parent;
            }

            return node;
        }).collect(Collectors.toList());

        root.children.removeAll(losingStates);
    }

    /**
     * find all leaf node ends at even level, at which, player 2 win (no next move for player 1)
     * @param root
     * @param losingStates
     */
    public void findLoseState(Node root, List<Node> losingStates) {
        if (root.children.size() == 0) {
            if (root.depth%2 == 0) {
                losingStates.add(root);
            }
            return;
        }

        for(Node child: root.children) {
            findLoseState(child, losingStates);
        }
    }

    public void buildTree(Node root, Map<String, Node> maps) {
        List<Stack> stacks = root.stacks;

        // hash set to avoid duplicate move
        //Set<String> hashSet = new HashSet<>();

        for(int i=0; i<stacks.size()-1; i++) {
            for(int j=i+1; j<stacks.size(); j++) {
                // check valid move
                if(stacks.get(i).height == stacks.get(j).height || stacks.get(i).color == stacks.get(j).color) {
                    // same height or same color
                    List<Stack> newStacks = cloneStacks(stacks);
                    // add i on-top of j, keep i's color
                    newStacks.get(i).height += stacks.get(j).height;
                    newStacks.remove(j);

                    String key = stackToString(newStacks);
                    if (maps.containsKey(key)) {
                        // we have been there
                        Node n = maps.get(key);
                        if (n.onLosingPath) {
                            markPath(root, 1);
                        }

                        if (n.onWiningPath) {
                            markPath(root, 0);
                        }

                        root.children.add(n);
                        continue;
                    }

                    Node newNode = new Node(newStacks, root, root.depth+1);
                    newNode.key = key;
                    maps.put(key, newNode);
                    root.children.add(newNode);

                    // depth first
                    buildTree(newNode, maps);


                }
            }
        }

        if (root.children.size() == 0) {
            // ok this is a final state
            if (root.depth%2==0) {
                // player 2 wins!
                markPath(root, 1);
            } else {
                markPath(root, 0);
            }
        }
    }

    private void markPath(Node node, int winner) {
        while(node != null) {
            if (winner == 0) {
                node.onWiningPath = true;
            } else {
                node.onLosingPath = true;
            }
            node = node.parent;
        }
    }

    /**
     * deep clone list of Stacks
     * @param stacks
     * @return
     */
    public List<Stack> cloneStacks(List<Stack> stacks) {
        List<Stack> result = new ArrayList<>();
        for(Stack s: stacks) {
            result.add(s.clone());
        }

        return result;
    }

    @Test
    public void testRun1() {
        // 2 color, 4 tiles
        int[][] stacks = {{1,0},{1,0},{1,1},{1,1}};

        TileStackGame tileStackGame = new TileStackGame();
        int winner = tileStackGame.winner(stacks);

        Assert.assertEquals(0, winner);
    }

    @Test
    public void testRun2() {
        // 2 color, 4 tiles
        int[][] stacks = {
                {1,0},{1,0},{1,0},{1,0},
                {1,1},{1,1},{1,1},{1,1},
                {1,2},{1,2},{1,2},{1,2},
                {1,3},{1,3},{1,3},{1,3}
        };

        TileStackGame tileStackGame = new TileStackGame();
        int winner = tileStackGame.winner(stacks);

        Assert.assertEquals(0, winner);
    }

    @Test
    public void testRun3() {
        // 2 color, 4 tiles
        int[][] stacks = {
                {1,0},{1,0},{1,0},
                {1,1},{1,1},{1,1},
                {1,2},{1,2},{1,2},
                {1,3},{1,3},{1,3}
        };

        TileStackGame tileStackGame = new TileStackGame();
        int winner = tileStackGame.winner(stacks);

        Assert.assertEquals(0, winner);
    }
}
