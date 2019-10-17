package com.pingfangx.study.book1.chapter04;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/17
 */
public class ForInitialTest {
    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
        }
        for (char i = 0, j = 1; i < 10; i++) {
        }
        //错误,不能
        //for (int i = 0,int j=1; i < 10; i++) {
        //}
        //错误
        //for (int i = 0,char j=1; i < 10; i++) {
        //}
    }
}
