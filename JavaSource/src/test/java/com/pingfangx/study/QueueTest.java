package com.pingfangx.study;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pingfangx
 * @date 2018/2/5
 */
public class QueueTest {
    @Test
    public void testNullElement() {
        Queue<Object> queue = new LinkedList<>();
        queue.add(null);
        queue.add(1);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }

    @Test
    public void testCalculateSize() {
        calculateSize((int) (Math.pow(2, 31) - 1));
        calculateSize((int) Math.pow(2, 30));
        for (int i = 1; i < 32; i++) {
            int n = (int) Math.pow(2, i) - 1;
            System.out.println("pow=" + i + ",num=" + n + ",size=" + calculateSize(n));
        }
    }

    private static int calculateSize(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }
}
