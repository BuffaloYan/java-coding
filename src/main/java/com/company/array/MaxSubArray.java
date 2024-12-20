package com.company.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MaxSubArray {
    /**
     * Kadane's Algorithm
     * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max_so_far = 0, max_global = nums[0], max_end_index = 0;

        for(int i=0; i<nums.length; i++) {
            max_so_far += nums[i];
            if (max_so_far > max_global) {
                max_global = max_so_far;
                max_end_index = i;
            }

            if (max_so_far < 0) {
                max_so_far = 0;
            }
        }

        int temp = max_global;
        int start_index = max_end_index;
        for(int i=max_end_index; i>=0; i--) {
            temp -= nums[i];
            if (temp == 0) {
                start_index = i;
                break;
            }
        }

        System.out.printf("sum[%d, %d] = %d\n", start_index, max_end_index, max_global);
        for(int i=start_index; i<=max_end_index; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();

        return max_global;
    }

    @Test
    public void testRun1() {
        int[] data = new int[] {-2, -3, 4, -1, -2, 1, 5, -3};
        MaxSubArray maxSubArray = new MaxSubArray();
        int result = maxSubArray.maxSubArray(data);

        assertEquals(7, result);
    }
}
