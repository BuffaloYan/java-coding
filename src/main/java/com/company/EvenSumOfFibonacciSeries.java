package com.company;

public class EvenSumOfFibonacciSeries {
    public static void main(String[] args) {
        int max = 4000000;
        int n1 = 1, n2 = 2, n;
        int sumOfEven = 2;
        while ( (n = n1+n2) < max ) {
            if (n%2 == 0) sumOfEven += n;
            n1 = n2;
            n2 = n;
        }

        System.out.println("sumOfEven = " + sumOfEven);
    }
}
