package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.List;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_14_to_6_15 {
    public void PowerSet(int i, int n) {
        if (i > n) {
            //输出
        } else {
            //取第 i 个
            PowerSet(i + 1, n);
            //舍第 i 个
            PowerSet(i + 1, n);
        }
    }

    /**
     * 6.15
     * 每一个元素，可能属于集合，也可能不属于
     */
    public static void getPowerSet(int i, List<Integer> a, List<Integer> b) {
        if (i >= a.size()) {
            LogUtils.d(b);
        } else {
            int x = a.get(i);
            int k = b.size();
            //属于，添加
            b.add(k, x);
            getPowerSet(i + 1, a, b);
            //不属于，再将其删除
            b.remove(k);
            getPowerSet(i + 1, a, b);
        }
    }

}
