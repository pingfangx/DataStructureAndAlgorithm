package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_6_3 Tester.
 *
 * @author pingfangx
 * @since 12/05/2017
 */
public class A_6_3Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: inorderTraverse(BiTree biTree)
     */
    @Test
    public void testInorderTraverse() throws Exception {
        BiTree biTree = BiTree.buildByDepth(4);
        LogUtils.d(biTree);
        A_6_3.inorderTraverse(biTree);
    }


} 
