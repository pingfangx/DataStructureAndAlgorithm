package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_19 {
    public static void main(String[] args) {
        DbLinkList list = DataBuilder.size(5).print().sort().buildDoubleLinkList();
        LogUtils.d(listDelete(list, 4));
        LogUtils.d(list);
    }

    private static Object listDelete(DbLinkList list, int index) {
        DbLinkNode next = list.next;
        int i = 0;
        while (next != null && i < index) {
            next = next.next;
            i++;
        }
        if (next == null || i > index) {
            return STATUS.ERROR;
        }
        if (next.prior != null) {
            next.prior.next = next.next;
        }
        if (next.next != null) {
            next.next.prior = next.prior;
        }
        return STATUS.OK;
    }
}
