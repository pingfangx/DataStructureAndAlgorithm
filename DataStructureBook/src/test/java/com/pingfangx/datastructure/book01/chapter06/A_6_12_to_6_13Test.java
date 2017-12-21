package com.pingfangx.datastructure.book01.chapter06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_6_12_to_6_13 Tester.
 *
 * @author pingfangx
 * @since 12/20/2017
 */
public class A_6_12_to_6_13Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: HuffmanCoding(int[] w, int n)
     */
    @Test
    public void testHuffmanCoding() throws Exception {
        A_6_12_to_6_13.HuffmanCoding(new int[]{
                5, 29, 7, 8, 14, 23, 3, 11
        }, 8);
    }

    /**
     * Method: HuffmanCoding2(int[] w, int n)
     */
    @Test
    public void testHuffmanCoding2() throws Exception {
        A_6_12_to_6_13.HuffmanCoding2(new int[]{
                5, 29, 7, 8, 14, 23, 3, 11
        }, 8);
    }
} 
