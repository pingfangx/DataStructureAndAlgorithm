package com.pingfangx.study.tutorial.extra.generics;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author pingfangx
 * @date 2019/5/27
 */
public class FinePrintTest<T> {
    /**
     * 数组对象的组件类型不能是类型变量或参数化类型，除非它是(无界)通配符类型。您可以声明数组 类型 其元素类型为类型变量或参数化类型，但不能创建数组 对象 其元素类型为类型变量或参数化类型。
     * 以下类型声明不报错，但是创建对象是错的
     */
    public void test() {
        //以下初始化均错误
//            List<T>[] objects = new List<T>[10];
//            List<String>[] lsa = new List<String>[10];
//            T[] a = new T[10];
    }

    /**
     * 无界通配符类型是可以的
     */
    @Test
    public void testUnboundedWildcardType() {
        List<?>[] listWildcardArray = new List<?>[10];
        List<String> stringList = new ArrayList<>();
        stringList.add("text");
        listWildcardArray[0] = stringList;

        //需要强制转换
        String s = (String) listWildcardArray[0].get(0);
        System.out.println(s);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        listWildcardArray[1] = integerList;

        Integer n = (Integer) listWildcardArray[1].get(0);
        System.out.println(n);
    }

    @Test
    public void testArrayNewInstance() {
        //实际创建的只是 List，却转为 List<String> 实际是不正确的
        List<String>[] listWildcardArray = (List<String>[]) Array.newInstance(List.class, 10);
        List<String> stringList = new ArrayList<>();
        stringList.add("text");
        listWildcardArray[0] = stringList;

        //需要强制转换
        String s = listWildcardArray[0].get(0);
        System.out.println(s);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        //报类型不正确
//        listWildcardArray[1] = integerList;

        //上面的赋值报错，向下面这样写之后，没有编译警告，却报错了，实际是不符合泛型设计目标的。
        Object object = listWildcardArray;
        Object[] objectArray = (Object[]) object;
        objectArray[1] = integerList;

        //正常
        System.out.println(String.valueOf(listWildcardArray[1].get(0)));


        //正常
        Object o = listWildcardArray[1].get(0);
        System.out.println(o);

        //报错，将 Integer 转 String 方法签名认为是 Strnig
        System.out.println(listWildcardArray[1].get(0));

        //报错，将 Integer 转 String
        String s2 = listWildcardArray[1].get(0);
        System.out.println(s2);
        String b="";
    }

    /**
     * 这一部分不太懂
     */
    @Test
    public void testWildcardCapture() {
        Set<?> unknownSet = new HashSet<String>();
        //不可
//        addToSet(unknownSet,"abc");

        Set<?> s = Collections.unmodifiableSet(unknownSet);
    }

    /* Add an element  t to a Set s. */
    public static <T> void addToSet(Set<T> s, T t) {
        s.add(t);
    }
}
