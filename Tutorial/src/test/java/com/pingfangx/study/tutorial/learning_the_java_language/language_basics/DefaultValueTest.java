package com.pingfangx.study.tutorial.learning_the_java_language.language_basics;

/**
 * @author pingfangx
 * @date 2019/9/20
 */
public class DefaultValueTest {
    byte b;
    short s;
    int i;
    long l;
    float f;
    double d;
    boolean bool;
    char c;
    String string;
    int[] iArray;

    public static void main(String[] args) {
        new DefaultValueTest().main();
    }

    private void main() {
        System.out.println("默认值");
        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(bool);
        System.out.println(c);
        System.out.println(string);
        System.out.println(iArray);

        //不同类型的后缀
        System.out.println("不同后缀");
        System.out.println(0);
        System.out.println(0L);
        System.out.println(0F);
        System.out.println(0D);

        //不同进制的整型
        System.out.println("不同前缀");
        System.out.println(0b1);
        System.out.println(1);
        System.out.println(0xA);

        System.out.println(1.234E2);
    }
}
