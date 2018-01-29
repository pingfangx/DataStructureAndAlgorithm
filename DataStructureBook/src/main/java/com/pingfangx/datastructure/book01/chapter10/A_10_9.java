package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class A_10_9 {
    /**
     * 直接选择排序
     * 移动次数
     * 最好 0
     * 最坏 3(n-1)
     * 比较次数
     * ∑(n-1-i,i=[0,n-1])
     * =n/2*(n-1+0)
     * =n(n-1)/2
     * 时间复杂度 O(n^2)
     */
    void selectSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int j = selectMin(list, i);
            if (i != j) {
                int t = list[i];
                list[i] = list[j];
                list[j] = t;
            }
        }
    }

    /**
     * 选择最小的记录
     */
    private int selectMin(int[] list, int start) {
        int min = start;
        for (int i = min + 1; i < list.length; i++) {
            if (list[i] < list[min]) {
                min = i;
            }
        }
        return min;
    }
}
