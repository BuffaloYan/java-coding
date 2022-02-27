package com.company.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

public class ClimbStairCost {
    public int minCostClimbingStairs(int[] cost) {
        int f2=0, f1=0;

        if (cost.length<=2) {
            return 0;
        }

        int fn = 0;
        // f(n) = min(f(n-1) + cost[n-1], f(n-2) + cost[n-2])
        for (int i=2; i<=cost.length; i++) {
            fn = Math.min(f1+cost[i-2], f2+cost[i-1]);
            f1 = f2;
            f2 = fn;
        }

        //fn = Math.min(f1, f2);

        return fn;
    }


    public int minCostClimbingStairs0(int[] cost) {
        int[] dp = new int[cost.length];
        return min(
                climb(0, cost, dp),
                climb(1, cost, dp)
        );
    }

    private int climb(int step, int[] cost, int[] dp) {
        if (step >= cost.length) return 0;
        //if (step >= cost.length-2) return cost[step];

        if (dp[step] == 0) {
            dp[step] = min(
                    cost[step] + climb(step+1, cost, dp),
                    cost[step] + climb(step+2, cost, dp)
            );
        }

        return dp[step];
    }

    private int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++) {
            min = Math.min(min, nums[i]);
        }

        return min;
    }

    @Test
    public void testRun1() {
        int[] costs = {1,100,1,1,1,100,1,1,100,1};

        ClimbStairCost climbStairCost = new ClimbStairCost();
        int minCost = climbStairCost.minCostClimbingStairs(costs);

        Assert.assertEquals(6, minCost);
    }
}
