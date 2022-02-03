package com.company.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Ordering {
    public static void arrangeOrderly(int[] data1, int[] data2) {
        arrangeOrderly(data1, 0, data2, 0, "");
    }

    public static void arrangeOrderly(int[] data1, int index1, int[] data2, int index2, String ordered){
        if (index1>=data1.length && index2>=data2.length) {
            System.out.println(ordered);
            return;
        }

        if (index1<data1.length) {
            arrangeOrderly(data1, index1+1, data2, index2, ordered + ", " + data1[index1]);
        }

        if (index2<data2.length) {
            arrangeOrderly(data1, index1, data2, index2+1, ordered + ", " + data2[index2]);
        }

    }

    public static class Node {
        Node left, right;
        int data;
    }

    public static List<List<Integer>> arrangeBST(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> listLeft=null, listRight=null;
        if (root.left != null) {
            listLeft = arrangeBST(root.left);
        }

        if (root.right != null) {
            listRight = arrangeBST(root.right);
        }

        if (listLeft != null && listRight != null) {

        }

        return null;
    }

    @Test
    public void testOrder() {
        int[] data1 = {1,2};
        int[] data2 = {4,5};

        arrangeOrderly(data1, data2);
    }

    public void testOrderBST(Node n) {
        String abc = "abc";
        abc.hashCode();
    }
}
