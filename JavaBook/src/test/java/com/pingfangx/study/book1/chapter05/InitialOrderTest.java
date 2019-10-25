package com.pingfangx.study.book1.chapter05;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class InitialOrderTest {
    static class Info {
        public Info(String text) {
            System.out.println(text);
        }
    }

    static class ParentClass {
        Info info = new Info("parent field");
        static Info sInfo = new Info("parent static field");

        public ParentClass() {
            Info info = new Info("parent constructor");
        }

        static {
            Info sInfo = new Info("parent static initial block");
        }

        {
            Info info = new Info("parent initial block");
        }

    }

    static class ChildClass extends ParentClass {
        Info info = new Info("child field");
        static Info sInfo = new Info("child static field");

        public ChildClass() {
            Info info = new Info("child constructor");
        }

        static {
            Info sInfo = new Info("child static initial block");
        }

        {
            Info info = new Info("child initial block");
        }

        static class InnerClass {
            static Info sInfo = new Info("child inner ");
        }
    }

    /**
     * 先初始化父类的 static
     * parent static field
     * 静态字段和静态化按源文件的声明顺序
     * parent static initial block
     * 再子类的静态字段
     * child static field
     * child static initial block
     * 父类的普通字段
     * parent field
     * parent initial block
     * 父类构造函数
     * parent constructor
     * 子类的普通字段
     * child field
     * child initial block
     * 子类构造函数
     * child constructor
     * 静态类只有使用时才初始化
     * child inner
     */
    @Test
    public void test() {
        new ChildClass();
        new ChildClass.InnerClass();
    }
}
