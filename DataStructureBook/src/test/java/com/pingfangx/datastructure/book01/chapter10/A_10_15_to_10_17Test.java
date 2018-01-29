package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_15_to_10_17 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/26/2018</pre>
 */
public class A_10_15_to_10_17Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: distribute(SLCell[] r, int i, int[] f, int[] e)
     */
    @Test
    public void testDistribute() throws Exception {
    }

    /**
     * Method: collect(SLCell[] r, int i, int[] f, int[] e)
     */
    @Test
    public void testCollect() throws Exception {
    }

    /**
     * Method: radixSort(SLList list)
     */
    @Test
    public void testRadixSort() throws Exception {
        int[] data = new int[]{278, 109, 63, 930, 589, 184, 505, 269, 8, 83};
        A_10_15_to_10_17.SLList list = A_10_15_to_10_17.SLList.create(data);
        new A_10_15_to_10_17().radixSort(list);
        LogUtils.d(list);
    }


} 
