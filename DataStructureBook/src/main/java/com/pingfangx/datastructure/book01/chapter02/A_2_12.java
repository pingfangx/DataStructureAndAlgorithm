package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_12 {
    public static void main(String[] args) {
        LinkList linkListA = DataBuilder.size(5).print().unique().sort().buildLinkList();
        LinkList linkListB = DataBuilder.size(5).print().unique().sort().buildLinkList();
        LogUtils.d(mergeList(linkListA, linkListB));
    }

    private static Object mergeList(LinkList listA, LinkList listB) {
        LinkList listC = new LinkList();
        LinkNode nodeA = listA.next;
        LinkNode nodeB = listB.next;
        LinkNode nodeC = null;
        while (nodeA != null && nodeB != null) {
            LinkNode node;
            if ((int) nodeA.data <= (int) nodeB.data) {
                node = nodeA;
                nodeA = nodeA.next;
            } else {
                node = nodeB;
                nodeB = nodeB.next;
            }
            if (nodeC == null) {
                listC.next = node;
            } else {
                nodeC.next = node;
            }
            nodeC = node;
        }
        //这里和原例相比，不大一样，原例应该是
        if (nodeC != null) {
            if (nodeA != null) {
                nodeC.next = nodeA;
            } else {
                nodeC.next = nodeB;
            }
        }
        return listC;
    }
}
