package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * A_7_12 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/26/2017</pre>
 */
public class A_7_12Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: topologicalSort(ALGraph g)
     */
    @Test
    public void testTopologicalSort() throws Exception {
        ALGraph graph = ALGraphTest.createTestGraph2();
        LogUtils.d(graph);
        LogUtils.d(A_7_12.topologicalSort(graph));
    }

    /**
     * Method: findInDegree(ALGraph g)
     */
    @Test
    public void testFindInDegree() throws Exception {
        ALGraph graph = ALGraphTest.createTestGraph2();
        LogUtils.d(graph);
        LogUtils.d(Arrays.toString(A_7_12.findInDegree(graph)));
    }


} 
