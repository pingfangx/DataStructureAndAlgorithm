package com.pingfangx.study.tutorial.learning_the_java_language.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/6/3
 */
public class StringTest {
    /**
     * 查找所有字符中，小写相等，但是大写不相等的字符
     * <pre>
     * 73 与 304，即 I,İ,小写相等 i,i，但是大写不相等I,İ
     * 75 与 8490，即 K,K,小写相等 k,k，但是大写不相等K,K
     * 105 与 304，即 i,İ,小写相等 i,i，但是大写不相等I,İ
     * 107 与 8490，即 k,K,小写相等 k,k，但是大写不相等K,K
     * 197 与 8491，即 Å,Å,小写相等 å,å，但是大写不相等Å,Å
     * 223 与 7838，即 ß,ẞ,小写相等 ß,ß，但是大写不相等ß,ẞ
     * 229 与 8491，即 å,Å,小写相等 å,å，但是大写不相等Å,Å
     * 920 与 1012，即 Θ,ϴ,小写相等 θ,θ，但是大写不相等Θ,ϴ
     * 937 与 8486，即 Ω,Ω,小写相等 ω,ω，但是大写不相等Ω,Ω
     * 952 与 1012，即 θ,ϴ,小写相等 θ,θ，但是大写不相等Θ,ϴ
     * 969 与 8486，即 ω,Ω,小写相等 ω,ω，但是大写不相等Ω,Ω
     * </pre>
     * 这里面并不涉及格鲁吉亚字母，这是为什么？
     */
    @Test
    public void test_equalsIgnoreCase() {
        // code point 有点大，速度实在太慢了，如何优化
        //find(Character.MAX_CODE_POINT);
        find(Character.MAX_VALUE);
    }

    public void find(int max) {
        List<Integer> lower = new ArrayList<>();
        List<Integer> upper = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            lower.add(Character.toLowerCase(i));
            upper.add(Character.toUpperCase(i));
        }
        System.out.println("大小写生成完毕，共" + max);
        int find = 0;
        for (int i = 0; i < max; i++) {
            if (i % 1000 == 0) {
                System.out.printf("比较中 %d/%d %n", i, max);
            }
            for (int j = i; j < max; j++) {
                if (i == j) {
                    continue;
                }
                if (lower.get(i).equals(lower.get(j))) {
                    //小写相等
                    if (!upper.get(i).equals(upper.get(j))) {
                        //大写不相等
                        find++;
                        System.out.printf("%d 与 %d，即 %c,%c,小写相等 %c,%c，但是大写不相等%c,%c %n",
                                i, j, i, j,
                                lower.get(i), lower.get(j), upper.get(i), upper.get(j));
                    }
                }
            }
        }
        System.out.printf("共找到 %d 个 %n", find);
    }


    /**
     * 在 格鲁吉亚字母 中查找，但是没找到，这是为何
     */
    @Test
    public void findInGeorgian() {
        //赋值，假设字母是递增的
        int length = 33;
        char[] firstLetters = {'Ⴀ', 'ⴀ', 'ა'};
        char[][] lettersArray = new char[3][length];
        for (int i = 0; i < firstLetters.length; i++) {
            char c = firstLetters[i];
            for (int j = 0; j < length; j++) {
                lettersArray[i][j] = (char) (c + j);
            }
        }
        for (int i = 0; i < length; i++) {
            char a = lettersArray[0][i];
            char b = lettersArray[1][i];
            char c = lettersArray[2][i];
            System.out.println("字母序号" + i);
            print("字母", a, b, c);
            print("大写", Character.toUpperCase(a), Character.toUpperCase(b), Character.toUpperCase(c));
            print("小写", Character.toLowerCase(a), Character.toLowerCase(b), Character.toLowerCase(c));
            if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                System.out.println("不相等");
            }
        }
    }

    private void print(String prefix, char a, char b, char c) {
        System.out.printf("%s %c,%c,%c %d,%d,%d%n", prefix, a, b, c, (int) a, (int) b, (int) c);
    }

    @Test
    public void test_trim() {
        System.out.println("a" == "a");//true
        System.out.println("a" == new String("a"));//false
        System.out.println("a" == "a".trim());//true
        System.out.println("a" == " a".trim());//false
    }
}
