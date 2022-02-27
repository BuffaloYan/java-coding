package com.company.array;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class YearBook1 {

    public static class Book {
        int num;
        int signs;
        public Book(int n) {
            num = n;
        }
    }

    public static class Student {
        int index;
        Book holding;
        public Student(int i, Book h) {
            index = i;
            holding = h;
        }
        public void sign() {
            holding.signs++;
        }
    }

    void removeStudents(Iterator<Student> iterator) {
        while (iterator.hasNext()) {
            Student stud = iterator.next();
            if (stud.index+1 == stud.holding.num) {
                // having his own book
                iterator.remove();
            }
        }
    }

    void passOneRound(List<Student> list) {
        Iterator<Student> iterator = list.iterator();

        Student previous = iterator.next();
        previous.sign();
        Book backup = previous.holding;
        Student stud = null;
        while (iterator.hasNext()) {
            stud = iterator.next();
            // sign
            stud.sign();

            // pass book
            previous.holding = stud.holding;
            previous = stud;
        }

        if (stud != null) {
            stud.holding = backup;
        }

        removeStudents(list.iterator());
    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        // a[index] = student_number (representing year book)

        // siganitures
        int n = arr.length;
        Student[] s = new Student[arr.length];
        List<Student> list = new LinkedList<>();

        for (int i=0; i<arr.length; i++) {
            s[i] = new Student(i, new Book(i+1));
            list.add(s[i]);
        }

        while (!list.isEmpty()) {
            passOneRound(list);
        }

        // collection result
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            result[i] = s[i].holding.signs;
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

        int[] arr_2 = {1, 2};
        int[] expected_2 = {1, 1};
        int[] output_2 = findSignatureCounts(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) throws IOException {
        new YearBook1().run();
    }
}
