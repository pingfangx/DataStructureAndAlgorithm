package com.pingfangx.datastructure.book01.chapter03;

import com.pingfangx.datastructure.common.structure.Stack;
import com.pingfangx.datastructure.common.util.LogUtils;

import java.io.IOException;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_3_2 {
    public static void main(String[] args) throws IOException {
        lineEdit();
    }

    private static void lineEdit() throws IOException {
        LogUtils.d("开始输入，# 删除前一个，@ 删除整行，~ 退出");
        Stack stack = new Stack();
        char ch = getChar();
        //没有EOF，找一个符号代替
        char EOF = '~';
        while (ch != EOF) {
            while (ch != EOF && ch != '\n') {
                switch (ch) {
                    case '#':
                        if (!stack.empty()) {
                            stack.pop();
                        }
                        break;
                    case '@':
                        stack.clear();
                        break;
                    default:
                        stack.push(ch);
                        break;
                }
                ch = getChar();
            }
            printStack(stack);
            stack.clear();
            if (ch != EOF) {
                ch = getChar();
            }
        }
    }

    private static char getChar() throws IOException {
        return (char) System.in.read();
    }

    public static void printStack(Stack stack) {
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            stringBuilder.insert(0, (char) stack.pop());
        }
        LogUtils.d(stringBuilder.toString());
    }
}
