package com.company.array;

import org.junit.Assert;
import org.junit.Test;

public class StockTrade {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];

        int max = 0;
        for(int i=0; i<prices.length-1; i++) {
            max = Math.max(max, maxProfit(prices, dp, i));
        }

        return max;
    }

    private int maxProfit(int[] prices, int[] dp, int start) {
        if (start>=prices.length-1) {
            return 0;
        }

        if (dp[start]>0) {
            return dp[start];
        }

        int max = 0;
        for(int i=start; i<prices.length-1; i++) {
            // skip all downday
            for(int j=i+1; j<prices.length; j++) {
                if(prices[j]>prices[j-1]) {
                    break;
                }
                i++;
            }

            for(int j=i+1; j<prices.length; j++) {
                // try every possible sell day
                if (prices[j]>prices[i]) {
                    max = Math.max(max, prices[j]-prices[i] + maxProfit(prices, dp, j+1));
                }
            }
        }

        dp[start] = max;

        // retrofit down days
//        while (start>0) {
//            if (prices[start-1]>=prices[start]) {
//                dp[start-1] = dp[start];
//                start--;
//            } else {
//                break;
//            }
//        }
        return max;
    }

    @Test
    public void testRun1() {
        int[] prices = new int[] {7,1,5,3,6,4};
        StockTrade stockTrade = new StockTrade();
        int result = stockTrade.maxProfit(prices);

        Assert.assertEquals(7, result);
    }

    @Test
    public void testRun2() {
        int[] prices = new int[] {1,2,3,4,5};
        StockTrade stockTrade = new StockTrade();
        int result = stockTrade.maxProfit(prices);

        Assert.assertEquals(4, result);
    }

    @Test
    public void testRun3() {
        int[] prices = new int[] {7,6,4,3,1};
        StockTrade stockTrade = new StockTrade();
        int result = stockTrade.maxProfit(prices);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testRun4() {
        int[] prices = new int[] {6,1,3,2,4,7};
        StockTrade stockTrade = new StockTrade();
        int result = stockTrade.maxProfit(prices);

        Assert.assertEquals(7, result);

    }

}
