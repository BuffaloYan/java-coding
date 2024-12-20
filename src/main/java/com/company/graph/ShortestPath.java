package com.company.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ShortestPath {
    public static class Node {
        int index;
        Node previous;
        boolean visited = false;
        int delay = Integer.MAX_VALUE;
        List<Integer> neighbours = new ArrayList<>();

        public Node(int i) {
            index = i;
        }

    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Node[] nodes = new Node[n+1];
        for (int i=0; i<n+1; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0].visited = true;

        int[][] weightMatrix = new int[n+1][n+1];
        for(int[] edge: times) {
            weightMatrix[edge[0]][edge[1]] = edge[2];
            weightMatrix[edge[1]][edge[0]] = edge[2];

            Node node1 = nodes[edge[0]];
            node1.neighbours.add(edge[1]);

            Node node2 = nodes[edge[1]];
            node2.neighbours.add(edge[0]);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> x.delay - y.delay);
        Set<Integer> inQueue = new HashSet<>();
        queue.add(nodes[k]);
        inQueue.add(k);

        nodes[k].delay = 0;
        int max = 0;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            inQueue.remove(node.index);
            node.visited = true;

            for(int i: node.neighbours) {
                Node neighbour = nodes[i];
                boolean visited = neighbour.visited;

                if (!visited) {
                    int newDelay = node.delay + weightMatrix[node.index][neighbour.index];
                    if (newDelay < neighbour.delay) {
                        // update
                        neighbour.previous = node;
                        neighbour.delay = newDelay;
                        //visited = false;
                    }

                    if (inQueue.contains(neighbour.index)) {
                        // need to remove to resort in heap
                        queue.remove(neighbour);
                    }

                    queue.add(neighbour);
                    inQueue.add(neighbour.index);
                }
            }

            max = Math.max(max, node.delay);
        }

        // check if there is node not visited
        for(Node node: nodes) {
            if (node.visited == false) {
                max = -1;
                break;
            }
        }

        return max;
    }

    @Test
    public void testRun1() {
        /**
         * [[1,2,1]]
         * 2
         * 2
         */

        int[][] times = new int[][] {{1,2,1}};
        ShortestPath shortestPath = new ShortestPath();
        int result = shortestPath.networkDelayTime(times, 2, 2);
        assertEquals(-1, result);

        List<Integer> l = new ArrayList<>();
        Integer[] boxed = new Integer[l.size()];
        boxed = l.toArray(boxed);
    }
}
