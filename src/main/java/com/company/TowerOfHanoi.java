package com.company;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class TowerOfHanoi {
    public static class Tower {
        Stack<Integer> stack = new Stack<>();
        int num;
        public Tower(int num) {
            this.num = num;
        }
    }

    /**
     * move numOfDisks from tower1 to tower2 via tower3
     * @param tower1
     * @param tower2
     * @param tower3
     * @param numOfDisks
     */
    public static void move(Tower tower1, Tower tower2, Tower tower3, int numOfDisks) {
        if (numOfDisks > 1) {
            move(tower1, tower3, tower2, numOfDisks - 1);
        }

        moveDisk(tower1, tower2);

        if (numOfDisks > 1) {
            move(tower3, tower2, tower1, numOfDisks - 1);
        }
    }

    /**
     * move top disk from tower1 to tower2
     * @param tower1
     * @param tower2
     */
    public static void moveDisk(Tower tower1, Tower tower2) {
        int disk = tower1.stack.pop();
        System.out.println("move disk " + disk + " from tower " + tower1.num + " to tower " + tower2.num);
        tower2.stack.push( disk );

    }

    @Test
    public void testRun() {
        Tower tower = new Tower(1);
        int numOfDisks = 3;
        for(int i=numOfDisks; i>=1; i--) {
            tower.stack.push(i);
        }

        move(tower, new Tower(2), new Tower(3), tower.stack.size());
    }
}
