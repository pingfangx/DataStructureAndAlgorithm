package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_6 {
    public static void main(String[] args) {
        List list = DataBuilder.size(10).max(10).sort().unique().print().buildList();
        LogUtils.d(locate(list, list.get(5)));
    }

    static int locate(List list, int e) {
        int i = 0;
        while (i < list.length() && !compare(e, list.get(i))) {
            i++;
        }
        if (i < list.length()) {
            return i;
        }
        return -1;
    }

    static boolean compare(int a, int b) {
        return a == b;
    }
}
