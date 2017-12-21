package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_12_to_6_13 {
    public static class HuffmanTree {
        int weight;
        int parent;
        int lchild;
        int rchild;

        public HuffmanTree(int weight, int parent, int lchild, int rchild) {
            this.weight = weight;
            this.parent = parent;
            this.lchild = lchild;
            this.rchild = rchild;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%d,%d)", weight, parent, lchild, rchild);
        }
    }

    public static class HuffmanCode {
        public char[] chars;

        @Override
        public String toString() {
            return Arrays.toString(chars);
        }
    }

    public static void HuffmanCoding(int[] w, int n) {
        if (n <= 1) {
            return;
        }
        HuffmanTree[] HT = getHuffmanTrees(w, n);
        HuffmanCode[] HC = getHuffmanCodes(HT, n);
        LogUtils.d(HT);
        LogUtils.d(Arrays.toString(HC));
    }

    public static void HuffmanCoding2(int[] w, int n) {
        if (n <= 1) {
            return;
        }
        HuffmanTree[] HT = getHuffmanTrees(w, n);
        HuffmanCode[] HC = getHuffmanCodes2(HT, n);
        LogUtils.d(HT);
        LogUtils.d(Arrays.toString(HC));
    }

    private static HuffmanTree[] getHuffmanTrees(int[] w, int n) {
        //n 个叶子结点的赫夫曼树共有 2n-1 个结点
        int m = 2 * n - 1;
        HuffmanTree[] HT = new HuffmanTree[m + 1];
        int i = 1;
        //原代码中使用了指针++，就是赋值到 HT 中
        while (i <= n) {
            HT[i] = new HuffmanTree(w[i - 1], 0, 0, 0);
            i++;
        }
        while (i <= m) {
            HT[i] = new HuffmanTree(0, 0, 0, 0);
            i++;
        }
        //建树
        for (i = n + 1; i <= m; i++) {
            int[] nodes = select(HT, i - 1);
            int s1 = nodes[0];
            int s2 = nodes[1];
            HT[s1].parent = i;
            HT[s2].parent = i;
            HT[i].lchild = s1;
            HT[i].rchild = s2;
            HT[i].weight = HT[s1].weight + HT[s2].weight;
        }
        return HT;
    }

    /**
     * 6.12
     */
    private static HuffmanCode[] getHuffmanCodes(HuffmanTree[] HT, int n) {
        int i;//求赫夫曼编码
        HuffmanCode[] HC = new HuffmanCode[n + 1];
        char[] cd = new char[n];
        cd[n - 1] = '\0';
        for (i = 1; i <= n; i++) {
            int start = n - 1;
            for (int c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent) {
                if (HT[f].lchild == c) {
                    cd[--start] = '0';
                } else {
                    cd[--start] = '1';
                }
            }
            HC[i] = new HuffmanCode();
            char[] chars = new char[n - start];
            //书中直接调的 strcpy 是因为为空的位置不会复制吗
            System.arraycopy(cd, start, chars, 0, chars.length);
            HC[i].chars = chars;
        }
        cd = null;
        return HC;
    }

    /**
     * 6.13，不太理解
     */
    private static HuffmanCode[] getHuffmanCodes2(HuffmanTree[] HT, int n) {
        //求赫夫曼编码
        HuffmanCode[] HC = new HuffmanCode[n + 1];
        int m = 2 * n - 1;
        int p = m;
        //清空用作标识
        for (int i = 1; i <= m; i++) {
            HT[i].weight = 0;
        }
        char[] cd = new char[n];
        int cdlen = 0;
        while (p > 0) {
            if (HT[p].weight == 0) {
                HT[p].weight = 1;
                if (HT[p].lchild != 0) {
                    p = HT[p].lchild;
                    cd[cdlen++] = '0';
                } else if (HT[p].rchild == 0) {
                    HC[p] = new HuffmanCode();
                    cd[cdlen] = '\0';
                    HC[p].chars = new char[cd.length];
                    System.arraycopy(cd, 0, HC[p].chars, 0, cd.length);
                }
            } else if (HT[p].weight == 1) {
                HT[p].weight = 2;
                if (HT[p].rchild != 0) {
                    p = HT[p].rchild;
                    cd[cdlen++] = '1';
                }
            } else {
                //HT[p].weight==2,回退
                HT[p].weight = 0;
                p = HT[p].parent;
                --cdlen;
            }
        }
        cd = null;
        return HC;
    }

    private static int[] select(HuffmanTree[] ht, int end) {
        int min1 = 0;
        int min2 = 0;
        for (int i = 1; i <= end; i++) {
            HuffmanTree tree = ht[i];
            if (tree.parent == 0) {
                if (min2 == 0 || tree.weight < ht[min2].weight) {
                    min2 = i;
                    if (min1 == 0 || ht[min2].weight < ht[min1].weight) {
                        //交换，保证 min1<min2
                        int t = min1;
                        min1 = min2;
                        min2 = t;
                    }
                }
            }
        }
        //这里不需要交换，只是为了匹配书中的按顺序
        if (min1 > min2) {
            int t = min1;
            min1 = min2;
            min2 = t;
        }
        return new int[]{min1, min2};
    }
}
