package com.pingfangx.datastructure.book01.chapter10;

import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2018/1/26
 */
public class A_10_15_to_10_17 {
    private static final int MAX_NUM_OF_KEY = 8;
    private static final int RADIX = 10;
    private static final int MAX_SPACE = 10000;

    static class SLCell {
        int data;
        int[] keys = new int[MAX_NUM_OF_KEY];
        Object otherItems;
        int next;

        public SLCell(int data) {
            this.data = data;
            for (int i = 0; i < MAX_NUM_OF_KEY && data != 0; i++) {
                keys[i] = data % 10;
                data /= 10;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    static class SLList {
        SLCell[] r = new SLCell[MAX_SPACE];
        /**
         * 关键字个数
         */
        int keyNum;
        /**
         * 长度
         */
        int recNum;

        static SLList create(int[] data) {
            SLList list = new SLList();
            int maxKeyNum = 0;
            list.r[0] = new SLCell(0);
            for (int i = 0; i < data.length; i++) {
                list.r[i + 1] = new SLCell(data[i]);
                if (maxKeyNum < list.r[i + 1].keys.length) {
                    maxKeyNum = list.r[i + 1].keys.length;
                }
            }
            list.keyNum = maxKeyNum;
            list.recNum = data.length;
            return list;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            int next = r[0].next;
            while (next != 0) {
                stringBuilder.append('-');
                stringBuilder.append('>');
                stringBuilder.append(r[next]);
                next = r[next].next;
            }
            return stringBuilder.toString();
        }
    }

    /**
     * 10.15
     * 分配
     */
    void distribute(SLCell[] r, int i, int[] f, int[] e) {

        for (int j = 0; j < RADIX; j++) {
            f[j] = 0;
        }

        for (int p = r[0].next; p != 0; p = r[p].next) {
            int j = ord(r[p].keys[i]);
            //将 p 所指的结点插入到第 j 个子表中
            //f[j] 指向头，e[j] 指向尾
            if (f[j] == 0) {
                f[j] = p;
            } else {
                r[e[j]].next = p;
            }
            e[j] = p;
        }
    }

    private int ord(int key) {
        return key;
    }

    /**
     * 10.16
     * 收集
     */
    void collect(SLCell[] r, int i, int[] f, int[] e) {
        //找出第一个非空子表
        int j = 0;
        while (f[j] == 0) {
            j = succ(j);
        }
        //指向非空子表第一个结点
        r[0].next = f[j];
        int t = e[j];
        while (j < RADIX) {
            //找下一下非空子表
            j = succ(j);
            while (j < RADIX - 1 && f[j] == 0) {
                j = succ(j);
            }
            if (f[j] != 0) {
                //链接
                r[t].next = f[j];
                t = e[j];
            }
        }
        r[t].next = 0;
    }

    private int succ(int j) {
        return j + 1;
    }

    /**
     * 10.17
     * 基数排序
     */
    void radixSort(SLList list) {
        //改造成静态链表
        for (int i = 0; i < list.recNum; i++) {
            list.r[i].next = i + 1;
        }
        list.r[list.recNum].next = 0;
        //LSD(Least Significant Digit first) 最低位优先
        int[] f = new int[RADIX + 1];
        int[] e = new int[RADIX + 1];
        LogUtils.d(list);
        for (int i = 0; i < list.keyNum; i++) {
            distribute(list.r, i, f, e);
            collect(list.r, i, f, e);
            LogUtils.d(list);
        }
    }

}
