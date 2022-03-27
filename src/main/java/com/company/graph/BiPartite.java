package com.company.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BiPartite {
    boolean oddCycle = false;
    public boolean isBipartite(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i=0; i<graph.length; i++) {
            if (!visited[i] && hasOddCycle(graph, visited, i, new ArrayList<Integer>())) {
                return false;
            }
        }


        return true;
    }

    private boolean hasOddCycle(int[][] graph, boolean[] visited, int index, List<Integer> path) {
        if (visited[index]) {
            int pathIndex = path.indexOf(index);
            // check cycle in current path
            if (pathIndex >= 0
                    && (path.size()-1-pathIndex) > 1 // skip immediate parent
                    && (path.size()-1-pathIndex)%2 == 0) {
                // there is odd number of node in the cycle
                return true;
            }

            return false;
        }

        visited[index] = true;
        path.add(index);

        for (int n: graph[index]) {
            if (hasOddCycle(graph, visited, n, path)) {
                return true;
            }
        }

        path.remove(path.size()-1);
        return false;
    }

    @Test
    public void testRun() {
        // [[3],[2,4],[1],[0,4],[1,3]]
        int[][] input = new int[][] {{3}, {2,4}, {1}, {0,4}, {1,3}};

        BiPartite biPartite = new BiPartite();
        boolean result = biPartite.isBipartite(input);
        Assert.assertEquals(true, result);
    }
}
