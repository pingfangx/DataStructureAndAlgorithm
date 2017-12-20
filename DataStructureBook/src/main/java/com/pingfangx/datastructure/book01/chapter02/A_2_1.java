package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;


/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_1 {
    public static void main(String[] args) {
        List listA = DataBuilder.size(5).max(10).print().buildList();
        List listB = DataBuilder.size(5).max(10).print().buildList();
        union(listA, listB);
        LogUtils.d(listA);
    }

    public static void union(List listA, List listB) {
        int lengthA = listA.length();
        int lengthB = listB.length();
        for (int i = 0; i < lengthB; i++) {
            int b = listB.get(i);
            if (!listA.contains(b)) {
                listA.add(lengthA++, b);
            }
        }
    }
}
