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

    public HString sub(int pos, int len) {
        if (pos < 0 || pos > length || len < 0 || len > length - pos + 1) {
            return null;
        }
        HString string = new HString("");
        if (len == 0) {
            string.ch = null;
            string.length = 0;
        } else {
            string.ch = new char[len - pos + 1];
            System.arraycopy(ch, pos, string.ch, 0, len);
            string.length = len;
        }
        return string;
    }

    @Override
    public String toString() {
        return length + ":" + Arrays.toString(ch);
    }
}
