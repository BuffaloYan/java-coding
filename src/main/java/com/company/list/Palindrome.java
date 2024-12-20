package com.company.list;

import org.junit.jupiter.api.Test;

public class Palindrome {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node reverse(Node root) {
        Node newRoot, curr, temp;
        newRoot = root;
        curr = root;
        while (curr.next != null) {
            temp = curr.next;
            curr.next = curr.next.next;

            temp.next = newRoot;
            newRoot = temp;
        }

        return newRoot;
    }

    public static Node findMiddle(Node root) {
        Node slow, fast;
        slow = fast = root;
        while (fast.next != null) {
            slow = slow.next;

            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            } else {
                // even number of nodes
                break;
            }
        }

        return slow;
    }

    public static boolean checkPanlinDrome(Node root) {
        Node middle = findMiddle(root);
        Node middleReversed = reverse(middle);

        Node left = root;
        Node right = middleReversed;
        while (left != null && right != null ) {
            if (left.data != right.data) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static void printList(Node root) {
        StringBuilder sb = new StringBuilder();
        sb.append(root.data);
        while (root.next != null) {
            sb.append(" -> ").append(root.next.data);
            root = root.next;
        }

        System.out.println(sb.toString());
    }

    @Test
    public void testFindMiddle() {
        Node[] nodes = new Node[7];

        for (int i=0; i<nodes.length; i++) {
            nodes[i] = new Node(i);

            if (i>0) {
                nodes[i-1].next = nodes[i];
            }
        }

        Node root = nodes[0];
        Node middle = findMiddle(root);

        System.out.println("middle node data = " + middle.data);

    }

    @Test
    public void testPanlindrome() {
        Node[] nodes = new Node[9];

        for (int i=0; i<nodes.length; i++) {
            nodes[i] = new Node(i);

            if (i>0) {
                nodes[i-1].next = nodes[i];
            }
        }

        for (int i=nodes.length-1; i>=nodes.length/2; i--) {
            nodes[i].data = (nodes.length-1)-i;
        }

        Node root = nodes[0];
        printList(root);
        System.out.println("is panlindrome = " + checkPanlinDrome(root));
    }
}
