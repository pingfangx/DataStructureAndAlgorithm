package com.pingfangx.datastructure.book01.chapter02;

import java.util.ArrayList;

/**
 * 不同之处只在于限定为 Integer
 * 使用 add() 方法时，必须指定 index
 *
 * @author pingfangx
 * @date 2017/11/3
 */
public class List {
    private java.util.List<Integer> mList;

    public List() {
        mList = new ArrayList<>();
    }

    public void add(int index, int element) {
        mList.add(index, element);
    }

    public void remove(int index) {
        mList.remove(index);
    }

    public int get(int index) {
        return mList.get(index);
    }

    public void set(int index, int element) {
        if (index == mList.size()) {
            mList.add(index, element);
        } else {
            mList.set(index, element);
        }
    }

    public int length() {
        return mList.size();
    }

    public boolean contains(Integer element) {
        return mList.contains(element);
    }

    @Override
    public String toString() {
        return mList.toString();
    }
}
