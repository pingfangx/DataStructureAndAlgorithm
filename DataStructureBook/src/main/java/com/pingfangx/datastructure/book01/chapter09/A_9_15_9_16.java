package com.pingfangx.datastructure.book01.chapter09;

/**
 * @author pingfangx
 * @date 2017/12/29
 */
public class A_9_15_9_16 {
    private static final int MAX_KEY_LEN = 16;

    class KeyType {
        char ch[] = new char[MAX_KEY_LEN];
        int num;
    }

    enum NodeKind {
        LEAF, BRANCH;
    }

    class Record {
    }

    class DLTree {
        char symbol;
        DLTree next;
        NodeKind kind;
        Record infoptr;
        DLTree first;
    }


    /**
     * 9.15
     */
    Record searchDLTree(DLTree t, KeyType k) {
        DLTree p = t.first;
        int i = 0;
        while (p != null && i < k.num) {
            while (p != null && p.symbol != k.ch[i]) {
                p = p.next;
            }
            if (p != null && i < k.num - 1) {
                p = p.first;
            }
            i++;
        }
        if (p == null) {
            return null;
        } else {
            return p.infoptr;
        }
    }

    class Lf {
        KeyType k;
        Record infoptr;
    }

    class Bh {
        TrieTree[] ptr;
        int num;
    }

    class TrieTree {
        NodeKind kind;
        Lf lf;
        Bh bh;
    }

    /**
     * 9.16
     */
    Record searchTrie(TrieTree t, KeyType k) {
        TrieTree p = t;
        int i = 0;
        while (p != null && p.kind == NodeKind.BRANCH && i < k.num) {
            p = p.bh.ptr[ord(k.ch[i])];
            i++;
        }
        if (p != null && p.kind == NodeKind.LEAF && p.lf.k == k) {
            return p.lf.infoptr;
        } else {
            return null;
        }
    }

    private int ord(char ch) {
        return 0;
    }
}
