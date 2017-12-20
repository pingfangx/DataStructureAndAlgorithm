package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_10 {

    public static void main(String[] args) {
        LinkList linkList = DataBuilder.size(5).print().buildLinkList();
        LogUtils.d(listDelete(linkList, 5));
        LogUtils.d(linkList);
    }

    /**
     * 比较乱的是 删除第 0 个和删除第 1 个，循环后 i 都为 0，所有分开处理了
     */
    private static Object listDelete(LinkList linkList, int position) {
        if (position == 0) {
            if (linkList.next != null) {
                linkList.next = linkList.next.next;
            } else {
                return STATUS.ERROR;
            }
        } else {
            LinkNode next = linkList.next;
            int i = 0;
            //退出时 i=position-1
            while (next != null && i < position - 1) {
                next = next.next;
                i++;
            }
            if (next == null || next.next == null || i > position - 1) {
                return STATUS.ERROR;
            } else {
                next.next = next.next.next;
            }
        }
        return STATUS.OK;
    }
}
