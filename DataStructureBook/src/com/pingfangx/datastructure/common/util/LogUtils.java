package com.pingfangx.datastructure.common.util;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class LogUtils {
    public static void d(String msg) {
        System.out.println(msg);
    }

    public static void d(String format, Object... args) {
        d(String.format(format, args));
    }

    public static void d(Object object) {
        if (object == null) {
            d("null");
            return;
        }
        if (object.getClass().isArray()) {
            d((Object[]) object);
            return;
        }
        d(object.toString());
    }


    public static void d(Object[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (Object item : array) {
            if (item != null) {
                stringBuilder.append(item.toString());
                stringBuilder.append(',');
            } else {
                stringBuilder.append("<null>,");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(']');
        d(stringBuilder.toString());
    }
}
