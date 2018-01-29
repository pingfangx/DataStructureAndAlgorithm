package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_10_to_10_11 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/17/2018</pre>
 */
public class A_10_10_to_10_11Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: heapAdjust(int[] h, int s, int m)
     */
    @Test
    public void testHeapAdjust() throws Exception {
    }

    /**
     * Method: heapSort(int[] h)
     */
    @Test
    public void testHeapSort() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        new A_10_10_to_10_11().heapSort(list);
        LogUtils.d(list);
        Assert.assertArrayEquals(list, A_10_1Test.getList_a_10_1_result());
    }

    /**
     * Method: printListAsHeap(int[] list)
     */
    @Test
    public void testPrintListAsHeap() throws Exception {
        A_10_10_to_10_11.printListAsHeap(new int[]{96, 83, 27, 38, 11, 9});
        A_10_10_to_10_11.printListAsHeap(new int[]{12, 36, 24, 85, 47, 30, 53, 91});
    }


} 
