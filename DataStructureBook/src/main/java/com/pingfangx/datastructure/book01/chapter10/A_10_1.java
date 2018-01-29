package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/16
 */
public class A_10_1 {
    /**
     * 直接插入排序
     * 最好情况，正序时，比较 n-1 次，不进入内层循环
     * 最坏情况，逆序时，比软次数为
     * ∑((i-1)*2+1,i=[1,n-1])
     * 在书中的算法中，使用的是 list[0] 来保存监视哨，因些内层循环只需比较一次，比较次数为
     * ∑i,i=[2,n]
     * =(n-2+1)/2*(2+n)
     * =(n+2)(n+1)/2
     * 移动次数为
     * ∑i+1,i=[2,n]
     * =(n-2+1)/2*(2+1+n+1)
     * =(n+4)(n-2)/2
     * <p>
     * 平均约为 n^2/4，时间复杂度为 O(n^2)
     */
    public static void insertSort(int[] list) {
        if (list == null || list.length <= 1) {
            return;
        }
        for (int i = 1; i < list.length; i++) {
            if (list[i] < list[i - 1]) {
                //小于，插入 list[i]
                //取 list[i] 作为监视哨
                int t = list[i];
                list[i] = list[i - 1];
                int j;
                for (j = i - 2; j >= 0 && t < list[j]; j--) {
                    //往前依次比较，如果小于，则需要向后移
                    list[j + 1] = list[j];
                }
                //比较结束， j+1 为要插入的位置
                list[j + 1] = t;
            }
        }
    }
}
