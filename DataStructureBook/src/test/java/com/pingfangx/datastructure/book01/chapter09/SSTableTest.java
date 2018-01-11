package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SSTable Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/28/2017</pre>
 */
public class SSTableTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: create(int[] data)
     */
    @Test
    public void testCreate() throws Exception {
        SSTable table = create_a_9_1_2();
        LogUtils.d(table);
    }

    public static SSTable create_a_9_1_2() {
        return SSTable.create(new int[]{
                5, 13, 19, 21, 37, 56, 64, 75, 80, 88, 92,
        });
    }

} 
