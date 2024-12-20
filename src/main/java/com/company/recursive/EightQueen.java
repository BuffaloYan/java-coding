package com.company.recursive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {
    public static class Position {
        int row, col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    private static int count = 0;
    public static int arrangeQueen(int n) {
        return arrangeQueen(0, new int[n][n], new ArrayList<>(), 0);
    }

    // arrange ith queen
    private static int arrangeQueen(int i, int[][] flags, List<Position> queenPositions, int count) {
        // each queen has n choice at row i
        for (int j=0; j<flags.length; j++) {
            if (flags[i][j]==0) {
                // available position
                List<Position> marked = markPosition(flags, i, j);
                queenPositions.add( new Position(i, j));
                if (i == flags.length-1) {
                    // all queens are in place
                    print(queenPositions);
                    count++;
                } else if (i <flags.length-1) {
                    count = arrangeQueen(i + 1, flags, queenPositions, count);
                }

                unMarkPosition(flags, i, marked);
                queenPositions.remove(i);

            }
        }

        return count;
    }

    private static void unMarkPosition(int[][] flags, int i, List<Position> marked) {
        for (Position p: marked) {
            flags[p.row][p.col] = 0;
        }
    }

    private static List<Position> markPosition(int[][] flags, int i, int j) {
        List<Position> marked = new ArrayList<>();

        // mark i row
        for (int k=0; k<flags.length; k++) {
            if (flags[i][k] == 0) {
                marked.add(new Position(i, k));
                // mark the positions
                flags[i][k] = 1;
            }
        }

        // mark j column starting at i+1 row
        for (int k=i+1; k<flags.length; k++) {
            if (flags[k][j] == 0) {
                marked.add(new Position(k, j));
                // mark the positions
                flags[k][j] = 1;
            }
        }

        // mark forward diagnal
        for (int k=i+1; k<flags.length && k-i+j<flags.length; k++) {
            if (flags[k][k-i+j] == 0) {
                marked.add(new Position(k,k-i+j));
                // mark the positions
                flags[k][k-i+j] = 1;
            }
        }

        // mark backward diagnal
        for (int k=i+1; k<flags.length && j-k+i>=0; k++) {
            if (flags[k][j-k+i] == 0) {
                marked.add(new Position(k,j-k+i));
                // mark the positions
                flags[k][j-k+i] = 1;
            }
        }

        // place queen here
        flags[i][j] = 2;

        return marked;
    }

    private static void print(List<Position> queenPositions) {
        System.out.println( queenPositions.stream()
                .map( p -> p.toString())
                .reduce("", (s1,s2) -> s1.length() == 0 ? s2 : s1 + ", " + s2)
        );
    }

    @Test
    public void testRuns() {
        for (int i=3; i<10; i++) {
            int count = arrangeQueen(i);
            System.out.println("Board size: " + i + ", Total count: " + count);
        }
    }

    @Test
    public void testRun() {
        for (int i=4; i<5; i++) {
            int count = arrangeQueen(i);
            System.out.println("Board size: " + i + ", Total count: " + count);
        }
    }


}
