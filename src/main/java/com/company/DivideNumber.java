package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class DivideNumber {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divide by 0");
        }

        long result = 0;
        long sum = 0;
        int sign = dividend > 0 && divisor > 0
                || dividend < 0 && divisor < 0 ? 1 : -1;

        long dividend1 = Math.abs((long)dividend);
        long divisor1 = Math.abs((long)divisor);

        while (sum <= dividend1) {
            sum += divisor1;
            result++;
        }

        result--;

        if (sign > 0){
            if (result > Integer.MAX_VALUE) {
                result = Integer.MAX_VALUE;
            }
            return (int) result;
        } else {
            if (result > (long)Integer.MAX_VALUE+1) {
                result = Integer.MAX_VALUE+1;
            }
            return (int)-result;
        }
    }

    @Test
    public void testRun1() {
        DivideNumber divideNumber = new DivideNumber();
        int result = divideNumber.divide(7, -3);
        assertEquals(-2, result);
    }

    @Test
    public void testRun2() {
        DivideNumber divideNumber = new DivideNumber();
        int result = divideNumber.divide(-2147483648, -1);
        assertEquals(2147483647, result);

        int a = 10;
        boolean b = (a & 1) == 0;
        Random random = new Random();
    }
}