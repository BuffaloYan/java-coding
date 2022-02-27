package com.company.dynamicprogramming;

import java.util.Arrays;

public class CleanAndEarn {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];

        return deleteAndEarn(nums, 0, dp);

    }

    private int deleteAndEarn(int[] nums, int index, int[] dp) {
        if (index >= nums.length) return 0;

        if (dp[index] > 0) {
            return dp[index];
        }

        int count = 1;
        int i = index + 1;
        while (i<nums.length && nums[i] == nums[index]) {
            count ++;
            i++;
        }

        int next = i;
        while (i < nums.length) {
            // skip +1 value
            if (nums[i] != nums[index]+1) {
                break;
            }

            i++;
        }

        int maxPoints = Math.max(
                nums[index] * count + deleteAndEarn(nums, i, dp),
                deleteAndEarn(nums, next, dp)
        );

        dp[index] = maxPoints;

        return maxPoints;
    }
}
