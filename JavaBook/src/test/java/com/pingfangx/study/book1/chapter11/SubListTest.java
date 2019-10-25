package com.pingfangx.study.book1.chapter11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class SubListTest {
    /**
     * 是修改的同一数据，修改对彼此可见
     */
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        List<Integer> subList = list.subList(1, 5);
        System.out.println(subList);

        Collections.shuffle(subList);
        System.out.println(subList);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(subList);
        System.out.println(list);
    }
}
