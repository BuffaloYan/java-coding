package com.company.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class JobSchedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        PriorityQueue<Integer>[] dayDiff = new PriorityQueue[d];
        for (int i=0; i<d; i++) {
            // initialize max priority queue
            dayDiff[i] = new PriorityQueue<Integer>((a,b) -> b.compareTo(a));
        }

        // for dynamic programming
        int[][] dp = new int[jobDifficulty.length][d];
        for (int i=0; i<jobDifficulty.length; i++) {
            for (int j=0; j<d; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }


        int result = schedule(jobDifficulty, dayDiff, 0, 0, dp);

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return result;
    }

    private int schedule(int[] jobs, PriorityQueue<Integer>[] dayDiffQueue, int d, int jobIndex, int[][] dp) {
        if (d >= dayDiffQueue.length || jobIndex >= jobs.length) return Integer.MAX_VALUE;

        if (dp[jobIndex][d] != Integer.MAX_VALUE) {
            return dp[jobIndex][d];
        }

        // work on jobIndex on current day d
        dayDiffQueue[d].add( jobs[jobIndex] );

        if (jobIndex == jobs.length-1 && d == dayDiffQueue.length-1) {
            // last job on last day!
            // calculate sum of difficulties

            int sum = 0;
            for (int i=0; i<dayDiffQueue.length; i++) {
                sum += dayDiffQueue[i].peek();
            }

            return sum;
        }

        int sum = Integer.MAX_VALUE, sum2 = Integer.MAX_VALUE;

        if (jobIndex < jobs.length-1) {

            // try continue working on same day
            if (jobs.length-jobIndex >= dayDiffQueue.length-d) {
                sum = schedule(jobs, dayDiffQueue, d, jobIndex + 1, dp);
                dayDiffQueue[d].remove(jobs[jobIndex + 1]);
            } else {
                // not enough days left, fail fast
                return Integer.MAX_VALUE;
            }

            // or work on next task on next day
            if (d < dayDiffQueue.length-1) {
                sum2 = schedule(jobs, dayDiffQueue, d+1, jobIndex+1, dp);
                dayDiffQueue[d+1].remove(jobs[jobIndex+1]);
            }

        }

        sum = Math.min(sum, sum2);
        dp[jobIndex][d] = Math.min(sum, dp[jobIndex][d]);
        return Math.min(sum, sum2);
    }

    @Test
    public void testRun1() {
        /**
         * [6,5,4,3,2,1]
         * 2
         */

        int[] jobs = new int[] {6,5,4,3,2,1};

        JobSchedule jobSchedule = new JobSchedule();
        int result = jobSchedule.minDifficulty(jobs, 2);

        Assert.assertEquals(7, result);
    }

    @Test
    public void testRun2() {
        /**
         * [6,5,4,3,2,1]
         * 2
         */

        int[] jobs = new int[] {9, 9 , 9};

        JobSchedule jobSchedule = new JobSchedule();
        int result = jobSchedule.minDifficulty(jobs, 4);

        Assert.assertEquals(-1, result);
    }


    @Test
    public void testRun3() {
        /**
         [11,111,22,222,33,333,44,444]
         6
         */

        int[] jobs = new int[] {11,111,22,222,33,333,44,444};

        JobSchedule jobSchedule = new JobSchedule();
        int result = jobSchedule.minDifficulty(jobs, 6);

        Assert.assertEquals(843, result);
    }
}
