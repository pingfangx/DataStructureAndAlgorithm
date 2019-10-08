package com.pingfangx.study.tutorial.essential_java_classes.exceptions;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/8
 */
public class FinallyClauseTest {
    /**
     * try
     * catch
     * finally
     */
    @Test
    public void test_normalTryCatchFinally() {
        try {
            System.out.println("try");
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }

    /**
     * try
     * finally
     * 结果是 finally
     */
    private String returnInClause() {
        try {
            System.out.println("try");
            return "try";
        } catch (Exception e) {
            System.out.println("catch");
            return "catch";
        } finally {
            System.out.println("finally");
            return "finally";
        }
    }

    /**
     * try
     * catch
     * finally
     * 结果是 finally
     * <p>
     * 警告
     * 'finally' block can not complete normally
     * 'return' inside 'finally' block
     */
    private String returnInClause2() {
        try {
            System.out.println("try");
            int i = 1 / 0;
            return "try";
        } catch (Exception e) {
            System.out.println("catch");
            return "catch";
        } finally {
            System.out.println("finally");
            return "finally";
        }
    }

    @Test
    public void test_returnInTryClause() {
        System.out.println("结果是 " + returnInClause());
        System.out.println("结果是 " + returnInClause2());
    }
}
