package com.pingfangx.study.tutorial.basic.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class CollectionEqualsTest {
    @Test
    public void test() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(10 - 1 - i);
        }
        System.out.println(list1);
        System.out.println(list2);
        //list 不相等
        Assert.assertNotEquals(list1, list2);

        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        System.out.println(set1);
        System.out.println(set2);
        //set 相等
        Assert.assertEquals(set1, set2);
    }
}
