package com.company.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuySellStock4 {
    List<int[]> _sales;
    int[] _prices;
    int[][] _dp;

    public int maxProfit(int k, int[] prices) {
        _sales = parseSales(prices);
        _prices = prices;

        if (_sales.size() == 0) {
            // no profitable transaction
            return 0;
        }

        int sum = 0;
        if (_sales.size() <= k) {
            // simply collect all possbile sales
            for(int[] s: _sales) {
                sum += prices[s[1]] - prices[s[0]];
            };
            return sum;
        }

        // dp matrix to track max profit start at ith sale with k transaction
        _dp = new int[_sales.size()][k+1];

        return maxProfit(0, k);
    }

    private int maxProfit(int index, int k) {
        if (index >= _sales.size() || k <= 0) {
            return 0;
        }

        if (_dp[index][k] > 0) {
            return _dp[index][k];
        }

        if (k == 1) {
            // one transaction left, easier problem
            _dp[index][k] = maxProfitLast(index);
            return _dp[index][k];
        }

        // start first transaction on index buy point, try all sell point leaving k-1 for future transactions
        int buyPrice = _prices[_sales.get(index)[0]];
        int lastSellPrice = buyPrice;
        int maxProfit = 0;
        for (int i=index; i<_sales.size()-(k-1); i++) {
            int sellPrice =  _prices[_sales.get(i)[1]];
            if (sellPrice <= lastSellPrice) {
                continue;
            }
            int profit = sellPrice-buyPrice + maxProfit(i+1, k-1);
            maxProfit = Math.max(maxProfit, profit);

            // use this to skip lower selling point
            lastSellPrice = sellPrice;
        }

        // don't use current sale
        maxProfit = Math.max(maxProfit, maxProfit(index+1, k));

        _dp[index][k] = maxProfit;
        return maxProfit;
    }

    private int maxProfitLast(int index) {
        int maxProfit = 0;
        int lowestBuyPrice = _prices[_sales.get(index)[0]];
        for (int i=index; i<_sales.size(); i++) {
            lowestBuyPrice = Math.min(lowestBuyPrice, _prices[_sales.get(i)[0]]);
            maxProfit = Math.max(maxProfit, _prices[_sales.get(i)[1]]-lowestBuyPrice);
        }

        return maxProfit;
    }

    /**
     * parse prices of single days into pair of days for possible single profitable sale
     */
    private List<int[]> parseSales(int[] prices) {
        int i=0;
        List<int[]> list = new ArrayList<>();
        while (i<prices.length) {
            // find valley
            int v = i;
            while (v+1 < prices.length && prices[v+1] < prices[v]) v++;

            if (v >= prices.length-1) {
                // valley at the end, no more sale opportunity
                break;
            }

            //find peak;
            int p = v+1;
            while (p+1 < prices.length && prices[p+1] > prices[p]) p++;

            if (p >= prices.length) {
                break;
            }

            if (prices[p] > prices[v]) {
                list.add(new int[]{v, p});
            }

            i = p+1;
        }

        return list;
    }

    @Test
    public void testRun1() {
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        BuySellStock4 buySellStock4 = new BuySellStock4();
        int result = buySellStock4.maxProfit(2, prices);

        assertEquals(6, result);
    }

    @Test
    public void testRun2() {
        int[] prices = new int[]{1,2,4,2,5,7,2,4,9,0};
        BuySellStock4 buySellStock4 = new BuySellStock4();
        int result = buySellStock4.maxProfit(2, prices);

        assertEquals(13, result);
    }

    @Test
    public void testRun3() {
        int[] prices = new int[]{8,6,4,3,3,2,3,5,8,3,8,2,6};
        BuySellStock4 buySellStock4 = new BuySellStock4();
        int result = buySellStock4.maxProfit(2, prices);

        assertEquals(11, result);
    }

    @Test
    public void testRun4() {
        int[] prices = new int[]{5,2,3,0,3,5,6,8,1,5};
        BuySellStock4 buySellStock4 = new BuySellStock4();
        int result = buySellStock4.maxProfit(2, prices);

        assertEquals(12, result);
    }

}
