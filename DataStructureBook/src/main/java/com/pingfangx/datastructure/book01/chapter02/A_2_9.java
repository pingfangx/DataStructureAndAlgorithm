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
public class A_2_9 {
    public static void main(String[] args) {
        LinkList linkList = DataBuilder.size(5).print().buildLinkList();
        LogUtils.d(listInsert(linkList, 0, 0));
        LogUtils.d(linkList);
    }

    /**
     * 原例中没有区分 LinkList 和 LinkNode，直接调用的 next
     */
    private static Object listInsert(LinkList linkList, int index, int element) {
        if (index == 0) {
            linkList.next = new LinkNode(element, linkList.next);
        } else {
            LinkNode next = linkList.next;
            int i = 0;
            while (next != null && i < index - 1) {
                next = next.next;
                i++;
            }
            //退出时 i=index-1
            if (next == null || i > index) {
                return STATUS.ERROR;
            }
            //如果是最后一个，next 也会置为 null
            next.next = new LinkNode(element, next.next);
        }
        return STATUS.OK;
    }
}
