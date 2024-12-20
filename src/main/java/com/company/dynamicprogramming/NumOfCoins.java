package com.company.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumOfCoins {
    private static int totalScan = 0;
    private static int totalCount = 0;

    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;

        int[] dp = new int[amount+1];
        int[] countCoin = new int[coins.length];
        Arrays.sort(coins);

        int result = change(coins, amount, dp, countCoin);

        System.out.printf("totalScan:%d, totalCount:%d\n", totalScan, totalCount);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int change(int[] coins, int remain, int[] dp, int[] countCoins) {
        totalScan++;
        if (remain < 0) {
            // fail fast
            return -1;
        }

        if (remain == 0) {
            totalCount++;
            int numOfCoins = Arrays.stream(countCoins).sum();
            System.out.printf("%d: %d*%d %d*%d %d*%d %d*%d\n", numOfCoins, countCoins[0], coins[0], countCoins[1], coins[1],
                    countCoins[2], coins[2], countCoins[3], coins[3]);
            return 0;
        }

        if (dp[remain] == 0) {
            int minCoins = Integer.MAX_VALUE;
            for (int i=coins.length-1; i>=0; i--) {
                if (coins[i] <= remain) {
                    countCoins[i]++;
                    int numOfCoins = change(coins, remain - coins[i], dp, countCoins);
                    countCoins[i]--;
                    if (numOfCoins >= 0) {
                        minCoins = Math.min(minCoins, 1 + numOfCoins);
                    }
                }
            }

            dp[remain] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }

        return dp[remain];
    }

    @Test
    public void testRun1() {
        /**
         * [186,419,83,408]
         * 6249
         * 20
         */
        int[] coins = new int[]{83,186,408,419};
        NumOfCoins numOfCoins = new NumOfCoins();
        int result = numOfCoins.coinChange(coins, 6249);

        assertEquals(20, result);
    }

    @Test
    public void testRun2() {
        /**
         * [186,419,83,408]
         * 6249
         * 20
         */
        int[] coins = new int[]{25, 10, 5, 1};
        NumOfCoins numOfCoins = new NumOfCoins();
        int result = numOfCoins.coinChange(coins, 1100);

        assertEquals(20, result);
    }
}
