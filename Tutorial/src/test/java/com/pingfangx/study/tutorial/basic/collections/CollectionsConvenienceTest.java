package com.pingfangx.study.tutorial.basic.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 例利实现
 *
 * @author pingfangx
 * @date 2019/10/21
 */
public class CollectionsConvenienceTest {
    @Test
    public void test_nCopies() {
        List<String> list = Collections.nCopies(10, "test");
        try {
            list.add("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_singleton() {
        List<String> list = Collections.singletonList("test");
        try {
            list.add("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
