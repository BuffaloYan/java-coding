package com.company.dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CutOfRod {
    /**
     * length of rod equals prices.length
     * @param prices
     * @return
     */
    public static int findMaxValue(int[] prices) {
        int length = prices.length;
        List<Integer> p = Arrays.stream(prices).boxed().collect(Collectors.toList());
        p.add(0, 0);
        int[] values = new int[length+1];
        for (int n=1; n<=length; n++) {
            for (int j=1; j<=n; j++) {
                values[n] = Math.max(values[n], p.get(j) + values[n-j]);
            }
        }

        return values[length];
    }

    /**
     * length of rod equals prices.length
     * @param prices
     * @return
     */
    public static int findMaxValue2(int[] prices, int rodLenth) {
        List<Integer> p = Arrays.stream(prices).boxed().collect(Collectors.toList());
        p.add(0, 0);
        int[] values = new int[rodLenth+1];
        for (int n=1; n<=rodLenth; n++) {
            for (int j=1; j<=n && j<prices.length; j++) {
                values[n] = Math.max(values[n], p.get(j) + values[n-j]);
            }
        }

        return values[rodLenth];
    }

    @Test
    public void testRun() {
        int[] p = {1,   5,   8,   9,  10,  17,  17,  20};

        System.out.println("max value = " + findMaxValue(p));
    }

    @Test
    public void testRun2() {
        int[] p = {3,   5,   8,   9,  10,  17,  17,  20};

        System.out.println("max value = " + findMaxValue2(p, 9));
        LinkedList<String> list;
    }
}
