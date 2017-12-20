package com.pingfangx.datastructure.book01.chapter05;

/**
 * 三元组
 *
 * @author pingfangx
 * @date 2017/12/18
 */
public class Triple {
    /**
     * 行下标
     */
    public int i;
    /**
     * 列下标
     */
    public int j;
    public ElemType e;

    public static Triple create(int[] data) {
        if (data == null || data.length < 3) {
            return null;
        }
        Triple triple = new Triple();
        triple.i = data[0];
        triple.j = data[1];
        triple.e = new ElemType(data[2]);
        return triple;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%s)", i, j, e);
    }
}
