package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class A_10_6_to_10_8 {
    /**
     * 冒泡排序
     * 最好情况，要比较 n-1 次
     * 最坏情况，要进行 n-1 趟
     * 时间复杂度 O(n^2)
     */
    void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int t = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = t;
                }
            }
        }
    }

    /**
     * 10.6.a
     */
    int partitionA(int[] list, int low, int high) {
        //取第一个记录作枢轴
        int pivotKey = list[low];
        while (low < high) {
            while (low < high && list[high] >= pivotKey) {
                high--;
            }
            int t = list[low];
            list[low] = list[high];
            list[high] = t;
            while (low < high && list[low] < pivotKey) {
                low++;
            }
            t = list[low];
            list[low] = list[high];
            list[high] = t;
        }
        return low;
    }

    /**
     * 10.6 b
     */
    int partitionB(int[] list, int low, int high) {
        //取第一个记录作枢轴
        int temp = list[low];
        int pivotKey = list[low];
        while (low < high) {
            while (low < high && list[high] >= pivotKey) {
                high--;
            }
            list[low] = list[high];
            while (low < high && list[low] < pivotKey) {
                low++;
            }
            list[high] = list[low];
        }
        list[low] = temp;
        return low;
    }

    /**
     * 10.7
     */
    void qSort(int[] list, int low, int high) {
        if (low < high) {
            //一分为二
            int pivotLoc = partitionB(list, low, high);
            //排序低子表
            qSort(list, low, pivotLoc - 1);
            //排序高子表
            qSort(list, pivotLoc + 1, high);
        }
    }

    /**
     * 10.8
     */
    void quickSort(int[] list) {
        qSort(list, 0, list.length - 1);
    }
}
