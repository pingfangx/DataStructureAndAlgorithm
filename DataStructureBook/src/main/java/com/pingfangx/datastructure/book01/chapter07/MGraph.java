package com.pingfangx.datastructure.book01.chapter07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图的数组（邻接矩阵）存储表示
 *
 * @author pingfangx
 * @date 2017/12/21
 */
public class MGraph extends Graph {

    public static enum GraphKind {
        //有向图，有向网，无向图，无向网
        DG, DN, UDG, UDN
    }

    /**
     * 顶点
     */
    public static class VertexType {
        public String value;

        public VertexType(int value) {
            this(String.valueOf(value));
        }

        public VertexType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    /**
     * 无权图，用 1 或 0 表示相邻否
     * 带权图，表示权值
     */
    public static class VRType {
        public VRType(int value) {
            this.value = value;
        }

        public int value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * 信息
     */
    public static class InfoType {
        public String info;

        public InfoType(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return info;
        }
    }

    public static class ArcCell {
        /**
         * 无权图，用 1 或 0 表示相邻否
         * 带权图，表示权值
         */
        public VRType adj;
        /**
         * 信息
         */
        public InfoType info;

        public ArcCell(VRType adj) {
            this.adj = adj;
        }

        @Override
        public String toString() {
            return String.valueOf(adj);
        }
    }

    /*顶点向量*/
    public VertexType[] vexs;
    /*弧*/
    public ArcCell[][] arcs = new ArcCell[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
    /*种类*/
    public GraphKind kind;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("kind=")
                .append(kind)
                .append("\nvexnum=")
                .append(vexnum)
                .append("\narcnum=")
                .append(arcnum)
                .append("\nvertex=")
                .append(Arrays.toString(vexs));
        for (int i = 0; i < arcs.length; i++) {
            ArcCell[] arcCells = arcs[i];
            builder.append('\n');
            builder.append("i=");
            builder.append(i);
            builder.append(':');
            builder.append(Arrays.toString(arcCells).replace("null", "0"));
        }
        return builder.toString();
    }

    public static MGraph create(int size, int[][] arcs) {
        List<Integer> vertex = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            vertex.add(i + 1);
        }
        MGraph graph = new MGraph();
        graph.vexnum = size;
        graph.arcnum = arcs.length;
        graph.vexs = new VertexType[size];
        for (int i = 0; i < size; i++) {
            graph.vexs[i] = new VertexType(vertex.get(i));
        }
        graph.arcs = new ArcCell[size][size];
        for (int[] iArcs : arcs) {
            graph.arcs[iArcs[0] - 1][iArcs[1] - 1] = new ArcCell(new VRType(iArcs[2]));
            graph.arcs[iArcs[1] - 1][iArcs[0] - 1] = new ArcCell(new VRType(iArcs[2]));
        }
        return graph;
    }

    public static MGraph create(String prefix, int size, int[][] arcs) {
        MGraph graph = new MGraph();
        graph.vexnum = size;
        graph.arcnum = arcs.length;
        graph.vexs = new VertexType[size];
        for (int i = 0; i < size; i++) {
            graph.vexs[i] = new VertexType(prefix + i);
        }
        graph.arcs = new ArcCell[size][size];
        for (int[] iArcs : arcs) {
            if (iArcs.length == 3) {
                //单向
                graph.arcs[iArcs[0]][iArcs[1]] = new ArcCell(new VRType(iArcs[2]));
            }
        }
        return graph;
    }
}
