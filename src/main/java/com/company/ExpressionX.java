package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ExpressionX {
    static Map<Character, Integer> operatorPriority = new HashMap<>();
    static {
        operatorPriority.put('^', 3);
        operatorPriority.put('&', 2);
        operatorPriority.put('|', 1);
    }

    public static class ExpNode {
        TokenType type;
        char value;
        ExpNode p1, p2;

        public ExpNode(TokenType type, char value) {
            this.type = type;
            this.value = value;
        }
    }

    public enum TokenType {OPERATOR, DATA}

    static LinkedList<ExpNode> operatorStack = new LinkedList<>();
    static LinkedList<ExpNode> dataStack = new LinkedList<>();

    public static ExpNode parse(String expression) {
        ExpNode p1 = new ExpNode(TokenType.DATA, expression.charAt(0));
        dataStack.add(p1);

        ExpNode op = null;
        if (expression.length() > 1) {
            op = new ExpNode(TokenType.OPERATOR, expression.charAt(1));
        } else {
            // end of expression, process stacks
            processStack();

            return dataStack.removeLast();
        }

        if (operatorStack.size()>0
                && operatorPriority.get(op.value) < operatorPriority.get(operatorStack.getLast().value)
            ) {
            processStack();
        }

        operatorStack.add(op);

        return parse(expression.substring(2));

    }

    private static void processStack() {
        while (!operatorStack.isEmpty()) {
            ExpNode op = operatorStack.removeLast();
            ExpNode p2 = dataStack.removeLast();
            ExpNode p1 = dataStack.removeLast();
            op.p2 = p2;
            op.p1 = p1;
            dataStack.add(op);
        }
    }

    public int eval(ExpNode root) {
        if (root.type == TokenType.DATA) {
            return root.value - '0';
        }

        int p1 = eval(root.p1);
        int p2 = eval(root.p2);

        switch (root.value) {
            case '^':
                System.out.printf("%d ^ %d = %d\n", p1, p2, p1^p2);
                return p1 ^ p2;
            case '&':
                System.out.printf("%d & %d = %d\n", p1, p2, p1&p2);
                return p1 & p2;
            case '|':
                System.out.printf("%d | %d = %d\n", p1, p2, p1|p2);
                return p1 | p2;
            default:
                throw new IllegalArgumentException("invalid operator: " + root.value);
        }
    }

    @Test
    public void testParseToken1() {
        String expression = "0|1&0^1";
        ExpNode root = parse(expression);
        int result = eval(root);
    }

    @Test
    public void testParseToken2() {
        String expression = "0&1|0^1";
        ExpNode root = parse(expression);
        int result = eval(root);
    }
}
