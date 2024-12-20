package com.company.list;

import org.junit.jupiter.api.Test;

public class SwapListNodeValue {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode root = head;
        ListNode reversed = reverseList(head);
        for (int i=0; i<k-1; i++) {
            head = head.next;
            reversed = reversed.next;
        }

        int temp = head.val;
        head.val = reversed.val;
        reversed.val = temp;

        return root;
    }

    private ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(head.val);
        head = head.next;

        while (head != null) {
            ListNode temp = new ListNode(head.val);
            head = head.next;

            temp.next = newHead;
            newHead = temp;
        }

        return newHead;
    }

    @Test
    public void testRun() {
        ListNode node = new ListNode(5);
        node = new ListNode(4, node);
        node = new ListNode(3, node);
        node = new ListNode(2, node);
        ListNode head = new ListNode(1, node);

        SwapListNodeValue swapListNodeValue = new SwapListNodeValue();
        swapListNodeValue.swapNodes(head, 2);

    }
}
