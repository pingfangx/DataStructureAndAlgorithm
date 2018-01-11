package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_9_1 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/28/2017</pre>
 */
public class A_9_1Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: search_seq(SSTable st, KeyType key)
     */
    @Test
    public void testSearch_seq() throws Exception {
        SSTable table = SSTableTest.create_a_9_1_2();
        LogUtils.d(table);
        LogUtils.d(A_9_1.search_seq(table, new KeyType(0)));
        LogUtils.d(A_9_1.search_seq(table, new KeyType(5)));
        LogUtils.d(A_9_1.search_seq(table, new KeyType(92)));
    }


} 
