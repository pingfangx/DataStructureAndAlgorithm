package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
class ElemType {
    KeyType key;

    public ElemType(int i) {
        key = new KeyType(i);
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
