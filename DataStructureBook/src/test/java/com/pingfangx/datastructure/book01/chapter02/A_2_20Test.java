package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.structure.LinkList;
import com.pingfangx.datastructure.common.structure.LinkNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_2_20 Tester.
 *
 * @author pingfangx
 * @since 12/05/2017
 */
public class A_2_20Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: listInsert(LinkList linkList, int i, int data)
     */
    @Test
    public void testListInsert() throws Exception {
        LogUtils.d("");
        LinkList linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LogUtils.d(A_2_20.listInsert(linkList, -1, 100));
        LogUtils.d(linkList);

        LogUtils.d("");
        linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LogUtils.d(A_2_20.listInsert(linkList, 0, 101));
        LogUtils.d(linkList);

        LogUtils.d("");
        linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LogUtils.d(A_2_20.listInsert(linkList, 4, 102));
        LogUtils.d(linkList);

        LogUtils.d("");
        linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LogUtils.d(A_2_20.listInsert(linkList, 5, 103));
        LogUtils.d(linkList);
    }

    /**
     * Method: insFirst(LinkNode linkNode, LinkNode newNode)
     */
    @Test
    public void testInsFirst() throws Exception {
        LinkList linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LinkNode linkNode = new LinkNode(100, null);
        A_2_20.insFirst(linkList.next, linkNode);
        LogUtils.d(linkList);

        linkNode = new LinkNode(101, null);
        A_2_20.insFirst(A_2_20.locatePos(linkList, 5), linkNode);
        LogUtils.d(linkList);
    }

    /**
     * Method: locatePos(LinkList linkList, int index)
     */
    @Test
    public void testLocatePos() throws Exception {
        LinkList linkList = DataBuilder.size(5).max(5).sort().unique().print().buildLinkList();
        LogUtils.d(A_2_20.locatePos(linkList, 0));
        LogUtils.d(A_2_20.locatePos(linkList, 1));
        LogUtils.d(A_2_20.locatePos(linkList, 5));
    }


}
