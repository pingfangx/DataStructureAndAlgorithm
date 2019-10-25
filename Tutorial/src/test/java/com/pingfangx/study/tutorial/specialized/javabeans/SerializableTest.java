package com.pingfangx.study.tutorial.specialized.javabeans;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * @author pingfangx
 * @date 2019/10/25
 */
public class SerializableTest {
    static class TransientTest implements Serializable {
        private static final long serialVersionUID = 4694968213664509029L;
        private int a = 1;
        private int b = 2;
        private transient int c = 3;
    }

    static class WriteObjectTest implements Serializable {
        private static final long serialVersionUID = 6948064813337295035L;
        private int a = 1;
        private int b = 2;
        private transient int c = 3;

        public WriteObjectTest() {
            System.out.println("初始化");
        }

        private void writeObject(ObjectOutputStream s) throws IOException {
            s.defaultWriteObject();
            s.writeInt(a);
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            System.out.println("默认读完" + a);
            System.out.println("再读一次" + s.readInt());
        }
    }

    @Test
    public void test_transient() {
        String fileName = "f1.ser";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            TransientTest obj = new TransientTest();
            obj.b = 22;
            obj.c = 33;
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            TransientTest obj = (TransientTest) objectInputStream.readObject();
            System.out.println("读取完成");
            //初始化为1
            Assert.assertEquals(1, obj.a);
            //因为字段已经被赋值为 22
            Assert.assertEquals(22, obj.b);
            //注意，虽然初始化为 3，但是序列化没有写入值
            Assert.assertEquals(0, obj.c);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_write() {
        String fileName = "f2.ser";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            WriteObjectTest obj = new WriteObjectTest();
            obj.b = 22;
            obj.c = 33;
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            WriteObjectTest obj = (WriteObjectTest) objectInputStream.readObject();
            System.out.println("读取完成");
            Assert.assertEquals(1, obj.a);
            //虽然 writeObject 没有显示写入，但 defaultWriteObject 和 defaultReadObject 完成了
            Assert.assertEquals(22, obj.b);
            //注意，虽然初始化为 3，但是序列化没有写入值
            Assert.assertEquals(0, obj.c);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
