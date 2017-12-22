package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ALGraph Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/25/2017</pre>
 */
public class ALGraphTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: create(String nodeRange, String[] arcs)
     */
    @Test
    public void testCreate() throws Exception {
        ALGraph graph = createTestGraph();
        LogUtils.d(graph);
        graph = createTestGraph2();
        LogUtils.d(graph);
        graph = createTestGraph_7_30();
        LogUtils.d(graph);
    }


    public static ALGraph createTestGraph() {
        return ALGraph.create("AM", new String[]{
                "AB", "AC", "AF", "AL",
                "BC", "BD", "BG", "BH", "BM",
                "C",
                "DE",
                "E",
                "F",
                "GH", "GI", "GK",
                "HK",
                "I",
                "JL", "JM",
                "K",
                "LM",
                "M"
        });
    }

    public static ALGraph createTestGraph2() {
        return ALGraph.create("A", "1-6", new String[]{
                "1-2", "1-3", "1-4",
                "2",
                "3-2", "3-5",
                "4-5",
                "5",
                "6-4", "6-5"
        });
    }

    /**
     * 图 7.30
     */
    public static ALGraph createTestGraph_7_30() {
        return ALGraph.create("V", "1-6", new String[]{
                "1-2-3", "1-3-2",
                "2-4-2", "2-5-3",
                "3-4-4", "3-6-3",
                "4-6-2",
                "5-6-1",
                "6"
        });
    }

} 
