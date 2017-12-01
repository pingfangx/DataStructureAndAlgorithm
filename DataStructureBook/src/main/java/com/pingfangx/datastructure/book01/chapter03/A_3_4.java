package com.pingfangx.datastructure.book01.chapter03;

import com.pingfangx.datastructure.common.util.IOUtils;

import java.util.Stack;

/**
 * TODO:修改为可用
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_3_4 {
    public static void main(String[] args) {
        new A_3_4().EvaluateExpression();
    }

    public void EvaluateExpression() {
        Stack<Character> operator = new Stack<>();
        Stack<Character> number = new Stack<>();
        char c = IOUtils.getChar();
        while (c != '#' || operator.peek() != '#') {
            if (!isOperator(c)) {
                number.push(c);
                c = IOUtils.getChar();
            } else {
                switch (precede(operator.peek(), c)) {
                    case '<':
                        operator.push(c);
                        c = IOUtils.getChar();
                        break;
                    case '=':
                        operator.pop();
                        c = IOUtils.getChar();
                        break;
                    case '>':
                        char ope = operator.pop();
                        char b = number.pop();
                        char a = number.pop();
                        number.push(operate(a, ope, b));
                        break;
                }
            }
        }
    }

    /**
     * 计算
     */
    private char operate(char a, char ope, char b) {
        return '1';
    }

    /**
     * 比校优先级
     */
    private char precede(Character a, char b) {
        return '<';
    }

    private boolean isOperator(char c) {
        return true;
    }
}
