package com.company.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

public class RotatedArray {
    public static int binarySearch(int[] data, int value) {
        return binarySearch(data, value, 0, data.length-1);
    }

    private static int binarySearch(int[] data, int value, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right)/2;

        int direction = checkValue(data, value, left, right, middle);
        if (direction == 0) {
            return middle;
        }

        if (left == right) {
            // last element checked
            return -1;
        }

        if (direction > 0) {
            //search right
            return binarySearch(data, value, middle+1, right);
        } else {
            return binarySearch(data, value, left, middle-1);
        }
    }

    private static int checkValue(int[] data, int value, int left, int right, int middle) {
        int searchDirection;
        if (value == data[middle]) {
            searchDirection = 0;
        } else if (value > data[middle]) {
            if (value < data[right] || data[right] < data[middle]) {
                // search right
                searchDirection = 1;
            } else {
                searchDirection = -1;
            }

        } else {
            if (value > data[left] || data[left] > data[middle]) {
                // search left
                searchDirection = -1;
            } else {
                searchDirection = 1;
            }

        }

        return searchDirection;
    }

    @Test
    public void testRunNoRotation() {
        int[] data = {1, 2, 3, 4};
        int index = binarySearch(data, 2);
        assertEquals(1, index);
    }

    @Test
    public void testRunLeftHalf() {
        int[] data = {5, 1, 2, 3, 4};
        int index = binarySearch(data, 1);
        assertEquals(1, index);
    }

    @Test
    public void testRunRightHalf() {
        int[] data = {5, 1, 2, 3, 4};
        int index = binarySearch(data, 3);
        assertEquals(3, index);
    }

    @Test
    public void testRunLessThanMin() {
        int[] data = {5, 1, 2, 3, 4};
        int index = binarySearch(data, 0);
        assertEquals(-1, index);
    }

    @Test
    public void testRunGreaterThanMax() {
        int[] data = {5, 1, 2, 3, 4};
        int index = binarySearch(data, 6);
        BitSet bs;
        assertEquals(-1, index);
    }
}
