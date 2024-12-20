package com.company.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearch {

    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "3, 1",
            "10, 4",
            "11, 5",
    })
    public void testInsert(int value, int expectedIndex) {
        List<Integer> arr = Arrays.stream(new Integer[]{2, 4, 6, 8, 10})
                .collect(Collectors.toList());
        BinarySearch binarySearch = new BinarySearch();

        int insertedIdx = binarySearch.insert(arr, value);
        assert arr.get(insertedIdx) == value;
        assert insertedIdx == expectedIndex;
    }

    @ParameterizedTest
    @CsvSource({
            "1, -1",
            "3, -1",
            "10, 4",
            "11, -1",
    })
    public void testSearch(int value, int expectedIndex) {
        List<Integer> arr = Arrays.stream(new Integer[]{2, 4, 6, 8, 10})
                .collect(Collectors.toList());
        BinarySearch binarySearch = new BinarySearch();

        int foundIdx = binarySearch.search(arr, value);
        assert foundIdx == expectedIndex;
    }

    public int insert(List<Integer> arr, Integer value) {
        int left = 0;
        int right = arr.size();

        while (left < right) {
            int mid = (right+left)/2;

            if (value == arr.get(mid)) {
                return mid;
            } else if (value < arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        arr.add(left, value);
        return left;
    }

    public int search(List<Integer> arr, Integer value) {
        int left = 0;
        int right = arr.size()-1;

        while (left <= right) {
            int mid = (right+left)/2;

            if (value == arr.get(mid)) {
                return mid;
            } else if (value < arr.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    
}
