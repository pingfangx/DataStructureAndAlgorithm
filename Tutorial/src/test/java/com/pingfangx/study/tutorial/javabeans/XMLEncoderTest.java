package com.pingfangx.study.tutorial.javabeans;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * @author pingfangx
 * @date 2019/5/30
 */
public class XMLEncoderTest {
    public static class B {
        private int n;

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }
    }

    public static class Test implements Serializable {
        private int number = 1;
        private transient int n = 2;
        private B b;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    /**
     * 测试写入
     * 需要 public 需要 get set 都存在（符合属性标准）
     */
    public static void main(String[] args) {
        try (
                XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("out.xml")))
        ) {
            Test o = new Test();
            o.setNumber(22);
            B b = new B();
            b.setN(1);
            o.setB(b);
            encoder.writeObject(o);
            System.out.println("写入成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (
                XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("out.xml")))
        ) {
            Test o = (Test) decoder.readObject();
            System.out.println("读取成功 " + o.getB().getN());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
