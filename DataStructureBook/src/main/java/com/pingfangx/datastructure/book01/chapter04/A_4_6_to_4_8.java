package com.pingfangx.datastructure.book01.chapter04;

/**
 * @author pingfangx
 * @date 2017/12/5
 */
public class A_4_6_to_4_8 {
    public static int index_KMP(String s, String t, int pos, int[] next) {
        int i = pos;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            //这里的-1是指没有 next 值，需比较 s[i+1] 和 t[0]
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= t.length()) {
            //匹配成功
            return i - t.length();
        }
        return -1;
    }

    /**
     * 求 kmp 算法中的 next 数组
     */
    public static int[] getNext(String t) {
        int[] next = new int[t.length()];
        //初始第 0 个置为-1
        int i = 0;
        next[0] = -1;
        int j = -1;
        while (i < t.length() - 1) {
            //当传入一个 j 时，会求字符中的 i 和 j 处是否相等，
            //如果相等，则next[i+1]=next[i]+1
            //如果不相等，则取 j=next[j] 处，继续比较字符串中 i 处和 next[j] 处
            //直到 j 的值为 next[0] 中的 -1
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 求 kmp 算法中的 next 数组
     */
    public static int[] getNext2(String t) {
        int[] next = new int[t.length()];
        //初始第 0 个置为-1
        int i = 0;
        next[0] = -1;
        int j = -1;
        while (i < t.length() - 1) {
            //当传入一个 j 时，会求字符中的 i 和 j 处是否相等，
            //如果相等，则next[i+1]=next[i]+1
            //如果不相等，则取 j=next[j] 处，继续比较字符串中 i 处和 next[j] 处
            //直到 j 的值为 next[0] 中的 -1
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                if (t.charAt(i) != t.charAt(j)) {
                    next[i] = j;
                } else {
                    //相等时，只需和前面相同的字母的 next 值相同即可
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
