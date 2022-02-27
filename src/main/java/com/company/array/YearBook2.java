package com.company.array;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YearBook2 {

    public static class Student {
        int index;
        int signs;
        Student next;
        public Student(int i) {
            index = i;
        }

    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        // a[index] = student_number (representing year book)

        // siganitures
        int n = arr.length;
        for (int i=0; i<n; i++) {
            // reduce to 0 started indexing
            arr[i]--;
        }

        Student[] s = new Student[arr.length];
        for (int i=0; i<arr.length; i++) {
            s[i] = new Student(i);
        }

        for (int i=0; i<arr.length; i++) {
            // connect the loop(s)
            s[i].next = s[arr[i]];
        }

        // number of signatures = size of loop
        for (int i=0; i<n; i++) {
            Student start = s[i];

            if (start.signs > 0) {
                continue;
            }

            int count = 1;
            Student next = start.next;
            while (next != start) {
                count++;
                next = next.next;
            }

            start = s[i];
            start.signs = count;
            next = start.next;
            while (next != start) {
                next.signs = count;
                next = next.next;
            }
        }

        // collection result
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            result[i] = s[i].signs;
        }

        return result;
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
        int[] arr_1 = {2, 1};
        int[] expected_1 = {2, 2};
        int[] output_1 = findSignatureCounts(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {1, 3, 2, 5, 6, 4};
        int[] expected_2 = {1, 2, 2, 3, 3, 3};
        int[] output_2 = findSignatureCounts(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) throws IOException {
        new YearBook2().run();
    }
}
