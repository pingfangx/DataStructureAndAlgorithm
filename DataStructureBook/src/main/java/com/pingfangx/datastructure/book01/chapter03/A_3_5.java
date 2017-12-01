package com.pingfangx.datastructure.book01.chapter03;


import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2017/11/9
 */
public class A_3_5 {
    private int mSize;
    /**
     * 塔列表
     * 每个塔的操作类似 stack，但是显示的时候要获取每一个元素，stack 不适用，因此还是用 list
     */
    private List<List<Integer>> mList;

    private A_3_5(int size) {
        this.mSize = size;
    }

    public static void main(String[] args) {
        new A_3_5(5).start();
    }

    private void start() {
        mList = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 0) {
                for (int j = mSize; j > 0; j--) {
                    list.add(j);
                }
            }
            mList.add(list);
        }
        printHanoi();
        hanoi(mSize, 0, 1, 2);
    }

    /**
     * 将 n 个盘从 x 移到 y，以 z 为中转
     */
    private void hanoi(int n, int x, int y, int z) {
        if (n == 1) {
            move(x, y, n);
        } else {
            //将 n-1 个移到中转
            hanoi(n - 1, x, z, y);
            move(x, y, n);
            //将 n-1 个由中转移到y
            hanoi(n - 1, z, y, x);
        }
    }

    /**
     * 从第 n 个从 x 移到 y
     */
    private void move(int x, int y, int n) {

        //将 x 中最后一个移掉，添加到 y
        List<Integer> listX = mList.get(x);
        int index = listX.size() - 1;
        mList.get(y).add(listX.get(index));
        listX.remove(index);

        printHanoi();
        LogUtils.d("move %d from %d to %d", n, x + 1, y + 1);
    }

    private void printHanoi() {
        printHanoi(mSize, mList);
    }

    private void printHanoi(int size, List<List<Integer>> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            //每一行
            for (int j = 0; j < 3; j++) {
                //每个塔
                List<Integer> tower = list.get(j);
                if (tower.size() > i) {
                    //有数据
                    stringBuilder.append(getTowerLevel(size, tower.get(i)));
                } else {
                    //无数据
                    stringBuilder.append(getTowerLevel(size, 0));
                }
            }
            stringBuilder.append('\n');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }

    /**
     * 获取一层的样式
     */
    private String getTowerLevel(int size, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        //12**21
        for (int i = 0; i < size * 2; i++) {
            if (i >= size - n && i < size + n) {
                stringBuilder.append('*');
            } else {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
