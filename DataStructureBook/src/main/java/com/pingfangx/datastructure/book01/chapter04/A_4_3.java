package com.pingfangx.datastructure.book01.chapter04;

import com.pingfangx.datastructure.book01.common.DataBuilder;
import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/10
 */
public class A_4_3 {
    public static void main(String[] args) {
        Object[] a = DataBuilder.buildSString("abcdefg");
        LogUtils.d(subString(a, 6, 1));
    }

    private static Object subString(Object[] a, int pos, int len) {
        int lengthA = (int) a[0];
        // len=7,pos=6,len>1错误
        if (pos < 0 || pos > lengthA || len < 0 || len > lengthA - pos) {
            return STATUS.ERROR;
        }
        Object[] r = new Object[len + 1];
        r[0] = len;
        System.arraycopy(a, 1, r, 1, len);
        return r;
    }
}
