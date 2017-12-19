package com.pingfangx.datastructure.common.structure;

/**
 * 元素类型
 *
 * @author pingfangx
 * @date 2017/12/18
 */
public class ElemType {
    public int value;

    public ElemType(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
