package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.constant.STATUS;
import com.pingfangx.datastructure.common.structure.DbLinkList;
import com.pingfangx.datastructure.common.structure.DbLinkNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_18 {
    public static void main(String[] args) {
        DbLinkList list = DataBuilder.size(5).print().sort().buildDoubleLinkList();
        LogUtils.d(listInsert(list, 5, 0));
        LogUtils.d(list);
    }

    /**
     * 原例中是循环的，这里没有循环，需要再补充 开头 和 结尾 的特殊处理。
     */
    private static Object listInsert(DbLinkList list, int index, int element) {
        if (index == 0) {
            DbLinkNode node = new DbLinkNode(element, list.next);
            list.next.prior = node;
            list.next = node;
        }
        DbLinkNode next = list.next;
        int i = 0;
        while (next != null && i < index - 1) {
            next = next.next;
            i++;
        }
        if (next == null || i > index - 1) {
            return STATUS.ERROR;
        }
        //返回时位于要插入位置的元素之前，
        DbLinkNode node = new DbLinkNode(element, next);
        node.prior = next;
        node.next = next.next;
        if (next.next != null) {
            next.next.prior = node;
        }
        next.next = node;
        return STATUS.OK;
    }
}
