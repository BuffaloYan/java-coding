package com.company.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

public class NumOfCoins2 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        int result = coinChange(coins, amount, new int[amount+1], 0);
        System.out.println("globalMinCoins = " + globalMinCoins);

        return result;
    }

    int globalMinCoins = Integer.MAX_VALUE;

    private int coinChange(int[] coins, int remain, int[] count, int num_of_coins) {
        if (remain < 0) return -1;
        if (remain == 0) {
            if (num_of_coins < globalMinCoins) {
                globalMinCoins = num_of_coins;
            }
            return 0;
        }
        if (count[remain] != 0) return count[remain];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, remain - coin, count, num_of_coins+1);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[remain] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remain];
    }

    @Test
    public void testRun1() {
        /**
         * [186,419,83,408]
         * 6249
         * 20
         */
        int[] coins = new int[]{186,419,83,408};
        NumOfCoins2 numOfCoins = new NumOfCoins2();
        int result = numOfCoins.coinChange(coins, 6249);

        Assert.assertEquals(20, result);
    }
}
