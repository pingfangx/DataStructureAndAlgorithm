package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.structure.List;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_2 {
    public static void main(String[] args) {
        List listA = DataBuilder.size(5).max(10).sort().unique().print().buildList();
        List listB = DataBuilder.size(5).max(10).sort().unique().print().buildList();
        LogUtils.d(mergeList(listA, listB));
    }

    static List mergeList(List listA, List listB) {
        int lengthA = listA.length();
        int lengthB = listB.length();
        int a = 0;
        int b = 0;
        int c = 0;
        List listC = new List();
        while (a < lengthA && b < lengthB) {
            if (listA.get(a) <= listB.get(b)) {
                listC.add(c++, listA.get(a++));
            } else {
                listC.add(c++, listB.get(b++));
            }
        }
        while (a < lengthA) {
            listC.add(c++, listA.get(a++));
        }
        while (b < lengthB) {
            listC.add(c++, listB.get(b++));
        }
        return listC;
    }
}
