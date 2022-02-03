package com.company.matrix;

import org.junit.Test;

public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] directions = {{0,1}, {1,0}, {0, -1}, {-1, 0}};

        int i = 0;
        int j = 0;
        int count = 1;
        int[][] grid = new int[n][n];
        grid[0][0] = count;

        int dir = 0;
        int maxCount = n*n;
        do {
            int i1 = i + directions[dir][0];
            int j1 = j + directions[dir][1];
            if (isValid(n, i1, j1) && grid[i1][j1] == 0) {
                grid[i1][j1] = ++count;

                // will keep moving on same direction
                i = i1;
                j = j1;
            } else {
                // move to next direction
                dir = (dir+1) % 4;
            }
        } while (count < maxCount);


        return grid;
    }

    private boolean isValid(int d, int i, int j) {
        return i>=0 && i<d && j>=0 && j<d;
    }

    @Test
    public void testRun() {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] grid = spiralMatrix.generateMatrix(3);

        System.out.println(grid);
    }

    @Test
    public void testRun2() {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] grid = spiralMatrix.generateMatrix(4);

        System.out.println(grid);
    }
}
