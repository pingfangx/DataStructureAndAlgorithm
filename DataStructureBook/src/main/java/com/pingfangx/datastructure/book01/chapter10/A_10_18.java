package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/29
 */
public class A_10_18 {
    /**
     * 先按地址进行排序，排完后重排数据
     */
    void rearrange(int[] list, int[] adr) {
        for (int i = 0; i < list.length; i++) {
            if (adr[i] != i) {
                //不相等，需要重排
                int j = i;
                //暂存 list[i]
                int t = list[i];
                while (adr[j] != i) {
                    //这里看作是 adr 中的 2 个元素交互，在交换过程中，一并交换数据
                    //直到最后交换形成一个循环
                    //取出位置
                    int k = adr[j];
                    //将置赋给下一个位置
                    list[j] = list[k];
                    //将地址赋为 j ，即已经处理的位置
                    adr[j] = j;
                    // j 指向 k 继续循环
                    j = k;
                }
                //退出循环时，形成一个小循环
                // j 的值赋为 一开始暂存的 list[i]
                list[j] = t;
                adr[j] = j;
            }
        }
    }
}
