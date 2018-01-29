package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_4_to_10_5 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/17/2018</pre>
 */
public class A_10_4_to_10_5Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: shellSort(int[] list, int[] dlta, int t)
     */
    @Test
    public void testShellSort() throws Exception {
        int[] list = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 55, 4};
        LogUtils.d(list);
        A_10_4_to_10_5.shellSort(list);
        LogUtils.d(list);
    }


} 
