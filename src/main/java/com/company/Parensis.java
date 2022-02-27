package com.company;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class Parensis {
    public static char OPEN_PAREN = '(';
    public static char CLOSE_PAREN = ')';

    public static void permutate(int pairs) {
        Deque<Character> stack = new LinkedList<>();
        int opened;
        int closed;
        int openAvailable;
        int closedAvailable;

        permutate("", 0,3, 3);
    }

    private static void permutate(String permutation, int opened, int openAvailable, int closedAvailable) {
        if (openAvailable == 0 && closedAvailable == 0) {
            System.out.println(permutation);
            return;
        }

        if (openAvailable > 0) {
            // first try add open paren
            permutate(permutation+OPEN_PAREN, opened+1, openAvailable-1, closedAvailable);
        }

        // then try close paren
        if (opened > 0 && closedAvailable > 0) {
            permutate(permutation+CLOSE_PAREN, opened - 1, openAvailable, closedAvailable-1);
        }
    }

    @Test
    public void testRun() {
        permutate(3);
    }

    @Test
    public void test() {
        java.lang.String s = "abc";
        StringBuilder sb = new StringBuilder();
        sb.append(s);

    }
}
