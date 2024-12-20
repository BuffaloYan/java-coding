package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Expression {
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


    public static ExpNode parse(String expression, ExpNode parsed) {
        ExpNode p1 = new ExpNode(TokenType.DATA, expression.charAt(0));
        ExpNode op = new ExpNode(TokenType.OPERATOR, expression.charAt(1));

        ExpNode resultNode = op;
        if (parsed != null) {
            if (parsed.type == TokenType.OPERATOR &&
                    operatorPriority.get(parsed.value) <= operatorPriority.get(op.value)) {
                op.p1 = p1;
                parsed.p2 = op;
                resultNode = parsed;
            } else {
                parsed.p2 = p1;
                op.p1 = parsed;
            }
        } else {
            op.p1 = p1;
        }

        ExpNode p2;
        if (expression.length() == 3) {
            p2 = new ExpNode(TokenType.DATA, expression.charAt(2));
            op.p2 = p2;
        } else if (expression.length() > 3) {
            resultNode = parse(expression.substring(2), op);
        }

        return resultNode;
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
        ExpNode root = parse(expression, null);
        int result = eval(root);
    }

    @Test
    public void testParseToken2() {
        String expression = "0&1|0^1";
        ExpNode root = parse(expression, null);
        int result = eval(root);
    }
}
