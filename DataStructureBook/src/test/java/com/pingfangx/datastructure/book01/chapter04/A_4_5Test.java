package com.pingfangx.datastructure.book01.chapter04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A_4_5 Tester.
 *
 * @author pingfangx
 * @since 12/04/2017
 */
public class A_4_5Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: index(String s1, String s2, int pos)
     */
    @Test
    public void testIndex() throws Exception {
        assertEquals(A_4_5.index(null, "", 0), -1);
        assertEquals(A_4_5.index("", null, 0), -1);
        assertEquals(A_4_5.index("", "", 1), -1);
        assertEquals(A_4_5.index("", "", 0), -1);
        assertEquals(A_4_5.index("a", "", 1), -1);
        assertEquals(A_4_5.index("a", "", 0), 0);
        assertEquals(A_4_5.index("abcde", "", 2), 2);
        assertEquals(A_4_5.index("abcba", "a", 0), 0);
        assertEquals(A_4_5.index("abcba", "a", 1), 4);
        assertEquals(A_4_5.index("abcba", "abcba", 0), 0);
        assertEquals(A_4_5.index("abcba", "abcba", 1), -1);
        assertEquals(A_4_5.index("abcba", "ba", 1), 3);
    }


} 
