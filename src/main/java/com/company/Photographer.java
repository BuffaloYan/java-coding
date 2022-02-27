package com.company;

import org.junit.Assert;
import org.junit.Test;

public class Photographer {

    public long getArtisticPhotographCount(int N, String C, int X, int Y) {
        // Write your code here
        int[] prefixP = prefixSum(C, 'P');
        int[] prefixB = prefixSum(C, 'B');

        long count = 0;
        for (int i=1; i<C.length()-1; i++) {
            if (C.charAt(i) == 'A') {
                int indexx = i - X;
                int indexy = i - Y - 1 ;
                //if (indexy < 0) indexy = 0;
                if (indexx < 0) continue;
                int countP1 = indexy < 0 ? prefixP[indexx] : prefixP[indexx] - prefixP[indexy];
                int countB1 = indexy < 0 ? prefixB[indexx] : prefixB[indexx] - prefixB[indexy];

                indexx = i + X -1;
                indexy = i + Y;
                if (indexx >= C.length()) continue;
                if (indexy >= C.length()) indexy = C.length()-1;
                int countP2 = prefixP[indexy] - prefixP[indexx];
                int countB2 = prefixB[indexy] - prefixB[indexx];

                count += countP1 * countB2 + countP2 * countB1;
            }
        }

        return count;


    }
    private int[] prefixSum(String s, char ch) {
        int[] sum = new int[s.length()];
        sum[0] = s.charAt(0) == ch ? 1 : 0;

        for (int i=1; i<s.length(); i++) {
            sum[i] = s.charAt(i) == ch ? sum[i-1]+1 : sum[i-1];
        }

        return sum;
    }

    @Test
    public void testRun() {
        Photographer photographer = new Photographer();
        long result = photographer.getArtisticPhotographCount(5, "APABA", 1, 2);
        Assert.assertEquals(1, result);
    }
}
