package com.pingfangx.datastructure.common.structure;

import java.util.Arrays;

/**
 * 串的堆分配存储表示
 *
 * @author pingfangx
 * @date 2017/11/10
 */
public class HString {
    public int length;
    public char[] ch;

    public HString(String source) {
        length = source.length();
        ch = source.toCharArray();
    }

    public HString(char[] ch) {
        this.ch = ch;
        this.length = ch.length;
    }

    public int length() {
        return this.length;
    }

    public int compare(HString b) {
        for (int i = 0; i < this.length && i < b.length; i++) {
            if (this.ch[i] != b.ch[i]) {
                return this.ch[i] - b.ch[i];
            }
        }
        return this.length - b.length;
    }

    public void clear() {
        this.length = 0;
        this.ch = null;
    }

    public void concat(HString s2) {
        char[] newChar = new char[this.length + s2.length];
        System.arraycopy(this.ch, 0, newChar, 0, this.length);
        this.ch = newChar;
    }

    @Override
    public String toString() {
        return length + ":" + Arrays.toString(ch);
    }
}
