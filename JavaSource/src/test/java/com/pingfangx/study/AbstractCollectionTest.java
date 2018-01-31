package com.pingfangx.study;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author pingfangx
 * @date 2018/2/2
 */
public class AbstractCollectionTest {
    /**
     * 在 finishToArray 中使用的
     * int newCap = ((cap / 2) + 1) * 3;
     * int newCap = cap + (cap >> 1) + 1;
     */
    @Test
    public void testIncreaseCap() {
        int[] a0 = new int[100];
        int[] a1 = new int[100];
        int[] a2 = new int[100];
        for (int i = 0; i < a1.length; i++) {
            a0[i] = i;
            a1[i] = ((i / 2) + 1) * 3;
            a2[i] = i + (i >> 1) + 1;
        }
        System.out.println(Arrays.toString(a0));
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
    }

    @Test
    public void testToArray() {
        main(null);
    }

    public static void main(String[] args) {
        int size = 100;
        Collection<Integer> collection = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }
        new IteratorThread(1, collection).start();
//        new RemoveThread(2, collection).start();
        new AddThread(2, collection).start();
    }

    static class RemoveThread extends Thread {
        int id;
        Collection collection;

        public RemoveThread(int id, Collection collection) {
            this.id = id;
            this.collection = collection;
        }

        @Override
        public void run() {
            super.run();
            Iterator iterator = collection.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                iterator.next();
                iterator.remove();
                System.out.println("remove" + i++);
            }
        }
    }

    static class AddThread extends Thread {
        int id;
        Collection collection;

        public AddThread(int id, Collection collection) {
            this.id = id;
            this.collection = collection;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 100; i < 200; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collection.add(i);
                System.out.println("add" + i);
            }
        }
    }

    static class IteratorThread extends Thread {
        int id;
        Collection collection;

        public IteratorThread(int id, Collection collection) {
            this.id = id;
            this.collection = collection;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int size = collection.size();
                try {
                    System.out.println(i + "-" + collection);
                    Object[] objects = collection.toArray();
                    if (size != objects.length) {
                        System.out.println(i + "-" + "size 不相等" + size + "!=" + objects.length);
                    }
                    System.out.println(i + "-" + Arrays.toString(objects));
                } catch (Exception e) {
                    System.out.println(i + "-异常");
                }
            }
        }
    }
}
