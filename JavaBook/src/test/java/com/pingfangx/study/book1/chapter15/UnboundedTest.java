package com.pingfangx.study.book1.chapter15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class UnboundedTest {
    void assign1(List list) {
    }

    void assign2(List<?> list) {
    }

    void assign3(List<? extends Object> list) {
    }

    @Test
    public void test() {
        assign1(new ArrayList());
        assign2(new ArrayList<>());
        assign3(new ArrayList<>());
        assign2(new ArrayList());
        //Unchecked assignment: 'java.util.ArrayList' to 'java.util.List<? extends java.lang.Object>'
        assign3(new ArrayList());
        List<?> wildList = new ArrayList<>();
        wildList = new ArrayList();
        wildList = new ArrayList<String>();
        assign1(wildList);
        assign2(wildList);
        assign3(wildList);
    }
}
