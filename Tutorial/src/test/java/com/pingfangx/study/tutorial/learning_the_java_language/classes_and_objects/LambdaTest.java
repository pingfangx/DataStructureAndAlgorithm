package com.pingfangx.study.tutorial.learning_the_java_language.classes_and_objects;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class LambdaTest {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list
                .forEach(System.out::println);
        list.forEach(i -> System.out.println(i));
    }
}
