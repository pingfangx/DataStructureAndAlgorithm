package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.List;

/**
 * todo 可查看 http://blog.csdn.net/hackbuteer1/article/details/6657109
 *
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_16 {
    public void trial(int i, int n) {
        if (i > n) {
            //输出
        } else {
            for (int j = 1; j <= n; j++) {
                //在 i 行 j 列放置一个棋子
                if (isLegal()) {
                    trial(i + 1, n);
                }
                //移走 i 行 j 列的棋子
            }
        }
    }

    private boolean isLegal() {
        return false;
    }

    public static void trial(List<List<Integer>> a, int i, int n) {
        if (i >= n) {
            log1("成功结果");
            printList(a);
        } else {
            for (int j = 0; j < n; j++) {
                a.get(i).set(j, 1);
                if (isLegal(a)) {
                    trial(a, i + 1, n);
                }
                a.get(i).set(j, 0);
            }
        }
    }

    private static boolean isLegal(List<List<Integer>> a) {
        log2("\n检查前");
        if (level >= 2) {
            printList(a);
        }
        int size = a.size();
        //最外面这一层是0-3，但是表示的意义不一致，由内部的 j 决定
        for (int i = 0; i < size; i++) {
            //每一行
            log3("检查第 %d 行", i);
            int sum = 0;
            for (int j = 0; j < size; j++) {
                log3("检查 %d,%d", i, j);
                sum += a.get(i).get(j);
            }
            if (sum > 1) {
                log2("第 %d 行有重复", i);
                return false;
            }
            //每一列
            log3("检查第 %d 列", i);
            sum = 0;
            for (int j = 0; j < size; j++) {
                log3("检查 %d,%d", j, i);
                sum += a.get(j).get(i);
            }
            if (sum > 1) {
                log2("第 %d 列有重复", i);
                return false;
            }
            //对角线
            //以第一行为起点，右下
            log3("检第一行，%d 位置,右下", i);
            sum = 0;
            for (int j = 0; j < size; j++) {
                int x = i + j;
                int y = j;
                if (x < size && y < size && x >= 0 && y >= 0) {
                    log3("检查 %d,%d", x, y);
                    sum += a.get(x).get(y);
                }
            }
            if (sum > 1) {
                log2("第一行，%d 位置，右下斜线重复", i);
                return false;
            }
            //对角线
            //以第一列为起点，右下
            log3("检查第一列, %d 位置,右下", i);
            sum = 0;
            for (int j = 0; j < size; j++) {
                int x = j;
                int y = i + j;
                if (x < size && y < size && x >= 0 && y >= 0) {
                    log3("检查 %d,%d", x, y);
                    sum += a.get(x).get(y);
                }
            }
            if (sum > 1) {
                log2("第一列，%d 位置，右下斜线重复", i);
                return false;
            }
            //对角线
            //以第一行为起点，左下
            log3("检查第一行, %d 位置,左下", i);
            sum = 0;
            for (int j = 0; j < size; j++) {
                int x = i - j;
                int y = j;
                if (x < size && y < size && x >= 0 && y >= 0) {
                    log3("检查 %d,%d", x, y);
                    sum += a.get(x).get(y);
                }
            }
            if (sum > 1) {
                log2("第一行，%d 位置，左下斜线重复", i);
                return false;
            }
            //对角线
            //以最后一列为起点，左下
            log3("检查第 %d 列,%d 位置,左下", size - 1, i);
            sum = 0;
            for (int j = 0; j < size; j++) {
                int x = size - 1 - j;
                int y = i + j;
                if (x < size && y < size && x >= 0 && y >= 0) {
                    log3("检查 %d,%d", x, y);
                    sum += a.get(x).get(y);
                }
            }
            if (sum > 1) {
                log2("最后一列，%d 位置，左下斜线重复", i);
                return false;
            }
        }
        log2("检查成功");
        return true;
    }

    private static int successCount;

    private static void printList(List<List<Integer>> a) {
        LogUtils.d("第 %d 种解法", ++successCount);
        for (List<Integer> integers : a) {
            LogUtils.d(integers);
        }
    }

    private static int level = 1;

    public static void log1(String format, Object... args) {
        if (level >= 1) {
            LogUtils.d(String.format(format, args));
        }
    }

    public static void log2(String format, Object... args) {
        if (level >= 2) {
            LogUtils.d(String.format(format, args));
        }
    }

    public static void log3(String format, Object... args) {
        if (level >= 3) {
            LogUtils.d(String.format(format, args));
        }
    }
}
