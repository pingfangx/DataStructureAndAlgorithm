package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
class KeyType {
    public int key;

    public KeyType(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    public boolean EQ(KeyType keyType) {
        return toString().compareTo(keyType.toString()) == 0;
    }

    public boolean LT(KeyType keyType) {
        return toString().compareTo(keyType.toString()) < 0;
    }

    public boolean GT(KeyType keyType) {
        return toString().compareTo(keyType.toString()) > 0;
    }
}
