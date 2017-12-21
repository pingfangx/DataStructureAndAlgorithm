package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;

import java.util.Stack;

/**
 * @author pingfangx
 * @date 2017/12/5
 */
public class A_6_2 {
    public static STATUS inorderTrverse(BiTree biTree) {
        Stack<BiTree> stack = new Stack<>();
        stack.push(biTree);
        while (!stack.isEmpty()) {
            //向左走到尽头
            BiTree top = stack.peek();
            while (top != null) {
                stack.push(top.lchild);
                top = stack.peek();
            }
            //空退栈
            stack.pop();
            if (!stack.isEmpty()) {
                top = stack.pop();
                //访问结点
                if (A_6_1.visit(top.data) != STATUS.OK) {
                    return STATUS.ERROR;
                }
                //向右
                stack.push(top.rchild);
            }
        }
        return STATUS.OK;
    }
}
