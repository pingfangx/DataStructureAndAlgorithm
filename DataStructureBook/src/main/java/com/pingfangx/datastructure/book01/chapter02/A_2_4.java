package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;


/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_4 {
    public static void main(String[] args) {
        List list = DataBuilder.size(10).max(10).sort().unique().print().buildList();
        LogUtils.d(insert(list, 1, 22));
        LogUtils.d(list);
    }

    /**
     * 在 index 之前添加 element
     * 让后面的元素依次后移
     */
    static Object insert(List list, int index, int element) {
        if (index < 1 || index > list.length() + 1) {
            return STATUS.ERROR;
        }
        for (int p = list.length() - 2; p > index; p--) {
            list.set(p + 1, list.get(p));
        }
        list.add(index - 1, element);
        return STATUS.OK;
    }
}
