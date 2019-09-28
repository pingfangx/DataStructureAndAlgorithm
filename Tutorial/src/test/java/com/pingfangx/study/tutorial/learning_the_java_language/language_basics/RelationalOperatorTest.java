package com.pingfangx.study.tutorial.learning_the_java_language.language_basics;

/**
 * 大于 -1 和 大于等于 0 是否一样的性能
 *
 * @author pingfangx
 * @date 2019/9/28
 */
public class RelationalOperatorTest {
    private int test() {
        return 0;
    }

    public void test1() {
        int i = test();
        if (i > -1) {
            i = 9;
        }
    }

    public void test2() {
        int i = test();
        if (i >= 0) {
            i = 9;
        }
    }
}
