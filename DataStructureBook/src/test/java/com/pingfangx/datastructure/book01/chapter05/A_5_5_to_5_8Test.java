package com.pingfangx.datastructure.book01.chapter05;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * A_5_5_to_5_8 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/20/2017</pre>
 */
public class A_5_5_to_5_8Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: depth(GLNode L)
     */
    @Test
    public void testDepth() throws Exception {
        LogUtils.d(A_5_5_to_5_8.depth(null));
        LogUtils.d(A_5_5_to_5_8.depth(A_5_5_to_5_8.createGList("()")));
        LogUtils.d(A_5_5_to_5_8.depth(A_5_5_to_5_8.createGList("(1)")));
        LogUtils.d(A_5_5_to_5_8.depth(A_5_5_to_5_8.createGList("(1,2,3)")));
        LogUtils.d(A_5_5_to_5_8.depth(A_5_5_to_5_8.createGList("((),(3),(a,(b,c,d)))")));
        A_5_5_to_5_8.GLNode list = A_5_5_to_5_8.createGList("(1,2,(3))");
        LogUtils.d(list);
        LogUtils.d(A_5_5_to_5_8.depth(list));
    }

    /**
     * Method: copyGList(GLNode L)
     */
    @Test
    public void testCopyGList() throws Exception {
        A_5_5_to_5_8.GLNode list = A_5_5_to_5_8.createGList("(1,2,(3))");
        LogUtils.d(list);
        LogUtils.d(A_5_5_to_5_8.copyGList(list));
    }

    /**
     * Method: createGList(String S)
     */
    @Test
    public void testCreateGList() throws Exception {
        LogUtils.d(A_5_5_to_5_8.createGList(""));
        LogUtils.d(A_5_5_to_5_8.createGList("1"));
        LogUtils.d(A_5_5_to_5_8.createGList("()"));
        LogUtils.d(A_5_5_to_5_8.createGList("(1)"));
        LogUtils.d(A_5_5_to_5_8.createGList("(1,2,(3))"));
    }

    /**
     * Method: server(String str)
     */
    @Test
    public void testServer() throws Exception {
        LogUtils.d(Arrays.toString(A_5_5_to_5_8.server("")));
        LogUtils.d(Arrays.toString(A_5_5_to_5_8.server("a")));
        LogUtils.d(Arrays.toString(A_5_5_to_5_8.server("a,b")));
        LogUtils.d(Arrays.toString(A_5_5_to_5_8.server("(a,b)")));
        LogUtils.d(Arrays.toString(A_5_5_to_5_8.server("(a,b),(c)")));
    }

    @Test
    public void testString() {
        String a = "1234";
        LogUtils.d("a=%s", a);
        String b = a;
        LogUtils.d("b=%s", b);
        a = null;
        LogUtils.d("a=%s", a);
        LogUtils.d("b=%s", b);
    }

} 
