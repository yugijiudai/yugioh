package com.practice;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Syn {
    static Thread a, b;
    static Object i = 0;

    public static void make() {
        //b是对象锁,要与wait对应,wait和notify要放在synchronized里面使用
        synchronized (b) {
            b.notify();
        }

    }

    public static void main(String[] args) {
        a = new Thread() {
            @Override
            public void run() {
                try {
                    a.sleep(1000);
                }
                catch (Exception e) {
                    System.out.println("A");
                    return;
                }
                System.out.println('B');
                make();
            }
        };

        b = new Thread() {
            @Override
            public void run() {
                //b是对象锁
                synchronized (b) {
                    System.out.println("加锁");
                    try {
                        b.wait();
                        System.out.println("唤醒");
                    }
                    catch (Exception e) {
                        System.out.println("C");
                        e.printStackTrace();
                        return;
                    }
                    System.out.println("D");
                }
            }

        };
        a.start();
        System.out.println("start");
        b.start();
    }
}
