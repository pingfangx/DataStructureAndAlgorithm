package com.pingfangx.datastructure.book01.chapter03;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.*;

/**
 * 一开始不是太理解，仔细读了好几遍
 * 首先插入第一个到达事件
 * 从事件列表中取出事件并处理
 * 处理到达会生成下一到达事件
 * 处理离开会处理下一排队者
 *
 * @author pingfangx
 * @date 2017/11/9
 */
public class A_3_6_to_3_7 {
    public static void main(String[] args) {
        new A_3_6_to_3_7().simulation(100);
    }

    /**
     * 关闭时间
     */
    private int closeTime;
    private int mIntervalTime = 5;
    private int mDurationTime = 30;
    /**
     * 事件列表
     */
    private List<Event> ev;
    private Event en;
    /**
     * 队列列表
     */
    private List<Queue<QueueElementType>> queueList;
    private QueueElementType customer;

    private int totalTime;
    private int customerNumber;

    public void simulation(int closeTime) {
        this.closeTime = closeTime;
        openForDay();
        while (!ev.isEmpty()) {
            en = ev.get(ev.size() - 1);
            ev.remove(ev.size() - 1);
            if (en.nType == 0) {
                customerArrived();
            } else {
                customerDeparture();
            }
        }
        LogUtils.d("平均时间为 %f", (float) totalTime / customerNumber);
    }

    private void openForDay() {
        totalTime = 0;
        customerNumber = 0;
        ev = new ArrayList<>();
        //生成到达事件
        en = new Event(0, 0);
        orderInsert(ev, en);
        queueList = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            queueList.add(new ArrayDeque<>());
        }
    }

    private void customerArrived() {
        customerNumber++;
        Random random = new Random();
        //插入达到事件
        int interTime = random.nextInt(mIntervalTime) + 1;
        int t = en.occurTime + interTime;
        if (t < closeTime) {
            orderInsert(ev, new Event(t, 0));
        }
        int durTime = random.nextInt(mDurationTime) + 1;
        int i = minimumQuene(queueList);
        //排队
        enqueue(queueList.get(i), new QueueElementType(en.occurTime, durTime));
        if (queueList.get(i).size() == 1) {
            //若该队列在插入前为空，则还应产生产生一个客户离开事件插事件表
            //意思是说队列前没有别人，直接处理该用户，所以要生成离开
            //如果队列前有人，会在离开事件时生成离开
            orderInsert(ev, new Event(en.occurTime + durTime, i + 1));
        }
    }

    private void customerDeparture() {
        //类型减 1 为 index
        int i = en.nType - 1;
        //移除用户
        //这个 customer 的赋值，不会冲突吗
        //不会，后来发现一个是 remove 一个只是 peek
        customer = queueList.get(i).remove();
        totalTime += en.occurTime - customer.arrivalTime;
        if (!queueList.get(i).isEmpty()) {
            //队列中有人，将会处理他，生成他的离开事件
            customer = queueList.get(i).peek();
            orderInsert(ev, new Event(en.occurTime + customer.duration, i + 1));
        }
    }

    private void enqueue(Queue<QueueElementType> events, QueueElementType event) {
        events.add(event);
    }

    private int minimumQuene(List<Queue<QueueElementType>> queueList) {
        int min = 0;
        for (int i = 1; i < queueList.size(); i++) {
            if (queueList.get(i).size() < queueList.get(min).size()) {
                min = i;
            }
        }
        return min;
    }

    private void orderInsert(List<Event> eventList, Event event) {
        eventList.add(event);
        eventList.sort(Comparator.comparingInt(o -> -o.occurTime));
    }

    class Event {
        /**
         * 事件发生时刻
         */
        int occurTime;
        /**
         * 0 表示到达，1-4 表示对应窗口离开
         */
        int nType;

        public Event(int occurTime, int nType) {
            this.occurTime = occurTime;
            this.nType = nType;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "occurTime=" + occurTime +
                    ", nType=" + nType +
                    '}';
        }
    }

    class QueueElementType {
        int arrivalTime;
        int duration;

        public QueueElementType(int arrivalTime, int duration) {
            this.arrivalTime = arrivalTime;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "QueueElementType{" +
                    "arrivalTime=" + arrivalTime +
                    ", duration=" + duration +
                    '}';
        }
    }
}
