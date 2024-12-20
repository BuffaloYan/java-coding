package com.company.optimize;

import org.junit.jupiter.api.Test;

public class MaxBoxInTruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[][] dp = new int[boxTypes.length+1][truckSize+1];
        return maxUnits(boxTypes, 0, truckSize, dp);
    }

    public int maximumUnits2(int[][] boxTypes, int truckSize) {
        return maxUnits2(boxTypes, truckSize);
    }

    private int maxUnits2(int[][] boxTypes, int boxLimit) {
        int boxCount = 0;
        int boxUnits = 0;
        for (int i=0; i<boxTypes.length; i++) {
            if (boxTypes[i][0] <= (boxLimit-boxCount)) {
                // take all the boxes of this size
                boxCount += boxTypes[i][0];
                boxUnits += boxTypes[i][0] * boxTypes[i][1];
            } else {
                boxUnits += (boxLimit-boxCount) * boxTypes[i][1];
                break;
            }
        }

        return boxUnits;
    }

    private int maxUnits(int[][] boxTypes, int boxSize, int boxLimit, int[][] dp) {
        if (boxSize>boxTypes.length-1 || boxLimit <= 0) {
            return 0;
        }

        if (dp[boxSize][boxLimit] > 0) {
            return dp[boxSize][boxLimit];
        }

        // without this boxSize
        int maxUnits = maxUnits(boxTypes, boxSize+1, boxLimit, dp);

        int units = 0;
        for (int i=1; i<=boxLimit && i<= boxTypes[boxSize][0]; i++) {
            units += boxTypes[boxSize][1];
            maxUnits = Math.max(maxUnits, units + maxUnits(boxTypes, boxSize+1, boxLimit-i, dp));
        }

        dp[boxSize][boxLimit] = Math.max(dp[boxSize][boxLimit], maxUnits);

        return maxUnits;
    }

    @Test
    public void testRun() {
        // [[1,3],[2,2],[3,1]]
        int[][] boxTypes = {{1,3,}, {2,2,}, {3,1}};

        MaxBoxInTruck maxBox = new MaxBoxInTruck();
        int result = maxBox.maximumUnits2(boxTypes, 4);

        System.out.println("result = " + result);
    }
}
