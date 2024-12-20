package com.company.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * FIFO limited size circular queue implemented with array
 */
public class CircularQueue  {
    int[] data;
    int headIndex = 0; // point to first available slot to read
    int tailIndex = 0; // point to next available slot to write

    public CircularQueue() {
        data = new int[101];
    }

//    public CircularQueue(int size) {
//        data = new int[size+1];
//    }

    // enqueue
    public void offer(int num) {
        if ( isFull() ) {
            throw new IllegalStateException("queue is full");
        }

        data[tailIndex++] = num;
    }

    public boolean isFull() {
        return (tailIndex+1)%data.length == headIndex;
    }

    public boolean isEmpty() {
        return tailIndex == headIndex;
    }

    // peek head value in the queue
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }

        return data[headIndex];
    }

    // remove first available value
    public int poll() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }

        return data[headIndex++];
    }

    public void reset(int n) {
        data = new int[n+1];
        headIndex = 0;
        tailIndex = 0;
    }

    @Test
    public void testQueue() {
        CircularQueue queue = new CircularQueue();
        queue.reset(2);

        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.offer(1);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.offer(2);
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        try {
            queue.offer(3);
            fail("exception expected when full");
        } catch (IllegalStateException e) {

        }

        int num = queue.peek();
        assertEquals(1, num);
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        // peek should not impact state
        num = queue.peek();
        assertEquals(1, num);
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        num = queue.poll();
        assertEquals(1, num);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        num = queue.poll();
        assertEquals(2, num);
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());

        try {
            queue.peek();
            fail("exception expected when peek at empty");
        } catch (IllegalStateException e) {

        }
    }

}
