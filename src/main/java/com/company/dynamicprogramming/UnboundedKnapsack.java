package com.company.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

public class UnboundedKnapsack {
    public static Knapsack.SackTracking findMaxValue(int[] values, int[] weights, int maxWeight) {
        Knapsack.SackTracking tracking = new Knapsack.SackTracking();
        tracking.maxWeight = maxWeight;
        tracking.values = values;
        tracking.weights = weights.clone();
        Arrays.sort( tracking.weights );

        findMaxValue(tracking);
        return tracking;
    }

    private static void findMaxValue(Knapsack.SackTracking tracking) {
        int[] dp = new int[tracking.maxWeight+1];
        int[][] weightCount = new int[tracking.maxWeight+1][tracking.weights.length];
        for (int w=1; w<=tracking.maxWeight; w+=1) {
            // loop through each weight, only one coin will be chosen for weight w
            int selectedCoin = -1;
            weightCount[w] = weightCount[w-1].clone();
            for (int j=0; j<tracking.weights.length; j++) {
                // check if current weight fits in the sack
                if (w-tracking.weights[j] >= 0) {
                    int previousWeight = w-tracking.weights[j];
                    int newValue = dp[previousWeight] + tracking.values[j];
                    if (newValue > dp[w]) {
                        dp[w] = newValue;
                        selectedCoin = j;
                    }

                }

            }

            if (selectedCoin>=0) {
                weightCount[w] = weightCount[w-tracking.weights[selectedCoin]].clone();
                weightCount[w][selectedCoin]++;
            }

        }

        for (int i=0; i<tracking.weights.length; i++) {
            tracking.maxList.add(weightCount[tracking.maxWeight][i]);
        }
        tracking.maxValue = dp[tracking.maxWeight];

    }

    @Test
    public void testRun() {
        int weight = 29;
        int val[] = {5, 18, 30};
        int wt[] = {4, 10, 15};
        int n = val.length;

        Knapsack.SackTracking tracking = findMaxValue(val, wt, weight);
        System.out.println("Max value = " + tracking.maxValue);
        System.out.println( tracking.maxList.stream().map( v -> ", " + v ).reduce("", String::concat) );
    }

    @Test
    public void testRunCutRod1() {
        int weight = 8;
        int val[] = {1,   5,   8,   9,  10,  17,  17,  20};
        int wt[] = {1,   2,   3,   4,   5,   6,   7,   8};
        int n = val.length;

        Knapsack.SackTracking tracking = findMaxValue(val, wt, weight);
        System.out.println("Max value = " + tracking.maxValue);
        System.out.println( tracking.maxList.stream().map( v -> ", " + v ).reduce("", String::concat) );
    }

    @Test
    public void testRunCutRod2() {
        int weight = 8;
        int val[] = {3,   5,   8,   9,  10,  17,  17,  20};
        int wt[] = {1,   2,   3,   4,   5,   6,   7,   8};
        int n = val.length;

        Knapsack.SackTracking tracking = findMaxValue(val, wt, weight);
        System.out.println("Max value = " + tracking.maxValue);
        System.out.println( tracking.maxList.stream().map( v -> ", " + v ).reduce("", String::concat) );
    }
}
