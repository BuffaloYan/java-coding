package com.company.matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildingShortestDistance {
    int shortest = Integer.MAX_VALUE;
    List<int[]> houseLoc = new ArrayList<>();
    int[][] houseDistSum;

    public int shortestDistance(int[][] grid) {

        houseDistSum = new int[grid.length][grid[0].length];
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    houseLoc.add(new int[]{i, j});
                    calcDist(grid, i, j);
                }
            }
        }

        int[][] houseDistSumCopy = houseDistSum;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        boolean found = false;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    shortest = Integer.MAX_VALUE;
                    dfs(grid, visited, new int[]{i,j}, 0);

                    // not all houses are reachable
                    if (isAllHouseVisited(visited)) {
                        found = true;
                        break;
                    };
                    resetHouseVisited(visited);
                }
            }

            if (found) break;
        }

        return found? shortest : -1;
    }


    /**
     calculate distance to house (r,c) for all empty spaces
     */
    private void calcDist(int[][] grid, int r, int c) {

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    houseDistSum[i][j] += Math.abs(r-i) + Math.abs(c-j);

                }
            }
        }

    }

    private int[][] dirs = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
    private void dfs(int[][] grid, boolean[][] visited, int[] loc, int d) {
        if (
                loc[0] <0 || loc[0]>= grid.length || // invalid locations
                        loc[1] <0 || loc[1]>= grid[0].length ||
                        visited[loc[0]][loc[1]] ) {
            return;
        }

        visited[loc[0]][loc[1]] = true;
        if (grid[loc[0]][loc[1]] != 0) return;

        // calculate travel distance
        shortest = Math.min(shortest, houseDistSum[loc[0]][loc[1]]);

        for (int i=0; i<4; i++) {
            int r = loc[0] + dirs[d][0];
            int c = loc[1] + dirs[d][1];

            dfs(grid, visited, new int[]{r, c}, d);

            d = (d+1)%4;
        }
    }

    private boolean isAllHouseVisited(boolean[][] visited) {
        for (int i=0; i<houseLoc.size(); i++) {
            if (!visited[houseLoc.get(i)[0]][houseLoc.get(i)[1]]) return false;
        }

        return true;
    }

    private void resetHouseVisited(boolean[][] visited) {
        for (int i=0; i<houseLoc.size(); i++) {
            visited[houseLoc.get(i)[0]][houseLoc.get(i)[1]] = false;
        }

    }

    @Test
    public void testRun() {
        BuildingShortestDistance shortestDistance = new BuildingShortestDistance();
        int[][] grid = new int[][] {{1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};

        int dist = shortestDistance.shortestDistance(grid);

        Assert.assertEquals(88, dist);
    }
}
