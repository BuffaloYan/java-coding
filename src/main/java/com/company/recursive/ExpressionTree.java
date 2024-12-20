package com.company.recursive;


import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

class Node {
     char val;
     Node left;
     Node right;
     Node() {this.val = ' ';}
     Node(char val) { this.val = val; }
     Node(char val, Node left, Node right) {
         this.val = val;
         this.left = left;
            this.right = right;
    }

    public String toString() {
         return dfs(this);
    }

    private String dfs(Node node) {
         if (node == null) return "null";

         return  node.val + ", " + dfs(node.left) + ", " + dfs(node.right);
    }
}

public class ExpressionTree {
    public Node expTree(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) deque.offerLast(c);
        return helper(deque);
    }

    private Node helper(Deque<Character> deque) {
        Deque<Node> operands = new LinkedList<>();
        Deque<Node> operators = new LinkedList<>();
        while (!deque.isEmpty()) {
            char c = deque.pollFirst();
            if (Character.isDigit(c)) {
                // directly push into operands
                operands.offerLast(new Node(c));
            } else if (c == '(') {
                // recursively push into operands
                operands.offerLast(helper(deque));
            } else if (c == ')') {
                // clear all operands and operators
                eliminate(operands, operators);
                return operands.pollLast();
            } else {
                // c is operator, + - * /
                // only case cannot use eliminate: c is * or /, previous operator is + or -.
                if (!operators.isEmpty() && !(((operators.peekLast().val == '+' || operators.peekLast().val == '-') && (c == '*' || c == '/')))) {
                    eliminate(operands, operators);
                }
                operators.offerLast(new Node(c));
            }
        }
        eliminate(operands, operators);
        return operands.pollLast();
    }

    private void eliminate(Deque<Node> operands, Deque<Node> operators) {
        while (operands.size() != 1) {
            Node right = operands.pollLast();
            Node left = operands.pollLast();
            Node operator = operators.pollLast();
            operator.left = left;
            operator.right = right;
            operands.offerLast(operator);
        }
    }

    @Test
    public void testExpressionNoParenthesis() {
        ExpressionTree expressionTree = new ExpressionTree();
        System.out.println(expressionTree.expTree("3*4-2*5"));
    }

    @Test
    public void testExpressionNoParenthesis2() {
        ExpressionTree expressionTree = new ExpressionTree();
        System.out.println(expressionTree.expTree("2-3/5*2+1"));

        TreeMap<Integer, Integer> map = new TreeMap<>();

        map.floorEntry(1);
    }
}