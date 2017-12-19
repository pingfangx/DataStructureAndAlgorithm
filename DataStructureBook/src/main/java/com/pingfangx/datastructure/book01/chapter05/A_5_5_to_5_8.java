package com.pingfangx.datastructure.book01.chapter05;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_5_5_to_5_8 {
    public static enum ElemTag {
        ATOM, LIST;
    }

    public static class Ptr {
        GLNode hp;
        GLNode tp;

        @Override
        public String toString() {
            return String.format("hp={%s},tp={%s}", hp, tp);
        }
    }

    public static class AtomType {
        Object value;

        public AtomType(Object value) {
            this.value = value;
        }

    }

    public static class GLNode {
        ElemTag tag;
        AtomType atom;
        Ptr ptr = new Ptr();

        @Override
        public String toString() {
            return String.format("tag=%s,atom=%s,ptr={%s}", tag, atom == null ? null : atom.value, ptr);
        }
    }

    /**
     * 5.5
     * 有问题，没有认真看
     */
    public static int depth(GLNode L) {
        if (L == null) {
            return 0;
        } else if (L.tag == null) {
            //空表
            return 1;
        } else if (L.tag == ElemTag.ATOM) {
            return 0;
        }
        int max = 0;
        GLNode pp = L;
        while (pp != null) {
            int depth = depth(pp.ptr.tp);
            pp = pp.ptr.tp;
            if (depth > max) {
                max = depth;
            }
        }
        return max + 1;
    }

    /**
     * 5.6
     */
    public static GLNode copyGList(GLNode L) {
        if (L == null) {
            return null;
        }
        GLNode T = new GLNode();
        T.tag = L.tag;
        if (L.tag == ElemTag.ATOM) {
            //复制单原子
            T.atom = L.atom;
        } else {
            //复制广义表
            T.ptr.hp = copyGList(L.ptr.hp);
            T.ptr.tp = copyGList(L.ptr.tp);
        }
        return T;
    }

    public static GLNode createGList(String S) {
        String emp = "()";
        if (S == null || S.length() == 0) {
            return null;
        } else if (S.equals(emp)) {
            return new GLNode();
        }
        GLNode L = new GLNode();
        if (S.length() == 1) {
            //单原子
            L.tag = ElemTag.ATOM;
            L.atom = new AtomType(S);
        } else {
            L.tag = ElemTag.LIST;
            GLNode p = L;
            GLNode q;
            //胶括号
            String sub = S.substring(1, S.length() - 1);
            do {
                //取出前一段，剩余后一段
                String[] result = server(sub);
                String hsub = result[0];
                sub = result[1];
                //head 指向前面的部分，生成一个表
                p.ptr.hp = createGList(hsub);
                q = p;
                if (sub != null && sub.length() > 0) {
                    p = new GLNode();
                    //当前为列表，下一个指向自己，
                    p.tag = ElemTag.LIST;
                    //下一个元素指向 p ，p.ptr.hp 会在下一次循环时置为新的表
                    q.ptr.tp = p;
                }
            } while (sub != null && sub.length() > 0);
            //循环结束，这里是最后一部分
            q.ptr.tp = null;
        }
        return L;
    }

    /**
     * 5.8
     */
    public static String[] server(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int n = str.length();
        int i = -1;
        int k = 0;
        char ch;
        do {
            i++;
            ch = str.charAt(i);
            if (ch == '(') {
                k++;
            } else if (ch == ')') {
                k--;
            }
        } while (i < n - 1 && (ch != ',' || k != 0));
        String hstr;
        String sub = "";
        if (i < n - 1) {
            hstr = str.substring(0, i);
            sub = str.substring(i + 1, n);
        } else {
            hstr = str;
            str = null;
        }
        return new String[]{hstr, sub};
    }
}
