package com.pingfangx.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2018/1/31
 */
public class IterableTest {
    @Test
    public void testForEach() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.forEach(System.out::println);
    }
}
