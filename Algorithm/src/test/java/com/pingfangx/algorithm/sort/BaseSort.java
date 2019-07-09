package com.pingfangx.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序
 * 相关方法 Python 已经实现了一遍
 * 再来一遍是循环等语言特性不一样，需要注意
 *
 * @author pingfangx
 * @date 2019/7/9
 */
public abstract class BaseSort {
    @Test
    public void test_sort() {
        int[] source = generateNumbers();
        int[] result = sort(Arrays.copyOf(source, source.length));

        int[] expected = Arrays.stream(source)
                .sorted()
                .toArray();
        Assert.assertArrayEquals(expected, result);
    }

    private int[] generateNumbers() {
        return new int[]{5, 4, 3, 2, 1};
    }

    protected abstract int[] sort(int[] array);
}
