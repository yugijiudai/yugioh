package com.practice;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ThreadDebug {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(null, new MyThread(1, 1), "Thread-1");
        Thread thread2 = new Thread(null, new MyThread(2, 2), "Thread-2");
        thread1.start();
        thread2.start();
    }

    static class MyThread implements Runnable {
        private Object obj1;
        private Object obj2;

        MyThread(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        /**
         * 将不需要观察的线程点击resume,让他走下去,剩下需要观察的就可以慢慢断点
         */
        @Override
        public void run() {
            System.out.println("Let's try:" + obj1);
            synchronized (obj1) {
                System.out.println("Acquired 1st lock:" + obj1);
                synchronized (obj2) {
                    System.out.println("Acquired 2nd lock:" + obj1);
                }
                System.out.println("Released 2nd lock:" + obj1);
            }
            System.out.println("Released 1st lock:" + obj1);
        }
    }

}
