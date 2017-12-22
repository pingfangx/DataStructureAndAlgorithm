package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_7_10_to_7_11 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/26/2017</pre>
 */
public class A_7_10_to_7_11Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findArticul(ALGraph g)
     */
    @Test
    public void testFindArticul() throws Exception {
        ALGraph graph = ALGraphTest.createTestGraph();
        LogUtils.d(graph);
        new A_7_10_to_7_11().findArticul(graph);
    }
} 
