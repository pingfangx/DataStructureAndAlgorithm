package com.pingfangx.study.tutorial.basic.collections.list;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/25
 */
public class ArrayListTest {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int grow(int minCapacity, int oldCapacity) {
        // overflow-conscious code
        //int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        //elementData = Arrays.copyOf(elementData, newCapacity);
        return newCapacity;
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 扩容是如何避免溢出的
     */
    @Test
    public void test_grow() {
        int oldCapacity = (int) (((1 << 31) - 1) * 0.9);
        int minCapacity = 1;
        /*
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
         */
        System.out.println("old=" + oldCapacity);
        System.out.println("min=" + minCapacity);
        System.out.println("grow=" + grow(minCapacity, oldCapacity));

        /*
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        取 MAX_ARRAY_SIZE
         */
        minCapacity = (1 << 31) - 100;
        System.out.println("old=" + oldCapacity);
        System.out.println("min=" + minCapacity);
        System.out.println("grow=" + grow(minCapacity, oldCapacity));


        /*
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        取 Integer.MAX_VALUE
         */
        minCapacity = (1 << 31) - 5;
        System.out.println("old=" + oldCapacity);
        System.out.println("min=" + minCapacity);
        System.out.println("grow=" + grow(minCapacity, oldCapacity));
    }
}
