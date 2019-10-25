package com.pingfangx.study.book1.chapter05;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class OverloadingVarargsTest {
    public void test(int i, Character... args) {
        System.out.println(i);
        System.out.println(Arrays.toString(args));
    }

    public void test(Character... args) {
        System.out.println(Arrays.toString(args));
    }

    @Test
    public void test() {
        /*
        编译错误，需要两者都带有非可变参数，或者把 int 改为 String
        因为 char 可以转为 int，所以 test('a', 'b') 两个方法都匹配，无法确定
         */
        test(1, 'a');
//        test('a', 'b');
    }

}
