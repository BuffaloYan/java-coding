package com.company.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EvenDigits {
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int n: nums) {
            int digitCount = countBinaryDigits(n);
            if (digitCount%2 == 0) count++;
        }

        return count;
    }

    private int countBinaryDigits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n>>=1;
        }

        return count;
    }

    @Test
    public void testRun1() {
        int[] nums = new int[]{12,345,2,6,7896};

        EvenDigits evenDigits = new EvenDigits();
        int result = evenDigits.findNumbers(nums);

        assertEquals(2, result);
    }

    @Test
    public void testBinaryDigits() {
        int[] nums = new int[]{12,345,2,6,7896};
        for (int n: nums) {
            int d = this.countBinaryDigits(n);
            System.out.printf("%d = %s, %d\n", n, Integer.toBinaryString(n), d);
        }
    }
}
