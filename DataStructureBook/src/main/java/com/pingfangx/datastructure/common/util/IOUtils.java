package com.pingfangx.datastructure.common.util;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class IOUtils {

    public static int getInt(String msg) {
        LogUtils.d(msg);
        return getInt();
    }

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static char getChar() {
        char r;
        try {
            r = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
            r = '\0';
        }
        return r;
    }
}
