package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class A_10_4_to_10_5 {
    static void shellInsert(int[] list, int dk) {
        //从 dk 开始，处理后面的
        for (int i = dk; i < list.length; i++) {
            if (list[i] < list[i - dk]) {
                //小于，将 list[i] 插入
                int t = list[i];
                int j;
                for (j = i - dk; j >= 0 && t < list[j]; j -= dk) {
                    //记录后移
                    list[j + dk] = list[j];
                }
                //插入
                list[j + dk] = t;
            }
        }
    }

    /**
     * 希尔排序
     */
    public static void shellSort(int[] list, int[] dlta, int t) {
        for (int k = 0; k < t; k++) {
            shellInsert(list, dlta[k]);
        }
    }

    public static void shellSort(int[] list) {
        int t = 3;
        int[] dlta = getDeltaList(list.length, t);
        shellSort(list, dlta, t);
    }

    /**
     * 获取增量列表
     */
    private static int[] getDeltaList(int n, int t) {
        return new int[]{5, 3, 1};
    }
}
