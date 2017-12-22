package com.pingfangx.datastructure.book01.chapter07;

import java.util.Arrays;

/**
 * 邻接表
 *
 * @author pingfangx
 * @date 2017/12/25
 */
public class ALGraph extends Graph {
    public static class InfoType {
        public int value;

        public InfoType(int value) {
            this.value = value;
        }
    }

    public static class ArcNode {
        //该弧所指向的顶点的位置
        int adjvex;
        //下一条弧
        ArcNode nextarc;
        InfoType info;
    }


    public static class VNode {
        /**
         * 顶点信息
         */
        MGraph.VertexType data;
        /**
         * 第一条弧
         */
        ArcNode firstarc;

        @Override
        public String toString() {
            return "{" + data + '}';
        }
    }

    /**
     * 顶点
     */
    VNode[] vertices = new VNode[MAX_VERTEX_NUM];

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("\nnodes=\n");
        builder.append(Arrays.toString(vertices));
        builder.append("\narcs=\n");
        for (VNode vertex : vertices) {
            if (vertex == null) {
                continue;
            }
            builder.append(vertex);
            builder.append(':');
            ArcNode arcNode = vertex.firstarc;
            if (arcNode != null) {
                builder.append(getArcString(arcNode));
                while (arcNode.nextarc != null) {
                    arcNode = arcNode.nextarc;
                    builder.append('-');
                    builder.append('>');
                    builder.append(getArcString(arcNode));
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private String getArcString(ArcNode arcNode) {
        return vertices[arcNode.adjvex].toString() + (arcNode.info == null ? "" : ":" + arcNode.info.value);
    }

    /**
     * 创建
     *
     * @param nodeRange 顶点范围，如 AZ
     * @param arcs      弧，如 AB AC
     */
    public static ALGraph create(String nodeRange, String[] arcs) {
        ALGraph graph = new ALGraph();
        if (nodeRange != null && nodeRange.length() == 2) {
            int start = nodeRange.charAt(0);
            int end = nodeRange.charAt(1);
            graph.vexnum = end - start + 1;
            //顶点
            for (int i = 0; i < graph.vexnum; i++) {
                VNode iNode = new VNode();
                iNode.data = new MGraph.VertexType(String.valueOf((char) (i + start)));
                graph.vertices[i] = iNode;
            }
            //弧
            if (arcs != null) {
                for (String arc : arcs) {
                    if (arc != null && arc.length() == 2) {
                        int arcStart = arc.charAt(0) - start;
                        int arcEnd = arc.charAt(1) - start;
                        //正反两个方向
                        addArc(graph, arcStart, arcEnd, 0);
                        addArc(graph, arcEnd, arcStart, 0);
                    }
                }
            }
        }
        return graph;
    }

    public static ALGraph create(String prefix, String nodeRange, String[] arcs) {
        ALGraph graph = new ALGraph();
        if (nodeRange == null || !nodeRange.contains("-")) {
            return null;
        }
        String[] startAndEnd = nodeRange.split("-");
        if (startAndEnd.length != 2) {
            return null;
        }
        int start = 0;
        int end = 0;
        try {
            start = Integer.parseInt(startAndEnd[0]);
            end = Integer.parseInt(startAndEnd[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (start < 0 || end < 0 || start > end) {
            return null;
        }
        graph.vexnum = end - start + 1;
        //顶点
        for (int i = 0; i < graph.vexnum; i++) {
            VNode iNode = new VNode();
            iNode.data = new MGraph.VertexType(prefix + (i + start));
            graph.vertices[i] = iNode;
        }
        //弧
        if (arcs != null) {
            for (String arc : arcs) {
                if (arc != null && arc.contains("-")) {
                    String[] arcStartAndEnd = arc.split("-");
                    if (arcStartAndEnd.length >= 2) {
                        int arcStart = 0;
                        int arcEnd = 0;
                        try {
                            arcStart = Integer.parseInt(arcStartAndEnd[0]);
                            arcEnd = Integer.parseInt(arcStartAndEnd[1]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        int info = 0;
                        if (arcStartAndEnd.length >= 3) {
                            try {
                                info = Integer.parseInt(arcStartAndEnd[2]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (arcStart != arcEnd) {
                            arcStart -= start;
                            arcEnd -= start;
                            //单向添加
                            addArc(graph, arcStart, arcEnd, info);
                        }
                    }
                }
            }
        }
        return graph;
    }

    private static void addArc(ALGraph graph, int arcIndex, int arcEnd, int info) {
        ArcNode arcNode = new ArcNode();
        arcNode.adjvex = arcEnd;
        if (info != 0) {
            arcNode.info = new InfoType(info);
        }
        ArcNode firstarc = graph.vertices[arcIndex].firstarc;
        if (firstarc == null) {
            //添加第一个
            graph.vertices[arcIndex].firstarc = arcNode;
        } else {
            if (firstarc.adjvex == arcNode.adjvex) {
                //已经包含
                return;
            }
            //查找最后一个,并拼接
            while (firstarc.nextarc != null) {
                firstarc = firstarc.nextarc;
                if (firstarc.adjvex == arcNode.adjvex) {
                    //已经包含
                    return;
                }
            }
            firstarc.nextarc = arcNode;
        }
        graph.arcnum++;
    }
}
