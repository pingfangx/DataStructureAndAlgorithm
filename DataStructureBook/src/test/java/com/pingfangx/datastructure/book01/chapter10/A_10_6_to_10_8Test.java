package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_6_to_10_8 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/17/2018</pre>
 */
public class A_10_6_to_10_8Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: bubbleSort(int[] list)
     */
    @Test
    public void testBubbleSort() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        new A_10_6_to_10_8().bubbleSort(list);
        LogUtils.d(list);
        Assert.assertArrayEquals(list, A_10_1Test.getList_a_10_1_result());
    }

    /**
     * Method: partitionA(int[] list, int low, int high)
     */
    @Test
    public void testPartitionA() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        new A_10_6_to_10_8().partitionA(list, 0, list.length - 1);
        LogUtils.d(list);
    }

    /**
     * Method: partitionB(int[] list, int low, int high)
     */
    @Test
    public void testPartitionB() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        new A_10_6_to_10_8().partitionB(list, 0, list.length - 1);
        LogUtils.d(list);
    }

    /**
     * Method: QuickSort(int[] list)
     */
    @Test
    public void testQuickSort() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        new A_10_6_to_10_8().quickSort(list);
        LogUtils.d(list);
        Assert.assertArrayEquals(list, A_10_1Test.getList_a_10_1_result());
    }


} 
