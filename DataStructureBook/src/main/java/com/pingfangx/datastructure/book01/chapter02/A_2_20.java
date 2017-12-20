package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * 改写自 2.9
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_20 {
    public static STATUS listInsert(LinkList linkList, int i, int data) {
        LinkNode linkNode = locatePos(linkList, i);
        if (linkNode == null) {
            return STATUS.ERROR;
        }
        LinkNode newNode = makeNode(data);
        if (newNode == null) {
            return null;
        }
        insFirst(linkNode, newNode);
        return STATUS.OK;
    }

    /**
     * 将结点插入到 首结点之前
     * 因为链表指向首结点，于是采取了变通的方法，将插入点的数据与首结点交换
     * 再将要插入的点插入到首结点之后
     */
    public static void insFirst(LinkNode linkNode, LinkNode newNode) {
        //交换数据
        Object data = linkNode.data;
        linkNode.data = newNode.data;
        newNode.data = data;
        //指向
        newNode.next = linkNode.next;
        linkNode.next = newNode;
    }

    public static LinkNode locatePos(LinkList linkList, int index) {
        if (linkList == null) {
            return null;
        }
        LinkNode next = linkList.next;
        int i = 0;
        while (next != null && i < index) {
            next = next.next;
            i++;
        }
        if (i > index) {
            return null;
        }
        return next;
    }

    private static LinkNode makeNode(int data) {
        return new LinkNode(data, null);
    }
}
