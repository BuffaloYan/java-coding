package com.company.recursive;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoinChangeSmart {

	public static int makeChange(int n, int[] denoms) {
		int[][] map = new int[n + 1][denoms.length];
		int[] countCoins = new int[denoms.length];

		return makeChangeHelper(n, denoms, 0, map, countCoins);
	}

	private static int totalScan = 0;
	private static int totalScanNoCache = 0;
	private static int minCoins = Integer.MAX_VALUE;
	public static int makeChangeHelper(int total, int[] denoms, int index, int[][] map, int[] countCoins) {
		totalScan++;
		if (total == 0) {
			int numOfCoins = Arrays.stream(countCoins).sum();
			minCoins = Math.min(minCoins, numOfCoins);
			System.out.printf("%d: %d %d %d %d\n", numOfCoins, countCoins[0], countCoins[1], countCoins[2], countCoins[3]);
			return 1;
		}

		if (index >= denoms.length) {
			return 0;
		}

//		/* Check cache for prior result. */
		if (map[total][index] > 0) { // retrieve value
			return map[total][index];
		}
		totalScanNoCache++;

		int coin = denoms[index];
//		if (index == denoms.length - 1) {
//			int remaining = total % coin;
//			return remaining == 0 ? 1 : 0;
//		}
		int numberOfWays = 0;
		/* Try 0 coin (don't use this coin), then 1 coin, 2 coins, ... (recursing each time on rest). */
		for (int amount = 0; amount <= total; amount += coin) {
			numberOfWays += makeChangeHelper(total - amount, denoms, index + 1, map, countCoins); // go to next denom
			countCoins[index]++;
		}
		countCoins[index] = 0;

		/* Update cache with current result. */
		map[total][index] = numberOfWays;
		
		return numberOfWays;
	}	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int[] denoms = {25, 10, 5, 1};
		int ways = makeChange(1100, denoms);
		long end = System.currentTimeMillis();
		System.out.printf("ways: %d, time elapsed: %d\n", ways,  (end-start));
		System.out.printf("totalScan: %d, totalScanNoCache: %d\n", totalScan, totalScanNoCache);
	}

	@Test
	public void testRun() {
		/**
		 *         [186,419,83,408]
		 *          * 6249
		 */
		int[] coins = new int[] {419, 408, 186, 83};

		long start = System.currentTimeMillis();

		CoinChangeSmart.makeChange(6249, coins);
		long end = System.currentTimeMillis();
		System.out.println(" time elapsed: " + (end-start));
		System.out.println(" minimum coins: " + CoinChangeSmart.minCoins);

	}

	@Test
	public void testRun1() {
		int[] coins = new int[] {419, 408, 186, 83};
		CoinChangeSmart changeSmart = new CoinChangeSmart();
		int[] result = changeSmart.minChanges(coins, 6249);
		List<String> list = Arrays.stream(result)
				.mapToObj(n -> String.valueOf(n))
				.collect(Collectors.toList());
		System.out.println(String.join(",", list));
	}
	public int[] minChanges(int[] coins, int amount) {
		int[] result = new int[coins.length];
		return _minChanges(coins, amount, 0, result);
	}

	public int[] _minChanges(int[] coins, int remain, int coinIndex, int[] result) {
		result[coinIndex] = remain/coins[coinIndex];
		int remainSub = remain % coins[coinIndex];

		if (remainSub == 0) {
			return result;
		}
		if (coinIndex == coins.length-1) {
			// last coin, not possible
			return null;
		}

		for (int a=remainSub; a <= remain; a+= coins[coinIndex]) {
			int[] subresult = _minChanges(coins, a, coinIndex+1, result);
			if (subresult != null) {
				// found solution
				return subresult;
			}

			result[coinIndex]--;
		}

		return null;
	}
}
