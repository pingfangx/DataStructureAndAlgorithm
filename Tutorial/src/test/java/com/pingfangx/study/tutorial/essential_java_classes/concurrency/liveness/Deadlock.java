package com.pingfangx.study.tutorial.essential_java_classes.concurrency.liveness;

/**
 * 死锁
 * <p>
 * 要执行 bowBack 必须等待 bow 结束
 * 而 bow 则等待对方的 bowBack 结束才能结束
 *
 * @author pingfangx
 * @date 2019/3/12
 */
public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        private void threadMessage(String msg, Object... args) {
            String name = Thread.currentThread().getName();
            System.out.println(name + ":" + String.format(msg, args));
        }

        public synchronized void bow(Friend bower) {
            threadMessage("%s 向 %s 鞠躬", bower.getName(), name);
            threadMessage("%s 开始回鞠一个", name);
            bower.bowBack(this);
            threadMessage("完成");
        }

        public synchronized void bowBack(Friend bower) {
            threadMessage("%s 向 %s 回鞠一个躬", bower.getName(), name);
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}