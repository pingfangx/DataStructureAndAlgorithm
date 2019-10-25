package com.pingfangx.study.tutorial.basic.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class RemoveAllTest {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
        }
        System.out.println(list);
        list.removeAll(Arrays.asList(1, 2));
        System.out.println(list);
    }
}
