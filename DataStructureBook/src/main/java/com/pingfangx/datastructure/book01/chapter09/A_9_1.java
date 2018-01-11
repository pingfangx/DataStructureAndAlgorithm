package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_1 {


    /**
     * 平均查找长度
     * ASL=∑P(i)C(i)|i=[1,n]
     * =1/n∑(n-i+1)
     * 1/n*(n*(n-1+1+n-n+1)/2)
     * 1/n*(n*(n+1)/2)
     * (n+1)/2
     * <p>
     * 如果还带有不成功的，则为
     * =1/2n∑(n-i+1)+1/2(n+1)
     * =3/4(n+1)
     */
    public static int search_seq(SSTable st, KeyType key) {
        //哨兵
        st.elem[0].key = key;
        int i = st.length;
        //如果都不相等， i 到 0 的时候肯定会相等
        //这样就少了一个 i>0 的判断
        while (!st.elem[i].key.EQ(key)) {
            i--;
        }
        return i;
    }
}
