package com.pingfangx.datastructure.book01.chapter08;

/**
 * @author pingfangx
 * @date 2017/12/27
 */
public class A_8_3 {
    void test() {

    }

    class GList {
        int mark;
        int tag;
        GList hp;
        GList tp;
    }

    void MarkList(GList gl) {
        GList t = null;
        GList p = gl;
        boolean finished = false;
        while (!finished) {
            GList q;
            while (p.mark == 0) {
                p.mark = 1;
                q = p.hp;
                if (q != null && q.mark == 0) {
                    if (q.tag == 0) {
                        q.mark = 1;
                    } else {
                        p.hp = t;
                        p.tag = 0;
                        t = p;
                        p = q;
                    }
                }
            }
            q = p.tp;
            if (q != null && q.mark == 0) {
                p.tp = t;
                t = p;
                p = q;
            } else {
                while (t != null && t.tag == 1) {
                    q = t;
                    t = q.tp;
                    q.tp = p;
                    p = q;
                }
                if (t == null) {
                    finished = true;
                } else {
                    q = t;
                    t = q.hp;
                    q.hp = p;
                    p = q;
                    p.tag = 1;
                }
            }
        }
    }
}
