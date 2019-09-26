package com.pingfangx.study.tutorial.learning_the_java_language.generics;

import java.io.Serializable;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/1/7
 */
public class UpperBoundsTest {
    private void test(List<? extends Number> list) {
    }

    private void testInterface(List<? extends Serializable> list) {
    }
}
