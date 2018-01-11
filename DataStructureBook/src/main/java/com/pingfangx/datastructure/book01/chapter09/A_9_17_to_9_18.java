package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2018/1/16
 */
public class A_9_17_to_9_18 {
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;
    private static final int DUPLICATE = -1;

    int c;
    int p;
    int[] hashSize = new int[]{997};

    class HashTable {
        ElemType[] elem;
        int count;
        int sizeindex;
    }

    /**
     * 9.17
     */
    int searchHash(HashTable h, KeyType k) {
        p = hash(k);
        while (h.elem[p].key != null && !k.EQ(h.elem[p].key)) {
            p = collision(++c);
        }
        if (k.EQ(h.elem[p].key)) {
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    private int collision(int i) {
        return 0;
    }

    int hash(KeyType k) {
        return 0;
    }

    /**
     * 9.18
     */
    int insertHash(HashTable h, ElemType e) {
        c = 0;
        if (searchHash(h, e.key) == SUCCESS) {
            return DUPLICATE;
        } else if (c < hashSize[h.sizeindex] / 2) {
            h.elem[p] = e;
            h.count++;
            return SUCCESS;
        } else {
            recreateHashTable(h);
            return FAIL;
        }
    }

    private void recreateHashTable(HashTable h) {

    }
}
