package com.pingfangx.study.tutorial.specialized.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/5/27
 */
public class GenericTest {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        System.out.println(l1.getClass());
        System.out.println(l2.getClass());
        System.out.println(l1.getClass() == l2.getClass());
    }
}
