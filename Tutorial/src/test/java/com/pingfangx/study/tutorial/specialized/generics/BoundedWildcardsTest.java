package com.pingfangx.study.tutorial.specialized.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 边界符测试
 *
 * @author pingfangx
 * @date 2019/9/29
 */
public class BoundedWildcardsTest {
    class A {
    }

    class B extends A {
    }

    class C extends B {
    }

    class D extends C {
    }

    class UpperBList<E extends B> extends ArrayList<E> {
    }
    //有界类型形参，只有上界和多重边界，没有下界
    //class LowerBList<E super B>{}

    /**
     * 上界是只读的，读出来肯定是 B
     * 但是不能写，因为不知道 ? 具体代表什么，它可能是 B 的子类，所以不能写入 B
     */
    public void readFromUpperBoundedList(List<? extends B> list) {
        B b = list.get(0);
        //list.add(new B());
    }

    public void test_upperBounded() {
        List<C> cList = new ArrayList<>();
        readFromUpperBoundedList(cList);

        List<? extends B> list = new ArrayList<>();
        readFromUpperBoundedList(list);

        List<A> aList = new ArrayList<>();
        //readFromUpperBoundedList(aList);
    }

    /**
     * 下界是只写的，? 肯定是 B 的父类，所以可以写入
     * 但是不能读，因为不知道 ? 具体代表什么，它可能是 B 的父类，读出来不能执行 B 的方法
     */
    public void writeToLowerBoundedList(List<? super B> list) {
        list.add(new B());
        //B b=list.get(0);
    }

    public void test_lowerBounded() {
        List<A> aList = new ArrayList<>();
        writeToLowerBoundedList(aList);

        List<? super B> list = new ArrayList<>();
        writeToLowerBoundedList(list);

        List<C> cList = new ArrayList<>();
        //writeToLowerBoundedList(cList);
    }


    /**
     * 你可以指定通配符的上界，也可以指定下界，但不能同时指定两者。
     * 就想限制 BC 行不行
     */
    @Test
    public void test_boundedWildcards() {
        //上界是只读的，不可写入
        List<? extends B> upperBoundedList = new ArrayList<>();
        //不用作泛型方法调用
        //writeToLowerBoundedList(upperBoundedList);
        //不用作泛型类实例创建
        //new ArrayList<? extends Number>();
        //下界可以写入
        List<? super C> lowerBoundedList = new ArrayList<>();
        //为什么明明声明的是 ? super C 却只能放入 C 的子类
        //① 回想 List<Number> 可以存入 Integer 因为 Integer 是 Number 的子类
        //② ? super C 是指整个 List<?> 的类型 ? 必须是 C 的父类，是指定义整个列表的类型实参，不限制存入的元素
        //③ 这里至低是 C 所以可以存入 C 及其子类
        //lowerBoundedList.add(new B());
        lowerBoundedList.add(new C());
        lowerBoundedList.add(new D());

        UpperBList<B> upperBList = new UpperBList<>();
        UpperBList<? super C> list = new UpperBList<>();
        list.add(new C());
        list.add(new D());
    }

    /**
     * 限制必须是 C 的超类，而 UpperBList 又必须是 B 的子类
     * 所以限制为 BC
     */
    public void writeToLowerAndUpperBoundedList(UpperBList<? super C> list) {
    }

    public void test_writeToLowerAndUpperBoundedList() {
        //失败，上界为 B
        //UpperBList<A> aList=new UpperBList<>();
        UpperBList<B> bList = new UpperBList<>();
        UpperBList<C> cList = new UpperBList<>();
        UpperBList<D> dList = new UpperBList<>();

        writeToLowerAndUpperBoundedList(bList);
        writeToLowerAndUpperBoundedList(cList);
        //失败，下界为 C
        //writeToLowerAndUpperBoundedList(dList);
    }

}
