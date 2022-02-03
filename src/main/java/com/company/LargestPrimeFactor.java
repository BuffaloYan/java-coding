package com.company;

public class LargestPrimeFactor {
    public static void main(String[] args) {
        long num = 600851475143l;

        long factor = maxFactor(num);

        System.out.printf("max prime factor of %d = %d\n", num, factor);
    }

    public static long maxFactor(long n) {
        if (n == 2 || n == 3) {
            return n;
        }

        long result = 1;
        for (long i=2; i<=Math.sqrt(n); i++) {
            long divider = n/i;
            if (n%i == 0) {
                long max = Math.max(maxFactor(i), maxFactor(divider));
                result = Math.max(result, max);
            }
        }

        if (result == 1) {
            // it's a prime!
            result = n;
        }

        return result;
    }
}
