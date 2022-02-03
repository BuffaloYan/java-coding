package com.company.array;

import org.junit.Test;

public class SortColor {
    public void sortColors(int[] nums) {
        int indexRed0 = 0, indexBlue2 = nums.length-1;

        while (indexRed0 < nums.length && nums[indexRed0] == 0) indexRed0++;
        if (indexRed0 == nums.length) indexRed0 = 0;

        while (indexBlue2 >= 0 && nums[indexBlue2] == 2) indexBlue2--;
        if (indexBlue2 == -1) indexBlue2 = nums.length-1;

        int index = indexRed0;
        while (index <= indexBlue2) {
            if (nums[indexRed0] == 2 && nums[indexBlue2] == 0) {
                exchange(nums, indexRed0++, indexBlue2--);
                index = indexRed0;
                continue;
            }

            if (nums[index] == 0) {
                exchange(nums, indexRed0++, index);

            } else if (nums[index] == 2) {
                exchange(nums, indexBlue2--, index);
            }

            if (nums[index] == 1) {
                index++;
                continue;
            }

            if (index < indexRed0) {
                index = indexRed0;
            }

        }



    }

    public void sortColors2(int[] nums) {
        // move 0 to front
        int left = 0, right = nums.length-1;
        while(left < right) {
            if(nums[left] == 0) {
                left++;
                continue;
            }

            if(nums[right] != 0) {
                right--;
                continue;
            }

            exchange(nums, left, right);
        }

        // move 2 to back, continue on left, reset right
        right = nums.length-1;
        while(left < right) {
            if(nums[left] != 2) {
                left++;
                continue;
            }

            if(nums[right] == 2) {
                right--;
                continue;
            }

            exchange(nums, left, right);
        }

    }
    private void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void testRun1() {
        int[] date = new int[]{};

    }
}
