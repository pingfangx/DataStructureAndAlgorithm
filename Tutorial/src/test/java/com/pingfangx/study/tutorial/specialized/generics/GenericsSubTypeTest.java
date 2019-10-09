package com.pingfangx.study.tutorial.specialized.generics;

import org.junit.Test;

import java.util.*;

/**
 * 子类关系
 *
 * @author pingfangx
 * @date 2019/9/29
 */
public class GenericsSubTypeTest {
    /**
     * 同一个泛型类，不同的类型参数
     */
    @Test
    public void test_differentParameterType() {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);

        //不能赋值，考虑读
        //integerList = numberList;
        Integer i = integerList.get(0);

        //不能赋值，考虑写
        //numberList = integerList;
        numberList.add(1.5F);
    }


    class PayloadList<E, P> extends ArrayList<E> {
    }

    /**
     * 泛型之前的继承关系
     */
    @Test
    public void test_sameParameterType() {
        Collection<String> stringCollection = new HashSet<>();
        List<String> stringList = new LinkedList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        PayloadList<String, Integer> stringIntegerPayloadList = new PayloadList<>();

        stringCollection = stringList;
        stringList = stringArrayList;
        stringArrayList = stringIntegerPayloadList;
    }

    /**
     * 边界
     */
    public void test_genericsBound() {
        List rawList = new ArrayList();
        List<?> unboundedList = new ArrayList<>();
        List<? extends Integer> upperBoundedIntegerList = new ArrayList<>();
        List<? extends Number> upperBoundedNumberList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<? super Integer> lowerBoundedIntegerList = new ArrayList<>();
        List<? super Number> lowerBoundedNumberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        //元界相当于原始类型
        rawList = unboundedList;
        unboundedList = rawList;

        //继承 Number 肯定继承 Integer
        unboundedList = upperBoundedNumberList = upperBoundedIntegerList = integerList;
        //反过来不行，继承 Number 可能为 Float
        //upperBoundedIntegerList=upperBoundedNumberList;

        //Number 的父类肯定是 Integer 的父类
        unboundedList = lowerBoundedIntegerList = lowerBoundedNumberList = numberList;
        //反过来不行，Integer 的父类不一定是 Number（考虑中间类、接口）
        //lowerBoundedNumberList=lowerBoundedIntegerList;

        //上下界都是包含自己的
        upperBoundedIntegerList = integerList;
        lowerBoundedIntegerList = integerList;
        upperBoundedNumberList = numberList;
        lowerBoundedNumberList = numberList;
    }
}
