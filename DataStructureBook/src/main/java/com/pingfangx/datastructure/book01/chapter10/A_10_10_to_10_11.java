package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.book01.chapter06.BiTree;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class A_10_10_to_10_11 {
    /**
     * 筛选
     */
    void heapAdjust(int[] h, int s, int m) {
        int rc = h[s];
        for (int j = (s + 1) * 2 - 1; j <= m; j = (j + 1) * 2 - 1) {
            if (j < m && h[j] < h[j + 1]) {
                //没有到最后一个结点
                //且右树校大，j 移到右树
                j++;
            }
            if (rc >= h[j]) {
                // TODO: 2018/1/17 仔细查看这里 
                //这里写反就成了小顶堆
                //已经不小于，rc 应该插在该结点上，
                //即 rc 应插在 s 位置上
                break;
            }
            //否则，s 与 j 交换
            h[s] = h[j];
            s = j;
        }
        h[s] = rc;
    }

    void heapSort(int[] h) {
        printListAsHeap(h);
        for (int i = h.length / 2 - 1; i >= 0; i--) {
            //h[0,length) 建成大顶堆
            LogUtils.d("筛选 %d,%d", i, h[i]);
            heapAdjust(h, i, h.length - 1);
            printListAsHeap(h);
        }
        for (int i = h.length - 1; i > 0; i--) {
            //堆顶与最后一个记录交换
            int t = h[0];
            h[0] = h[i];
            h[i] = t;
            LogUtils.d("输出 %d 到 %d", t, i);
            printListAsHeap(h);
            //将 [0,i-1] 重新调整为大顶堆
            heapAdjust(h, 0, i - 1);
        }
    }

    /**
     * 以堆（二叉树）的形式输出数组
     */
    static void printListAsHeap(int[] list) {
        LogUtils.d(BiTree.buildByArray(list));
    }
}
