package com.company.matrix;

import org.junit.Assert;
import org.junit.Test;

public class RottenOrange {
    public int orangesRotting(int[][] grid) {

        // initial count
        int[] gridCount= countGrid(grid);

        if (gridCount[1] == 0) {
            // fresh count, no fresh orange
            return 0;
        }

        if (gridCount[0] == 0 ) {
            // rotten count, nothing rotten
            return -1;
        }



        int previous_freshCount = -1;
        int freshCount = gridCount[1];
        int growCount = 0;
        while (true) {
            GrowResult result = growGrid(grid);

            if (result.rottenCount == 0) {
                break;
            }

            growCount++;
            grid = result.grid;
        };

        gridCount= countGrid(grid);

        if (gridCount[1]>0) {
            return -1;
        } else {
            return growCount;
        }
    }

    private int[] countGrid(int[][] grid) {
        int rottenCount = 0;
        int freshCount = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rottenCount++;
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        return new int[] {rottenCount, freshCount};
    }

    public static class GrowResult {
        int[][] grid;
        int rottenCount;
    }

    // grow rotten accross grid
    private GrowResult growGrid(int[][] grid) {
        int[][] newGrid = clone2DArray(grid);
        int rottenCount = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    // only grow in newGrid
                    rottenCount += growRotten(newGrid, i, j);
                }
            }
        }

        GrowResult result = new GrowResult();
        result.grid = newGrid;
        result.rottenCount = rottenCount;

        return result;
    }

    private int[][] clone2DArray(int[][] origin) {
        int[][] result = new int[origin.length][origin[0].length];
        for (int i=0; i<origin.length; i++) {
            result[i] = origin[i].clone();
        }

        return result;
    }

    private int growRotten(int[][] grid, int i, int j) {
        grid[i][j] = 3;

        int rottenCount = markRotten(grid, i+1, j);
        rottenCount += markRotten(grid, i-1, j);
        rottenCount += markRotten(grid, i, j-1);
        rottenCount += markRotten(grid, i, j+1);

        return rottenCount;
    }

    private int markRotten(int[][] grid, int i, int j) {
        if (i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 2;
            return 1;
        }

        return 0;
    }

    @Test
    public void testRun1() {
        RottenOrange rottenOrange = new RottenOrange();
        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};

        int result = rottenOrange.orangesRotting(grid);
        System.out.println("result = " +result);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testRun2() {
        RottenOrange rottenOrange = new RottenOrange();
        int[][] grid = new int[][] {{2,1,1},{1,1,0},{0,1,1}};
        int result = rottenOrange.orangesRotting(grid);
        System.out.println("result = " +result);
        Assert.assertEquals(4, result);
    }
}
