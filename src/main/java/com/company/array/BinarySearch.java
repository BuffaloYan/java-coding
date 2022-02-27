package com.company.array;

import java.util.List;

public class BinarySearch {

    public int insert(List<Integer> arr, int value) {
        int left = 0;
        int right = arr.size()-1;

        if (arr.size() == 0 || value >= arr.get(right)) {
            arr.add(value);
            return arr.size()-1;
        }

        if (value < arr.get(0)) {
            arr.add(0, value);
            return 0;
        }

        while (left < right) {
            int mid = left + (right-left)/2;

            if (value <= arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        arr.add(left, value);
        return left;
    }

    
}
