package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.IOUtils;

/**
 * @author pingfangx
 * @date 2017/12/21
 */
public class A_7_1_to_7_2 {
    public static MGraph createGraph() {
        MGraph G = new MGraph();
        G.kind = MGraph.GraphKind.values()[IOUtils.getInt("类型")];
        switch (G.kind) {
            case DG:
                return createDG(G);
            case DN:
                return createDN(G);
            case UDG:
                return createUDG(G);
            case UDN:
                return createUDN(G);
            default:
                return null;
        }
    }

    /**
     * 7.2
     */
    private static MGraph createUDN(MGraph g) {
        g.vexnum = IOUtils.getInt("顶点数");
        g.arcnum = IOUtils.getInt("弧数");
        int incInfo = IOUtils.getInt("inc info");
        //顶点向量
        g.vexs = new MGraph.VertexType[g.vexnum];
        for (int i = 0; i < g.vexnum; i++) {
            g.vexs[i] = new MGraph.VertexType(IOUtils.getInt("i=" + i));
        }
        //邻接矩阵
        for (int i = 0; i < g.vexnum; i++) {
            for (int j = 0; j < g.vexnum; j++) {
                g.arcs[i][j] = new MGraph.ArcCell(null);
            }
        }
        for (int k = 0; k < g.arcnum; k++) {
            int v1 = IOUtils.getInt("v1");
            int v2 = IOUtils.getInt("v2");
            int w = IOUtils.getInt("w");
            int i = g.locateVex(v1);
            int j = g.locateVex(v2);
            g.arcs[i][j].adj = new MGraph.VRType(w);
            if (incInfo != 0) {
                g.arcs[i][j].info = new MGraph.InfoType(IOUtils.getChar() + "");
            }
            g.arcs[j][i] = g.arcs[i][j];
        }
        return g;
    }

    private static MGraph createUDG(MGraph g) {
        return g;
    }

    private static MGraph createDN(MGraph g) {
        return g;
    }

    private static MGraph createDG(MGraph g) {
        return g;
    }
}
