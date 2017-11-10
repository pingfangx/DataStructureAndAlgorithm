package com.pingfangx.datastructure.book01.chapter04;

import com.pingfangx.datastructure.common.constant.STATUS;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2017/11/10
 */
public class A_4_2 {
    static int MAX_LENGTH = 10;

    public static void main(String[] args) {
        Object[] a = DataBuilder.buildSString("abcdefghijkl");
        Object[] b = DataBuilder.buildSString("xyz");
        LogUtils.d(Arrays.toString(a));
        LogUtils.d(Arrays.toString(b));
        Object[] c = new Object[MAX_LENGTH + 1];
        LogUtils.d(concat(a, b, c));
        LogUtils.d(Arrays.toString(c));
    }

    private static Object concat(Object[] a, Object[] b, Object[] c) {
        int lengthA = (int) a[0];
        int lengthB = (int) b[0];
        if (lengthA + lengthB < MAX_LENGTH) {
            System.arraycopy(a, 1, c, 1, lengthA);
            System.arraycopy(b, 1, c, lengthA + 1, lengthB);
            c[0] = lengthA + lengthB;
            return STATUS.TRUE;
        } else if (lengthA < MAX_LENGTH) {
            System.arraycopy(a, 1, c, 1, lengthA);
            System.arraycopy(b, 1, c, lengthA + 1, MAX_LENGTH - lengthA);
            c[0] = MAX_LENGTH;
            return STATUS.FALSE;
        } else {
            System.arraycopy(a, 1, c, 1, MAX_LENGTH);
            c[0] = MAX_LENGTH;
            return STATUS.FALSE;
        }
    }

}
