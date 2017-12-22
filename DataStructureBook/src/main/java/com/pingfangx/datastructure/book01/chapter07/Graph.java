package com.pingfangx.datastructure.book01.chapter07;

/**
 * @author pingfangx
 * @date 2017/12/21
 */
public class Graph {
    public static final int MAX = Integer.MAX_VALUE;
    public static final int MAX_VERTEX_NUM = 20;
    /*顶点数*/
    public int vexnum;
    /*弧数*/
    public int arcnum;

    public int locateVex(int v) {
        return 0;
    }

    public int getVex(int v) {
        return 0;
    }

    public void putVex(int v, int value) {
    }

    /**
     * g 的第一个邻接顶点
     */
    public int firstAdjVex(int v) {
        return 0;
    }

    public int nextAdjVex(int v, int w) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("vexnum=%d\narcnum=%d", vexnum, arcnum);
    }
}
