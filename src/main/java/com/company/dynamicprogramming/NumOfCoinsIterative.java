package com.company.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumOfCoinsIterative {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;

        int[] dp = new int[amount+1];

        dp[0] = 0;
        for (int i=1; i<=amount; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int j=0; j<coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i-coins[j]] >= 0) {
                    minCoins = Math.min(minCoins, dp[i-coins[j]]);
                }
            }
            dp[i] = minCoins == Integer.MAX_VALUE? -1 : minCoins + 1;
        }

        return dp[amount];

    }


    @Test
    public void testRun2() {
        /**
         * [186,419,83,408]
         * 6249
         * 20
         */
        int[] coins = new int[]{83,186,408,419};
        NumOfCoinsIterative numOfCoins = new NumOfCoinsIterative();
        int result = numOfCoins.coinChange(coins, 6249);

        assertEquals(20, result);
    }
}
