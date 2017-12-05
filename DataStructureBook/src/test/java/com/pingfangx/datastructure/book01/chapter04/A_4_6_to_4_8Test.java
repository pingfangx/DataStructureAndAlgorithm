package com.pingfangx.datastructure.book01.chapter04;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * A_4_6_to_4_8 Tester.
 *
 * @author pingfangx
 * @since 12/05/2017
 */
public class A_4_6_to_4_8Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: index_KMP(String s, String t, int pos, int[] next)
     */
    @Test
    public void testIndex_KMP() throws Exception {
        String s = "ababcabcacbab";
        String t = "abcac";
        int[] next = A_4_6_to_4_8.getNext(t);
        LogUtils.d(A_4_6_to_4_8.index_KMP(s, t, 0, next));
    }

    /**
     * Method: getNext(String t)
     */
    @Test
    public void testGetNext() throws Exception {
        int[] next = new int[]{-1, 0, 0, 1, 1, 2, 0, 1};
        int[] result = A_4_6_to_4_8.getNext("abaabcac");
        assertArrayEquals(next, result);
    }


    /**
     * Method: getNext2(String t)
     */
    @Test
    public void testGetNext2() throws Exception {
        LogUtils.d(Arrays.toString(A_4_6_to_4_8.getNext("aaaab")));
        LogUtils.d(Arrays.toString(A_4_6_to_4_8.getNext2("aaaab")));
        LogUtils.d(Arrays.toString(A_4_6_to_4_8.getNext("baaaab")));
        LogUtils.d(Arrays.toString(A_4_6_to_4_8.getNext2("baaaab")));
    }
} 
