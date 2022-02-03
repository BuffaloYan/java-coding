package com.company.recursive;

public class Coin {

	public static int makeChange(int n, int[] denoms) {
		int[][] map = new int[n + 1][denoms.length];
		return makeChangeHelper(n, denoms, 0, map);
	}

	private static int totalScan = 0;
	private static int totalScanNoCache = 0;
	public static int makeChangeHelper(int total, int[] denoms, int index, int[][] map) {
		totalScan++;
		if (total == 0) {
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
		/* Try 1 coin, then 2 coins, 3 three, ... (recursing each time on rest). */
		for (int amount = 0; amount <= total; amount += coin) {
			numberOfWays += makeChangeHelper(total - amount, denoms, index + 1, map); // go to next denom

		}
		
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

}
