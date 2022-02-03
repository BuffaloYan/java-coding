package com.company.sort;

import org.junit.Assert;
import org.junit.Test;

public class TrackNumber {
    public static class Node {
        int lessCount;
        int data;
        Node left, right;
    }

    private Node root;

    public void track(int x) {
        if (root == null) {
            root = new Node();
            root.data = x;
            return;
        }

        track(root, x);
    }

    private void track(Node root, int x) {

        if (x > root.data) {
            if (root.right == null) {
                Node node = new Node();
                node.data = x;
                root.right = node;
            } else {
                track(root.right, x);
            }
        } else {
            root.lessCount++;
            if (root.left == null) {
                Node node = new Node();
                node.data = x;
                root.left = node;
            } else {
                track(root.left, x);
            }
        }
    }

    public int findNumberLess(int x) {
        return findNumberLess(root, x);
    }

    private int findNumberLess(Node root, int x) {
        if (root == null) {
            return 0;
        }

        if (x == root.data) {
            return root.lessCount;
        }

        if (x > root.data) {
            return root.lessCount + 1 + findNumberLess(root.right, x);
        } else {
            return findNumberLess(root.left, x);
        }
    }

    @Test
    public void testRun() {
        TrackNumber trackNumber = new TrackNumber();
        int[] data = {20, 15, 10, 5, 13, 25, 23, 24};
        for (int d: data) {
            trackNumber.track(d);
        }

        Assert.assertEquals(4, trackNumber.findNumberLess(20));
        Assert.assertEquals(2, trackNumber.findNumberLess(13));
        Assert.assertEquals(6, trackNumber.findNumberLess(24));

    }
}
