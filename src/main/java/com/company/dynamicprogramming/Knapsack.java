package com.company.dynamicprogramming;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class Knapsack {
    public static class SackTracking {
        int[] values;
        int[] weights;
        int sumValue;
        int sumWeight;
        int maxValue;
        int maxWeight;
        Deque<Integer> maxList = new LinkedList<>();
        Deque<Integer> list = new LinkedList<>();
    }

    public SackTracking findMaxValue(int[] values, int[] weights, int maxWeight) {
        SackTracking tracking = new SackTracking();
        tracking.maxWeight = maxWeight;
        tracking.values = values;
        tracking.weights = weights;
        findMaxValue(0, tracking);

        return tracking;
    }


    private void findMaxValue(int start, SackTracking tracking) {
        if (tracking.sumWeight > tracking.maxWeight) {
            // doesn't fit any more
            return;
        }

        if (tracking.sumValue > tracking.maxValue) {
            // found better solution
            tracking.maxValue = tracking.sumValue;
            tracking.maxList.clear();;
            tracking.maxList.addAll( tracking.list );
        }

        if (tracking.sumWeight == tracking.maxWeight) {
            // it's full
            return;
        }

        for (int i=start; i<tracking.weights.length; i++) {
            // try each
            tracking.sumValue += tracking.values[i];
            tracking.sumWeight += tracking.weights[i];
            tracking.list.add(tracking.weights[i]);

            // try rest combination without using ith element (starting from i+1)
            findMaxValue(i+1, tracking);

            // remove element to try next
            tracking.sumValue -= tracking.values[i];
            tracking.sumWeight -= tracking.weights[i];
            tracking.list.removeLast();
        }
    }

    @Test
    public void testKnapSack() {
        int[] values = {2, 3, 5, 6, 8, 10};
        int[] weights = {1, 2, 3, 4, 5, 6};

        Knapsack knapsack = new Knapsack();

        SackTracking tracking = knapsack.findMaxValue(values, weights, 10);

        System.out.println("Max value = " + tracking.maxValue);
        System.out.println( tracking.maxList.stream().map( v -> ", " + v ).reduce("", String::concat) );
    }
}
