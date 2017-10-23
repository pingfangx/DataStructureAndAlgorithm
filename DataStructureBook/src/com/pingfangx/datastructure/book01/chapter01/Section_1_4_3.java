package com.pingfangx.datastructure.book01.chapter01;

/**
 * @author pingfangx
 * @date 2017/10/23
 */
public class Section_1_4_3 {
    public static void main(String[] args) {
        int[] a = new int[]{5, 4, 3, 2, 1};
        bubble_sort(a, a.length);
        for (int i : a) {
            System.out.println(i);
        }
    }

    /**
     * 书中说
     * 语句 ++x 的执行次数关于 n 的增长率为 n^2，它是语句频度表达式 (n-1)(n-2)/2 中增长最快的项
     * 我的问题是不知道 (n-1)(n-2)/2 是怎么算出来的，我的数学这么差么……
     * i=2,j<=1,0次
     * i=3,j<=2,1次
     * i=4,j<=3,2次
     * i=5,j<=4,3次
     * (0+n-2)(n-1)/2，就是这么算出来的
     */
    private static void method01(int[][] a, int n) {
        int x = 0;
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= i - 1; ++j) {
                ++x;
                a[i][j] = x;
            }
        }
    }

    /**
     * i 从后往前推,=1表示的是第2项
     * j 从 0 到 i 依次判断，如果没有交换，说明已经顺序
     */
    private static void bubble_sort(int[] a, int n) {
        boolean change = true;
        for (int i = n - 1; i >= 1 && change; --i) {
            change = false;
            for (int j = 0; j < i; ++j) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    change = true;
                }
            }
        }
    }
}
