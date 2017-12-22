package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * A_7_15 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/27/2017</pre>
 */
public class A_7_15Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: shortestPath_DIJ(MGraph g, int v0, boolean[][] p, int[] d)
     */
    @Test
    public void testShortestPath_DIJ() throws Exception {
        MGraph graph = MGraphTest.create_7_34();
        LogUtils.d(graph);
        boolean[][] p = new boolean[graph.vexnum][graph.vexnum];
        int[] d = new int[graph.vexnum];
        A_7_15.shortestPath_DIJ(graph, 0, p, d);
        for (int i = 0; i < p.length; i++) {
            boolean[] booleans = p[i];
            LogUtils.d(i + ":" + Arrays.toString(booleans));
        }
        LogUtils.d(Arrays.toString(d));
    }


} 
