package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.chapter02.List;
import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BiTree Tester.
 *
 * @author pingfangx
 * @since 12/05/2017
 */
public class BiTreeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: buildByDepth(int depth)
     */
    @Test
    public void testBuildByDepth() throws Exception {
        LogUtils.d(BiTree.buildByDepth(0));
        LogUtils.d(BiTree.buildByDepth(1));
        LogUtils.d(BiTree.buildByDepth(2));
        LogUtils.d(BiTree.buildByDepth(3));
        LogUtils.d(BiTree.buildByDepth(4));
    }

    /**
     * Method: buildByNodeNumber(int nodeNumber)
     */
    @Test
    public void testBuildByNodeNumber() throws Exception {
        for (int i = 0; i < 10; i++) {
            LogUtils.d(BiTree.buildByNodeNumber(i));
            LogUtils.d("");
        }
    }

    /**
     * Method: buildByList(List data, int index)
     */
    @Test
    public void testBuildByList() throws Exception {
        List list = new List();
        for (int i = 0; i < 64; i++) {
            list.add(i, i + 1);
        }
        LogUtils.d(BiTree.buildByList(list));
        list.set(26, -1);
        LogUtils.d(BiTree.buildByList(list));
        list.set(7, -1);
        LogUtils.d(BiTree.buildByList(list));
        list.set(4, -1);
        LogUtils.d(BiTree.buildByList(list));
    }


} 
