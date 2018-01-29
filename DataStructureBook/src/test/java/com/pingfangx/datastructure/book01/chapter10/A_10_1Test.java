package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_1 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/16/2018</pre>
 */
public class A_10_1Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insertSort(int[] list)
     */
    @Test
    public void testInsertSort() throws Exception {
        int[] list = getList_a_10_1();
        LogUtils.d(list);
        A_10_1.insertSort(list);
        LogUtils.d(list);
    }

    /**
     * 10.1 的测试数组
     */
    public static int[] getList_a_10_1() {
        return new int[]{49, 38, 65, 97, 76, 13, 27, 49};
    }

    public static int[] getList_a_10_1_result() {
        return new int[]{13, 27, 38, 49, 49, 65, 76, 97};
    }

} 
