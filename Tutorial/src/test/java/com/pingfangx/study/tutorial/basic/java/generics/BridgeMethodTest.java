package com.pingfangx.study.tutorial.basic.java.generics;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/1/8
 */
public class BridgeMethodTest {
    public class Node<T> {

        public T data;

        public Node(T data) {
            this.data = data;
        }

        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }

    public class MyNode extends Node<Integer> {
        public MyNode(Integer data) {
            super(data);
        }

        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }
    }

    @Test
    public void test() {
        MyNode mn = new MyNode(5);
        Node n = mn;
        // set 方法出错，生成的桥接方法转换失败 java.lang.String cannot be cast to java.lang.Integer
        n.setData("Hello");
        Integer x = (Integer) mn.data;
    }
}
