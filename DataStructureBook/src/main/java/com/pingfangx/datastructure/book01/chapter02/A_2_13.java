package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_13 {
    public static void main(String[] args) {
        StaticLinkListNode[] list = DataBuilder.size(10).max(10).unique().sort().print().buildStaticLinkList();
        LogUtils.d(locateElement(list, 9));
    }

    private static Object locateElement(StaticLinkListNode[] list, int element) {
        int i = list[0].cur;
        while (i != 0 && (int) list[i].data != element) {
            i = list[i].cur;
        }
        return i;
    }
}
