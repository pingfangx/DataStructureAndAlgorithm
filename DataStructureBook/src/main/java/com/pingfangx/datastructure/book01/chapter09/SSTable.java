package com.pingfangx.datastructure.book01.chapter09;

import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
class SSTable {
    /**
     * 元素，0 号单元留空
     */
    ElemType[] elem;
    int length;

    public static SSTable create(int[] data) {
        if (data == null) {
            return null;
        }
        SSTable table = new SSTable();
        table.length = data.length;
        table.elem = new ElemType[table.length + 1];
        table.elem[0] = new ElemType(0);
        for (int i = 0; i < data.length; i++) {
            table.elem[i + 1] = new ElemType(data[i]);
        }
        return table;
    }

    @Override
    public String toString() {
        return "length=" + length + "\nelem=" + Arrays.toString(elem);
    }
}
