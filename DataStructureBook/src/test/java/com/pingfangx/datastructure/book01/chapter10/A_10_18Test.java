package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_10_18 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/29/2018</pre>
 */
public class A_10_18Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: rearrange(int[] list, int[] adr)
     */
    @Test
    public void testRearrange() throws Exception {
        int[] list = new int[]{49, 65, 38, 27, 97, 13, 76, 49};
        int[] adr = new int[]{6, 4, 3, 1, 8, 2, 7, 5};
        LogUtils.d(list);
        LogUtils.d(adr);
        for (int i = 0; i < adr.length; i++) {
            adr[i] -= 1;
        }
        new A_10_18().rearrange(list, adr);
        for (int i = 0; i < adr.length; i++) {
            adr[i] += 1;
        }
        LogUtils.d(list);
        LogUtils.d(adr);
    }


} 
