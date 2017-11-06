package com.pingfangx.datastructure.book01.chapter03;

import com.pingfangx.datastructure.common.constant.STATUS;

import java.util.Stack;

/**
 * TODO
 * 求迷宫的方法，很喜欢这个，需要再扩展一下
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_3_3 {
    public static void main(String[] args) {
    }

    public Object mazePath(Maze maze, Position start, Position end) {
        int currentStep = 1;
        Position curPosition = start;
        Stack<Path> stack = new Stack<>();
        do {
            if (pass(curPosition)) {
                //当前位置可通过
                footPrint(curPosition);
                stack.push(new Path(curPosition, currentStep));
                if (curPosition == end) {
                    return STATUS.TRUE;
                }
                //当前位置的东邻
                curPosition = nextPosition(curPosition, 1);
                currentStep++;
            } else {
                //当前位置不可通过
                if (!stack.isEmpty()) {
                    Path path = stack.pop();
                    while (path.direction == 4 && !stack.isEmpty()) {
                        //如果已经到最后一个方向不可通过，则将其标记为不可用
                        markPrint(path.position);
                        path = stack.pop();
                    }
                    if (path.direction < 4) {
                        path.direction++;
                        stack.push(path);
                        curPosition = nextPosition(path.position, path.direction);
                    }
                }
            }
        } while (!stack.isEmpty());
        return STATUS.FALSE;
    }

    /**
     * 标记为不可通过
     */
    private void markPrint(Position position) {
    }

    private Position nextPosition(Position position, int direction) {
        return position;
    }

    /**
     * 留下足迹
     */
    private static void footPrint(Position curPos) {
    }

    private static boolean pass(Position curPos) {
        return true;
    }

    class Maze {
    }

    class Position {
    }

    class Path {
        int direction;
        Position position;

        public Path(Position position, int direction) {
            this.position = position;
            this.direction = direction;
        }
    }
}
