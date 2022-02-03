package com.company.recursive;

import org.junit.Test;

import java.util.Arrays;

public class Changes {
    private static int coins[] = {25, 10, 5, 1};
    private static int totalScan = 0;
    private static int totalCount = 0;
    public static void makeChanges(int sum) {
        int[] countCoins = {0, 0, 0, 0};
        int amount = 0;
        makeChanges(sum, countCoins, 0, amount);

        System.out.printf("totalScan: %d, totalCount: %d\n", totalScan, totalCount);
    }

    private static void makeChanges(int total, int[] countCoins, int coinStartIndex, int amount) {
        if (amount > total || coinStartIndex >= countCoins.length) {
            return;
        }

        totalScan++;

        if (total == amount) {
            totalCount++;
            //System.out.printf("%d %d %d %d\n", countCoins[0], countCoins[1], countCoins[2], countCoins[3]);
            return;
        }

        for (int i=coinStartIndex; i<countCoins.length; i++) {
            if (coinStartIndex == countCoins.length-1) {

                int remain = (total-amount)%coins[i];
                if ( remain == 0) {
                    //int restCount = (total-amount)/coins[i];
                    //countCoins[i] += restCount;
                    totalCount++;
                }

            } else {
                // use the coin
                countCoins[i]++;
                makeChanges(total, countCoins, i, amount + coins[i]);

                // stop using the coin
                countCoins[i]--;
            }
        }

    }

    @Test
    public void testRun() {
        long start = System.currentTimeMillis();
        makeChanges(1100);
        long end = System.currentTimeMillis();
        System.out.println(" time elapsed: " + (end-start));
    }
}
