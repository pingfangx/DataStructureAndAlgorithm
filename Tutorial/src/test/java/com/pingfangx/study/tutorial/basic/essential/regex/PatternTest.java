package com.pingfangx.study.tutorial.basic.essential.regex;

import java.util.regex.Pattern;

/**
 * @author pingfangx
 * @date 2019/5/21
 */
public class PatternTest {
    public static void main(String[] args) {
        //这量 \s 表示的空白，不仅仅是空格
        System.out.println(Pattern.matches("[ \\t\\n\\x0B\\f\\r]", "\u000b"));
    }
}
