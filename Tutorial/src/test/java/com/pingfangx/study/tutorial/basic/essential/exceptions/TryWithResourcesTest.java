package com.pingfangx.study.tutorial.basic.essential.exceptions;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2019/3/7
 */
public class TryWithResourcesTest {
    private void test1(FileReader fileReader) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(fileReader);
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void test2(FileReader fileReader) {
        try (BufferedReader br = new BufferedReader(fileReader)) {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void suppressedException() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""))) {
            System.out.println(br.readLine());
            int i = 1 / 0;
        }
    }

    /**
     * 测试抑制的异常
     */
    @Test
    public void test_suppressedException() {
        try {
            suppressedException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class TestResource implements AutoCloseable {
        private boolean isOpen;

        public void open() {
            isOpen = true;
        }

        @Override
        public void close() throws IllegalStateException {
            System.out.println("close,isOpen=" + isOpen);
            if (isOpen) {
                isOpen = false;
            } else {
                throw new IllegalStateException("文件已关闭");
            }
        }
    }

    /**
     * close in try
     * close,isOpen=true
     * try finish
     * close,isOpen=false
     * 拦截到异常:文件已关闭
     * finally
     */
    private void suppressedException2() {
        try (TestResource resource = new TestResource()) {
            resource.open();
            System.out.println("close in try");
            resource.close();
            System.out.println("try finish");
        } catch (IllegalStateException e) {
            System.out.println("拦截到异常:" + e.getLocalizedMessage());
        } finally {
            //错误，引用不到 resource
            //resource.close();
            System.out.println("finally");
        }
    }

    /**
     * close in try
     * close,isOpen=true
     * try finish
     * close,isOpen=false
     * finally
     * 外部拦截到异常:/ by zero
     * 抑制的异常:[java.lang.IllegalStateException: 文件已关闭]
     */
    private void suppressedException3() {
        try (TestResource resource = new TestResource()) {
            resource.open();
            System.out.println("close in try");
            resource.close();
            System.out.println("try finish");
            int i = 1 / 0;
        } catch (IllegalStateException e) {
            System.out.println("拦截到异常:" + e.getLocalizedMessage());
        } finally {
            //错误，引用不到 resource
            //resource.close();
            System.out.println("finally");
        }
    }

    /**
     * 测试抑制的异常
     */
    @Test
    public void test_suppressedException2() {
        try {
            suppressedException3();
        } catch (Exception e) {
            System.out.println("外部拦截到异常:" + e.getLocalizedMessage());
            System.out.println("抑制的异常:" + Arrays.toString(e.getSuppressed()));
        }
    }
}
