package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * MGraph Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/22/2017</pre>
 */
public class MGraphTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: create()
     */
    @Test
    public void testCreate() throws Exception {
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
        LogUtils.d(graph);

        graph = create_7_34();
        LogUtils.d(graph);
    }

    public static MGraph create_7_34() {
        int[][] arcs = new int[][]{
                {1, 2, 5},
                {2, 3, 50},
                {3, 5, 10},
                {4, 3, 20},
                {4, 5, 60},
                {5},
                {0, 2, 10},
                {0, 4, 30},
                {0, 5, 100},
        };
        return MGraph.create("v", 6, arcs);
    }

} 
