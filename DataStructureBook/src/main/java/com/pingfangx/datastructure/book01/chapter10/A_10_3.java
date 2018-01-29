package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class A_10_3 {
    void arrange(SLinkListType SL) {
        //p 指示要调整的位置
        int p = SL.r[0].next;
        for (int i = 1; i < SL.length; i++) {
            while (p < i) {
                p = SL.r[p].next;

            }
            int q = SL.r[p].next;
            if (p != i) {
                //交换，证第 i 条记录到位
                SLinkListType.SLNode t = SL.r[p];
                SL.r[p] = SL.r[i];
                SL.r[i] = t;
                SL.r[i].next = p;//指向被移走的记录，可以由上方的 while 找回
            }
            p = q;
        }
    }
}
