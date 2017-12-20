package com.pingfangx.datastructure.book01.chapter04;

import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/10
 */
public class A_4_4 {
    public static void main(String[] args) {
        HString a = new HString("abc");
        HString b = new HString("12345");
        LogUtils.d(a);
        LogUtils.d(b);
        LogUtils.d(strInsert(a, b, 0));
        LogUtils.d(a);
    }

    private static Object strInsert(HString a, HString b, int index) {
        if (index < 0 || index > a.length) {
            return STATUS.ERROR;
        }
        if (b.length > 0) {
            char[] ch = new char[a.length + b.length];
            System.arraycopy(a.ch, 0, ch, 0, a.length);
            a.ch = ch;
            //上面模拟生成新的字符串
            //移动，将 a.ch 从 index 移至 index+b.length，移的长度为 a.length-index
            System.arraycopy(a.ch, index, a.ch, index + b.length, a.length - index);
            System.arraycopy(b.ch, 0, a.ch, index, b.length);
        }
        return STATUS.OK;
    }
}
