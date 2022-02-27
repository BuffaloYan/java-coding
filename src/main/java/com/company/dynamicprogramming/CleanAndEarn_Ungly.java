package com.company.dynamicprogramming;

import java.util.Arrays;

public class CleanAndEarn_Ungly {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);

        int[][] dp = new int[nums.length][nums.length];

        return deleteAndEarn(nums, 0, nums.length-1, dp);

    }

    private int deleteAndEarn(int[] num, int start, int end, int[][] dp) {
        if (start > end) return 0;

        if (dp[start][end] > 0) {
            return dp[start][end];
        };

        int i = start;
        int maxPoints = 0;
        while (i<=end) {

            // collect points at i, with duplicates
            int points = num[i];
            int iStart = i;
            while (i+1<=end && num[i+1] == num[i]) {
                // add up same number
                points += num[i];
                i++;
            }

            int j = iStart-1;
            // remove left
            while (j>=0 && num[j] == num[iStart]-1) {
                j--;
            }

            if (j>=start) {
                // sum left
                points += deleteAndEarn(num, start, j, dp);
            }

            j = i+1;
            // remove right
            while (j<=end && num[j] == num[i]+1) {
                j++;
            }


            if (j<=end) {
                // sum right
                points += deleteAndEarn(num, j, end, dp);
            }

            maxPoints = Math.max(maxPoints, points);
            i++;
        }

        dp[start][end] = maxPoints;

        return maxPoints;
    }
}
