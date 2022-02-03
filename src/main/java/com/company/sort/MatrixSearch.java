package com.company.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * search a number in a sorted matrix
 * both row and col numbers are in accending order
 */
public class MatrixSearch {
    public static class Location {
        int col, row;

        public Location(int row, int col) {
            this.col = col;
            this.row = row;
        }
    }
    public static Location search(int[][] matrix, int number) {
        int row, col;
        int colCount = matrix[0].length;
        for (row = 0; row<matrix.length; row++) {
            if (matrix[row][0] > number) {
                // first number in the row out of range, not possible further
                break;
            }

            if (matrix[row][colCount-1] >= number) {
                // possible row
                col = searchCol(matrix[row], number, 0, colCount-1);
                if (col>=0) {
                    return new Location(row, col);
                }
            }

        }


        return null;
    }

    private static int searchCol(int[] matrix, int number, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middle = (end+start)/2;
        if (number == matrix[middle]) {
            // found number;
            return middle;
        }

        if (number < matrix[middle]) {
            return searchCol(matrix, number, start, middle-1);
        } else {
            return searchCol(matrix, number, middle+1, end);
        }

    }

    /**
     * this only work when range in rows don't overlap
     * @param matrix
     * @param number
     * @param start
     * @param end
     * @return
     */
    private static int searchRow(int[][] matrix, int number, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middle = (end+start)/2;
        if (number >= matrix[middle][0] && number <= matrix[middle][matrix[middle].length-1]) {
            // number lies in current row;
            return middle;
        }

        if (number < matrix[middle][0]) {
            return searchRow(matrix, number, start, middle-1);
        } else {
            return searchRow(matrix, number, middle+1, end);
        }

    }

    @Test
    public void testRun() {
        int[][] matrix = {
                {15, 20, 40, 85},
                {20, 30, 80, 95},
                {30, 55, 82, 105},
                {40, 80, 100, 120}
        };

        Location loc = search(matrix, 30);
        Assert.assertNotNull(loc);
        System.out.printf("find element at (%d, %d)\n", loc.row, loc.col);
    }
}
