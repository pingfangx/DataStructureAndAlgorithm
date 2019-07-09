package com.pingfangx.algorithm.sort;

/**
 * @author pingfangx
 * @date 2019/7/9
 */
public class BubbleSort extends BaseSort {
    @Override
    protected int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean swap = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap = true;
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
            if (!swap) {
                return array;
            }
        }
        return array;
    }
}
