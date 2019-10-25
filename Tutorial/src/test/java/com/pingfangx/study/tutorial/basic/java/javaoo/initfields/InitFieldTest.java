package com.pingfangx.study.tutorial.basic.java.javaoo.initfields;

/**
 * @author pingfangx
 * @date 2019/10/23
 */
public class InitFieldTest {
    static int staticField1 = 1;
    static int staticField2 = 2;

    static {
        staticField1 = 1;
        int staticField3 = 3;
    }

    int field4 = 4;
    int field5 = 5;

    {
        field4 = 4;
        int field6 = 6;
    }

    public InitFieldTest(int field4) {
        this.field4 = field4;
    }

    public InitFieldTest(int field4, int field5) {
        this.field4 = field4;
        this.field5 = field5;
    }
}
