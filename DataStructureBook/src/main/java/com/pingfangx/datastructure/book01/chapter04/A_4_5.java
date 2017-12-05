package com.pingfangx.datastructure.book01.chapter04;

/**
 * @author pingfangx
 * @date 2017/12/1
 */
public class A_4_5 {
    /**
     * 查找 s2 的位置，不使用其他操作
     */
    public static int index(String s1, String s2, int pos) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        if (pos >= s1.length()) {
            return -1;
        }
        if (s2.equals("")) {
            return pos;
        }

        int i = pos;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= s2.length()) {
            return i - s2.length();
        }
        return -1;
    }
}
