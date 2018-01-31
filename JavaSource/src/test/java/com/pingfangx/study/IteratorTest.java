package com.pingfangx.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author pingfangx
 * @date 2018/1/31
 */
public class IteratorTest {

    @Test
    public void testRemove() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.forEach(System.out::println);


        System.out.println("after remove");
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        list.forEach(System.out::println);

        //出错，因为没有要 remove 的对象
//        System.out.println("after remove");
//        list.iterator().next();
//        list.iterator().remove();
//        list.forEach(System.out::println);
    }

    @Test
    public void testForEachRemaining() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.iterator().forEachRemaining(System.out::println);

        System.out.println();
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        //先调用了 next()，迭代剩余的
        iterator.forEachRemaining(System.out::println);
    }
}
