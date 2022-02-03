package com.company.matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RottenOrange2 {
    private static final int[][] dir = {{0,1}, {1, 0}, {0,-1}, {-1,0}};

    public static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

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

        int depth = search(grid);

        gridCount= countGrid(grid);

        // if there are still fresh ones, then there must be islands not reachable from rotten ones
        return gridCount[1] == 0 ? depth : -1;

    }
    public int search(int[][] grid) {
        List<Point> queue = new LinkedList<>();

        // initialize queue, add all rotten ones
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Point(i, j));
                }
            }
        }

        int depth = 0;
        List<Point> queue2 = new ArrayList<>();
        while (!queue.isEmpty()) {
            // all the elements in the current queue are at same level from head
            queue2.clear();
            while (!queue.isEmpty()) {
                Point parent = queue.remove(0);

                List<Point> children = getFreshNeighbors(grid, parent);
                queue2.addAll(children);
            }

            if (queue2.isEmpty()) {
                // no fresh ones find
                break;
            }

            depth++;
            queue.addAll(queue2);
        }

        return depth;
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

    /**
     * get fresh neighbors and mark them as rotten
     * @param grid
     * @param parent
     * @return
     */
    private List<Point> getFreshNeighbors(int[][] grid, Point parent) {
        List<Point> freshNeighbors = new ArrayList<>();
        for (int i=0; i<dir.length; i++) {
            int row = parent.row + dir[i][0];
            int col = parent.col + dir[i][1];

            if (row>=0 && row<grid.length && col>=0 && col<grid[0].length) {
                if (grid[row][col]==1) {
                    grid[row][col]=2;
                    freshNeighbors.add(new Point(row,col));
                }
            }
        }

        return freshNeighbors;
    }

    @Test
    public void testRun1() {
        RottenOrange2 rottenOrange = new RottenOrange2();
        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};

        int result = rottenOrange.orangesRotting(grid);
        System.out.println("result = " +result);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testRun2() {
        RottenOrange2 rottenOrange = new RottenOrange2();
        int[][] grid = new int[][] {{2,1,1},{1,1,0},{0,1,1}};
        int result = rottenOrange.orangesRotting(grid);
        System.out.println("result = " +result);
        Assert.assertEquals(4, result);

        List<Integer> list = new LinkedList<>();
        Optional<Integer> found = list.stream().filter(n -> n>0).findAny();
        if (found.isPresent()) {

        }
    }
}
