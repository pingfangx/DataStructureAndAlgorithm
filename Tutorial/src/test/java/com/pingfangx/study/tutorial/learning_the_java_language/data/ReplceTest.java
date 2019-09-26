package com.pingfangx.study.tutorial.learning_the_java_language.data;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class ReplceTest {
    /**
     * 测试 java.lang.String#replace(char, char) 方法中的 avoid getfield opcode
     */
    private class TestClass {
        private char value[];

        private void print() {
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }
        }

        private void printWithVariable() {
            char[] val = value;
            for (int i = 0; i < val.length; i++) {
                System.out.println(val[i]);
            }
        }
    }
}
