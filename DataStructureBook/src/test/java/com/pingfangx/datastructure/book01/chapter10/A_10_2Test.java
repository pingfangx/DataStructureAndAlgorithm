package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_2 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/16/2018</pre>
 */
public class A_10_2Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: binaryInsertSort(int[] list)
     */
    @Test
    public void testBinaryInsertSort() throws Exception {
        int[] list = A_10_1Test.getList_a_10_1();
        LogUtils.d(list);
        A_10_2.binaryInsertSort(list);
        LogUtils.d(list);
    }


} 
