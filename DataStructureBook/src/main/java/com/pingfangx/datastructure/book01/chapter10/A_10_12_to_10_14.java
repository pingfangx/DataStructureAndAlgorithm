package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/26
 */
public class A_10_12_to_10_14 {
    /**
     * source[i,m] 和 source[m+1,n] 归并为 target[i,n]
     * i 指示 i 到 m
     * j 指示 m+1 到 n
     * k = i 开始，指示 target 的索引
     */
    void merge(int[] source, int target[], int i, int m, int n) {
        int j = m + 1;
        int k;
        for (k = i; i <= m && j <= n; k++) {
            if (source[i] <= source[j]) {
                target[k] = source[i++];
            } else {
                target[k] = source[j++];
            }
        }
        if (i <= m) {
            System.arraycopy(source, i, target, k, m - i + 1);
        }
        if (j <= n) {
            System.arraycopy(source, j, target, k, n - k + 1);
        }
    }

    /**
     * 将数组分为左右两部分，先排序左边的，再排序右边的，然后左右归并
     */
    void mSort(int[] source, int[] target1, int s, int t) {
        if (s == t) {
            target1[s] = source[s];
        } else {
            int m = (s + t) / 2;
            int[] target2 = new int[target1.length];
            mSort(source, target2, s, m);
            mSort(source, target2, m + 1, t);
            merge(target2, target1, s, m, t);
        }
    }

    void mergeSort(int[] list) {
        if (list.length == 0) {
            return;
        }
        mSort(list, list, 0, list.length - 1);
    }
}
