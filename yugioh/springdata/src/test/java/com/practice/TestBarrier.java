package com.practice;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-06
 */
public class TestBarrier {

    private static final int THREAD_NUM = 5;

    public CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
        //当所有线程到达barrier时执行
        @Override
        public void run() {
            System.out.println("Inside Barrier");
        }
    });

    private class WorkerThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("ID:" + Thread.currentThread().getId() + " waiting");
                //线程在这里等待，直到所有线程都到达barrier。
                cb.await();
                System.out.println("ID:" + Thread.currentThread().getId() + " Working");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new WorkerThread()).start();
        }
    }

    @Test
    public void test1() {
        this.start();
    }

}
