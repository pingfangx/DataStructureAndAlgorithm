package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_12_to_10_14 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/26/2018</pre>
 */
public class A_10_12_to_10_14Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: mergeSort(int[] list)
     */
    @Test
    public void testMergeSort() throws Exception {
        int[] list = new int[]{49, 38, 65, 97, 76, 13, 27};
        LogUtils.d(list);
        new A_10_12_to_10_14().mergeSort(list);
        LogUtils.d(list);
    }


} 
