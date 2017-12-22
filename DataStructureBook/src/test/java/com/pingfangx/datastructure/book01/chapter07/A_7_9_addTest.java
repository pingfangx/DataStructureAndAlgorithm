package com.pingfangx.datastructure.book01.chapter07;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_7_9_add Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/22/2017</pre>
 */
public class A_7_9_addTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: miniSpanTree(MGraph graph)
     */
    @Test
    public void testMiniSpanTree() throws Exception {
        //先外面一圏，再中间 5 条
        int[][] arcs = new int[][]{
                {1, 2, 6},
                {2, 5, 3},
                {5, 6, 6},
                {6, 4, 2},
                {4, 1, 5},
                {3, 1, 1},
                {3, 2, 5},
                {3, 5, 6},
                {3, 6, 4},
                {3, 4, 5},
        };
        MGraph graph = MGraph.create(6, arcs);
        new A_7_9_add().miniSpanTree(graph);
    }


}
