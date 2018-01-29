package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/16
 */
public class A_10_2 {
    /**
     * 折半插入排序
     * 最坏情况，移动同 10.1 ，因此时间复杂度仍为 O(n^2)
     */
    public static void binaryInsertSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int t = list[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int m = (low + high) / 2;
                if (t < list[m]) {
                    high = m - 1;
                } else {
                    low = m + 1;
                }
            }
            for (int j = i - 1; j >= high + 1; j--) {
                int u = list[j + 1];
                list[j + 1] = list[j];
                list[j] = u;
            }
            list[high + 1] = t;
        }
    }
}
