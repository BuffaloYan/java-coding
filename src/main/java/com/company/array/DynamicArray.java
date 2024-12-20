package com.company.array;

import java.util.ArrayList;
import java.util.List;

// https://www.hackerrank.com/challenges/dynamic-array/problem?isFullScreen=true
public class DynamicArray {

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<Integer>();

        //Declare a 2-dimensional array, , of  empty arrays. All arrays are zero indexed.
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        for (int i=0; i<n; i++) {
            arr.add(new ArrayList<Integer>());
        }

        //Declare an integer and initialize it to .
        int lastAnswer = 0;

        for (int i=0; i<queries.size(); i++) {
            List<Integer> row = queries.get(i);
            int q = row.get(0);
            int x = row.get(1);
            int y = row.get(2);

            int idx = (x ^ lastAnswer) % n;
            List<Integer> currentList = arr.get(idx);
            switch (q) {
                case 1:
                    currentList.add(y);
                    break;
                case 2:
                    lastAnswer = currentList.get(y % currentList.size());
                    result.add(lastAnswer);
                    break;
                default:
                    System.err.println("Invalid query " + q + " on row " + i);
                    return result;
            }


        }

        return result;
    }
}
