package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.util.IOUtils;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * 创建链表
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_11 {
    public static void main(String[] args) {
        LogUtils.d(A_2_11.createLinkList(0));
        LogUtils.d(A_2_11.createLinkList(3));
    }

    public static LinkList createLinkList(int n) {
        LinkList linkList = new LinkList();
        linkList.next = null;
        LogUtils.d("请输入 %d 个结点", n);
        for (int i = 0; i < n; i++) {
            int input = IOUtils.getChar();
            linkList.next = new LinkNode(input, linkList.next);
        }
        return linkList;
    }
}
