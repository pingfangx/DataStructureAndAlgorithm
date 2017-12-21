package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_6_1 Tester.
 *
 * @author pingfangx
 * @since 12/05/2017
 */
public class A_6_1Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: preorderTraverse(BiTree biTree)
     */
    @Test
    public void testPreOrderTraverse() throws Exception {
    }

    /**
     * Method: inorderTraverse(BiTree biTree)
     */
    @Test
    public void testInOrderTraverse() throws Exception {
        BiTree biTree = BiTree.buildByDepth(4);
        LogUtils.d(biTree);
        A_6_1.inorderTraverse(biTree);
    }

    /**
     * Method: postorderTraverse(BiTree biTree)
     */
    @Test
    public void testPostOrderTraverse() throws Exception {
        BiTree biTree = BiTree.buildByDepth(4);
        LogUtils.d(biTree);
        A_6_1.postorderTraverse(biTree);
    }
}
