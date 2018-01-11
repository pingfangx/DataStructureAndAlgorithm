package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_2 {
    /**
     * 在判定树中，虽然不是完全二叉树，但是深度一致（原文说但它的叶子所在层次之差最多为 1）
     * Math.floor(log2(n))+1
     * ASL=∑P(i)C(i) i=[1,n]
     * =1/n(∑C(i)) 概率为 1/n
     * =1/n(∑j*2^(j-1))  j=[1,h]，第 j 层有 2^(j-1) 个结点，每个结点要比较 j 次
     * =1/n(1*2^0+2*2^1+...+h*2^(h-1))
     * =1/n(∑2^i(i=[0,h-1])+2*∑2^i(i=[0,h-2])+...+2^k-1(∑2*i()))
     * 上一步因为每个，1*，2*，一直到 h*
     * 首先提出一个等比数列，到2^(h-1)，然后提出 2，剩下的又是一个等比数列，只不过到 2^(h-2)
     * =1/n[h*2^h-(2^0+2^1+...+2^(h-1))]
     * 上一步是因为每一个求和，可以看作 2^h 减去一个值，最终就是 h*2^h-
     * =1/n[h*2^h-(2^h-1)]
     * =1/n[(h-1)*2^k+1]
     * 而 h=log2(n+1)，于是
     * =1/n[(log2(n+1)-1)*(n+1)+1]
     * =1/n[(log2(n+1)-1)*(n+1)]+1/n
     * =(n+1)/n*(log2(n+1)-1)+1/n
     * =(n+1)/n*log2(n+1)-n+1/n+1/n
     * =(n+1)/n*log2(n+1)-1
     * 当 n 较大时(n>50)
     * log2(n+1)-1
     */
    public static int search_bin(SSTable st, KeyType keyType) {
        int low = 1;
        int high = st.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (keyType.EQ(st.elem[mid].key)) {
                return mid;
            } else if (keyType.LT(st.elem[mid].key)) {
                //小于
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }
}
