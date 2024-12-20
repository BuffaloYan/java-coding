package com.company.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class RottenApple {
    public static class Apple implements Comparable<Apple> {
        int count;
        int rottenDay;
        int growDay;

        public Apple(int count, int rottenDay, int growDay) {
            this.count = count;
            this.rottenDay = rottenDay;
            this.growDay = growDay;
        }

        public int compareTo(Apple target) {
            return ((Integer)rottenDay).compareTo(target.rottenDay);
        }
    }


    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<Apple> queue = new PriorityQueue<>();

        int count = 0;
        int day = -1;
        Apple apple = null;
        do {
            // new day
            day++;

            // grow apple
            if (day < apples.length) {
                queue.add( new Apple(apples[day], day+days[day], day));
            }

            apple = null;
            while (!queue.isEmpty()) {
                apple = queue.peek();
                if (apple.rottenDay <= day) {
                    // rotten apple, get rid of it
                    queue.poll();
                    apple = null;
                    continue;
                }

                break;
            }

            if (apple != null) {
                // there is apple to eat
                count++;
                apple.count--;

                if (apple.count == 0) {
                    queue.poll();
                }
            } else if (day >= apples.length) {
                // no more apple produce and queue is empty
                break;
            }


        } while (true);

        return count;
    }

    public int eatenApples0(int[] apples, int[] days) {
        int maxDay = 0;
        for(int i=0; i<apples.length; i++) {
            maxDay = Math.max(maxDay, i+days[i]);
        }

        int[] changes = new int[maxDay+1];
        // everything will be rotten on maxDay

        for(int i=0; i<apples.length; i++) {
            changes[i] += apples[i];
            changes[i+days[i]] -= apples[i];
        }

        int balance = 0;
        int count = 0;
        for(int i=0; i<changes.length; i++) {
            balance += changes[i];
            if (balance>0) {
                // have apples to eat
                count++;
                balance--;
            }
        }

        return count;
    }

    @Test
    public void testRun1() {
        RottenApple rottenApple = new RottenApple();
        /**
         * [1,2,3,5,2]
         * [3,2,1,4,2]
         */
        int[] apples = new int[]{1,2,3,5,2};
        int[] days = new int[]{3,2,1,4,2};

        int count = rottenApple.eatenApples(apples, days);
        assertEquals(7, count);
    }
}
