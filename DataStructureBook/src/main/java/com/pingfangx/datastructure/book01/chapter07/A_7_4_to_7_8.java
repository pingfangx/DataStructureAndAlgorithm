package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.book01.common.Visit;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author pingfangx
 * @date 2017/12/21
 */
public class A_7_4_to_7_8 {
    boolean[] visited = new boolean[Graph.MAX];

    public void DFSTraverse(Graph g) {
        for (int v = 0; v < g.vexnum; v++) {
            visited[v] = false;
        }
        for (int v = 0; v < g.vexnum; v++) {
            if (!visited[v]) {
                DFS(g, v);
            }
        }
    }

    /**
     * 7.5 深度优先
     */
    private void DFS(Graph g, int v) {
        visited[v] = true;
        Visit.visit(v);
        for (int w = g.firstAdjVex(v); w >= 0; w = g.nextAdjVex(v, w)) {
            if (!visited[w]) {
                DFS(g, w);
            }
        }
    }

    /**
     * 7.6 广度优先
     */
    public void BFSTraverse(Graph g) {
        for (int v = 0; v < g.vexnum; v++) {
            visited[v] = false;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int v = 0; v < g.vexnum; v++) {
            if (!visited[v]) {
                visited[v] = true;
                Visit.visit(v);
                //入队
                q.add(v);
                while (!q.isEmpty()) {
                    int u = q.remove();
                    for (int w = g.firstAdjVex(u); w >= 0; w = g.nextAdjVex(u, w)) {
                        if (!visited[w]) {
                            visited[w] = true;
                            //入队
                            Visit.visit(w);
                            q.add(w);
                        }
                    }
                }
            }
        }
    }

    public class CSTree {
        CSTree nextSibling;
        CSTree lchild;

        public CSTree(int vex, Object o, Object o1) {

        }
    }

    CSTree q = null;

    /**
     * 7.7
     */
    public void DFSForest(Graph g) {
        for (int v = 0; v < g.vexnum; v++) {
            visited[v] = false;
        }
        CSTree T = null;
        for (int v = 0; v < g.vexnum; v++) {
            if (!visited[v]) {
                CSTree p = new CSTree(g.getVex(v), null, null);
                if (T == null) {
                    T = p;
                } else {
                    q.nextSibling = p;
                }
                q = p;
                DFSTree(g, v, p);
            }
        }
    }

    /**
     * 7.8
     */
    private void DFSTree(Graph g, int v, CSTree t) {
        visited[v] = true;
        boolean first = true;
        for (int w = g.firstAdjVex(v); w >= 0; w = g.nextAdjVex(v, w)) {
            if (!visited[w]) {
                CSTree p = new CSTree(g.getVex(w), null, null);
                if (first) {
                    t.lchild = p;
                    first = false;
                } else {
                    q.nextSibling = p;
                }
                q = p;
                DFSTree(g, w, q);
            }
        }
    }
}
