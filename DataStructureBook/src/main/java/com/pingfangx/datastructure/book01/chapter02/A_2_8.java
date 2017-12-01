package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.constant.STATUS;
import com.pingfangx.datastructure.common.structure.LinkList;
import com.pingfangx.datastructure.common.structure.LinkNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_8 {
    public static void main(String[] args) {
        LinkList linkList = DataBuilder.size(5).print().buildLinkList();
        LogUtils.d(getElement(linkList, 4));
    }

    private static Object getElement(LinkList linkList, int index) {
        LinkNode next = linkList.next;
        int i = 0;
        while (next != null && i < index) {
            next = next.next;
            i++;
        }
        if (next == null || i > index) {
            return STATUS.ERROR;
        }
        return next.data;
    }
}
