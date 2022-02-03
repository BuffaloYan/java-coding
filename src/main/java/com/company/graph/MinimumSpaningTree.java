package com.company.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpaningTree {
    public static class DisjointSet {
        int[] roots;
        int[] rank;

        public DisjointSet(int size) {
            roots = new int[size];
            rank = new int[size];

            for (int i=0; i<size; i++) {
                roots[i] = i;
                rank[i] = 1;
            }
        }

        public int findRoot(int i) {
            if (roots[i] == i) {
                return i;
            }

            return roots[i] = findRoot(roots[i]);
        }

        public void union(int i, int j) {
            int rootI = findRoot(i);
            int rootJ = findRoot(j);

            if (rootI != rootJ) {
                if (rank[rootI] > rank[rootJ]) {
                    roots[rootJ] = rootI;
                } else if (rank[rootI] < rank[rootJ]) {
                    roots[rootI] = rootJ;
                } else {
                    roots[rootI] = rootJ;
                    rank[rootJ]++;
                }
            }
        }

        public boolean isConnected(int i, int j) {
            return findRoot(i) == findRoot(j);
        }
    }

    public static class Edge implements Comparable<Edge> {
        int i, j, cost;
        public Edge(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        public int compareTo(Edge target) {
            // Edge target = (Edge) obj;

            return ((Integer)this.cost).compareTo(target.cost);
        }
    }

    public int minCostConnectPoints(int[][] points) {
        DisjointSet disjointSet = new DisjointSet(points.length);
        List<Edge> edgeList = new ArrayList<>();

        // calculate distance
        for (int i=0; i<points.length-1; i++) {
            for (int j=i+1; j<points.length; j++) {
                int distance = Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                edgeList.add(new Edge(i, j, distance));
            }
        }

        // sort
        Collections.sort(edgeList);

        int sum = 0;
        int edgeCount = 0;
        // loop each edge
        for(Edge edge: edgeList) {
            if (disjointSet.isConnected(edge.i, edge.j)) {
                continue;
            }

            // joint the points
            disjointSet.union(edge.i, edge.j);

            sum += edge.cost;
            edgeCount++;

            if (edgeCount == points.length-1) {
                break;
            }
        }

        // return
        return sum;
    }

    @Test
    public void testRun1() {
       // [[0,0],[2,2],[3,10],[5,2],[7,0]]
       int[][] points = new int[][] {{0,0}, {2,2}, {3,10},{5,2},{7,0}};

       MinimumSpaningTree spaningTree = new MinimumSpaningTree();
       int result = spaningTree.minCostConnectPoints(points);

        Assert.assertEquals(20, result);

    }
}
