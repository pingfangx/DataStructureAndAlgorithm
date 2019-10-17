package com.pingfangx.study.book1.chapter17;

import org.junit.Test;

import java.util.*;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class UnsupportedOperationTest {
    private void test_operate(List<String> list) {
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        Collection<String> c2 = new ArrayList<>(subList);
        try {
            c.retainAll(c2);
            System.out.println("retainAll " + "成功");
        } catch (Exception e) {
            System.out.println("retainAll " + e);
        }
        try {
            c.removeAll(c2);
            System.out.println("removeAll " + "成功");
        } catch (Exception e) {
            System.out.println("removeAll " + e);
        }
        try {
            c.clear();
            System.out.println("clear " + "成功");
        } catch (Exception e) {
            System.out.println("clear " + e);
        }
        try {
            c.add("X");
            System.out.println("add " + "成功");
        } catch (Exception e) {
            System.out.println("add " + e);
        }
        try {
            c.addAll(c2);
            System.out.println("addAll " + "成功");
        } catch (Exception e) {
            System.out.println("addAll " + e);
        }
        try {
            c.remove("B");
            System.out.println("remove " + "成功");
        } catch (Exception e) {
            System.out.println("remove " + e);
        }
        try {
            list.set(0, "X");
            System.out.println("list.set " + "成功");
        } catch (Exception e) {
            System.out.println("list.set " + e);
        }
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("A B C D E F G H I J K L M N ".split(" "));
        System.out.println("可修改的复制");
        test_operate(new ArrayList<>(list));
        System.out.println("Arrays.asList()");
        test_operate(list);
        System.out.println("unmodifiableList");
        test_operate(Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
