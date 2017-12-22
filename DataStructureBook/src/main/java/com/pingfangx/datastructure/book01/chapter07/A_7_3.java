package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.IOUtils;

/**
 * @author pingfangx
 * @date 2017/12/21
 */
public class A_7_3 {
    public static class ArcBox {
        int headvex;
        int trilvex;
        ArcBox hlink;
        ArcBox tlink;
        MGraph.InfoType info;

        public ArcBox(int headvex, int trilvex, ArcBox hlink, ArcBox tlink, MGraph.InfoType info) {
            this.headvex = headvex;
            this.trilvex = trilvex;
            this.hlink = hlink;
            this.tlink = tlink;
            this.info = info;
        }
    }

    public class VexNode {
        MGraph.VertexType data;
        ArcBox firstin;
        ArcBox firsout;
    }

    public class OLGraph extends MGraph {
        VexNode[] xlist = new VexNode[MGraph.MAX_VERTEX_NUM];
    }

    public OLGraph createDG() {
        OLGraph g = new OLGraph();
        g.vexnum = IOUtils.getInt("顶点数");
        g.arcnum = IOUtils.getInt("弧数");
        int incInfo = IOUtils.getInt("inc info");
        for (int i = 0; i < g.vexnum; i++) {
            g.xlist[i].data = new MGraph.VertexType(IOUtils.getInt("i=" + i));
            g.xlist[i].firstin = null;
            g.xlist[i].firsout = null;
        }
        for (int k = 0; k < g.arcnum; k++) {
            int v1 = IOUtils.getInt("v1");
            int v2 = IOUtils.getInt("v2");
            int i = g.locateVex(v1);
            int j = g.locateVex(v2);
            ArcBox p = new ArcBox(i, j, g.xlist[j].firstin, g.xlist[i].firsout, null);
            g.xlist[j].firstin = p;
            g.xlist[i].firsout = p;
            if (incInfo != 0) {
                p.info = new MGraph.InfoType(IOUtils.getChar() + "");
            }
        }
        return g;
    }
}

