package com.pingfangx.datastructure.book01.chapter04;

import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/10
 */
public class A_4_1 {
    public static void main(String[] args) {
        String a = "abcdefg";
        String b = "efg";
        LogUtils.d(index(a, b, 0));
    }

    private static int index(String a, String b, int start) {
        if (start > -1) {
            int lengthA = a.length();
            int lengthB = b.length();
            int i = start;
            //如 7-1，可以取6
            while (i < lengthA - lengthB + 1) {
                String sub = a.substring(i, i + lengthB);
                if (sub.equals(b)) {
                    return i;
                } else {
                    i++;
                }
            }
        }
        return -1;
    }
}
