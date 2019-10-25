package com.pingfangx.study.book1.chapter03;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/17
 */
public class IfVariableTest {
    @Test
    public void test() {
        int i, j = 0;
        boolean a, b = true;
        //Incompatible types.
        //Required:
        //boolean
        //Found:
        //int
        //if (i = j) {
        //    System.out.println("i=j");
        //}
        if (a = b) {
            System.out.println("a=b");
        }
    }
}
