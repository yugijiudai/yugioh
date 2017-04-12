package com.practice;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-22
 */
public class TestPutAndTake {

    private static final ExecutorService exe = Executors.newCachedThreadPool();

    private int size = 3;

    //当全部线程进入栅栏后,栅栏就可以重新使用，所以这里容量是2 * size + 1
    private final CyclicBarrier barrier = new CyclicBarrier(2 * size + 1, new Runnable() {
        @Override
        public void run() {
            System.out.println("起跑啦!");
        }
    });

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":准备生产");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + ":开始生产");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + ":生产完成");
                barrier.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":准备消费");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + ":开始消费");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + ":消费完成");
                barrier.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < size; i++) {
            exe.execute(new Producer());
            exe.execute(new Consumer());
        }
        barrier.await();
        System.out.println("主线程等待");
        barrier.await();
        System.out.println("finish");
        exe.shutdown();
    }
}
