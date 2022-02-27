package com.company.array;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


public class QueueRemoval {

    // Add any helper functions you may need here


    int[] findPositions(int[] arr, int x) {
        // Write your code here
        List<int[]> list = new LinkedList<>();

        // keep track of index with the number
        for (int i=0; i<arr.length; i++) {
            list.add(new int[]{arr[i], i+1});
        }

        int[] output = new int[x];
        for (int i=0; i<x; i++) {

            // do one cycle
            int count = Math.min(x, list.size());
            int[] max = list.remove(0);
            count--;
            while (count-- > 0) {
                int[] pop = list.remove(0);
                if (pop[0] > max[0]) {
                    push(list, max);
                    max = pop;

                } else {
                    push(list, pop);
                }
            }

            output[i] = max[1];

        }

        return output;

    }


    void push(List<int[]> list, int[] pop) {
        // public back to the queue
        if (pop[0] > 0) {
            pop[0] = pop[0]-1;
        }

        list.add(pop);
    }









    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;
    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for(int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int n_1 = 6;
        int x_1 = 5;
        int[] arr_1 = {1, 2, 2, 3, 4, 5};
        int[] expected_1 = {5, 6, 4, 1, 2 };
        int[] output_1 = findPositions(arr_1, x_1);
        check(expected_1, output_1);

        int n_2 = 13;
        int x_2 = 4;
        int[] arr_2 = {2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4};
        int[] expected_2 = {2, 5, 10, 13};
        int[] output_2 = findPositions(arr_2, x_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new QueueRemoval().run();
    }
}