package com.company.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

public class ClimbStairCost {
    public int minCostClimbingStairs(int[] cost) {
        int f_2=0, f_1=0;

        if (cost.length>=1) {
            f_2 = cost[cost.length-1];
            if (cost.length==1)
                return f_2;
        }

        if (cost.length>=2) {
            f_1 = Math.min(cost[cost.length-1], cost[cost.length-2]);

            if (cost.length==2)
                return f_1;
        }

        int f_0 = 0;
        for (int i=cost.length-3; i>=0; i--) {
            f_0 = cost[i] + Math.min(f_1, f_2);
            f_2 = f_1;
            f_1 = f_0;
        }

        f_0 = Math.min(f_1, f_2);

        return f_0;
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
