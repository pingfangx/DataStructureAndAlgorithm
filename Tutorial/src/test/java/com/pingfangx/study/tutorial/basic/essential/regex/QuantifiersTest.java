package com.pingfangx.study.tutorial.basic.essential.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pingfangx
 * @date 2019/10/9
 */
public class QuantifiersTest {
    private void test_match(String regex, String text) {
        System.out.printf("match %s in %s %n", regex, text);
        Matcher matcher = Pattern.compile(regex).matcher(text);
        boolean found = false;
        while (matcher.find()) {
            System.out.printf("found %s,%d,%d %n", matcher.group(), matcher.start(), matcher.end());
            found = true;
        }
        if (!found) {
            System.out.println("not found");
        }
    }

    @Test
    public void test_Quantifiers() {
        String text = "abccccccabcd";
        //贪婪
        test_match(".*c", text);
        //懒惰
        test_match(".*?c", text);
        //占有
        test_match(".*+c", text);
    }
}
