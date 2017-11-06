package com.pingfangx.datastructure.common.util;

import java.io.IOException;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class IOUtils {
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
