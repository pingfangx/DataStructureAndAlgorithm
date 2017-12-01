package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.structure.LinkList;
import com.pingfangx.datastructure.common.structure.LinkNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/30
 */
public class ReverseLinkList {
    public static void main(String[] args) {
        LinkList linkList = DataBuilder.size(5).min(1).max(6).print().unique().sort().buildLinkList();
        LogUtils.d(reverse2(linkList));
        LogUtils.d(reverse(linkList));
    }

    /**
     * 反转一个链表
     * 持有 pre ，遍历使每个结点指向 pre
     * 注意一开始 pre.next 和 最后 linkList.next 的赋值
     */
    private static LinkList reverse(LinkList linkList) {
        if (linkList == null) {
            return null;
        }
        LinkNode pre = linkList.next;
        LinkNode current = pre.next;
        pre.next = null;
        while (current != null) {
            LinkNode next = current.next;
            current.next = pre;

            pre = current;
            current = next;
        }
        linkList.next = pre;
        return linkList;
    }

    /**
     * 持有第一个元素，依次将它后面的一个元素移到开头
     */
    private static LinkList reverse2(LinkList linkList) {
        if (linkList == null) {
            return null;
        }
        LinkNode current = linkList.next;
        while (current.next != null) {
            //取下一个
            LinkNode tmp = current.next;

            //当前指向下下一个
            current.next = tmp.next;
            //指向链表开头元素
            tmp.next = linkList.next;
            //指向下一个
            linkList.next = tmp;
        }
        return linkList;
    }

}
