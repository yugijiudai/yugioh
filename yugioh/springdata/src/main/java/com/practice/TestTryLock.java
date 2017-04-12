package com.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-24
 */
public class TestTryLock {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private int a = 0;

    public void putCondition() throws InterruptedException {
        lock.lock();
        try {
            while (a >= 2) {
                System.out.println("等待put");
                notFull.await();
            }
            a++;
            System.out.println("放入:" + a);
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }


    public void takeCondition() throws InterruptedException {
        lock.lock();
        try {
            while (a == 0) {
                System.out.println("等待take");
                notEmpty.await();
            }
            a--;
            System.out.println("取出:" + a);
            notFull.signal();
        }
        finally {
            lock.unlock();
        }
    }


    public synchronized void put() throws InterruptedException {
        while (a >= 2) {
            System.out.println("等待put");
            wait();
        }
        a++;
        System.out.println("放入:" + a);
        notifyAll();
    }


    public synchronized void take() throws InterruptedException {
        while (a == 0) {
            System.out.println("等待take");
            wait();
        }
        a--;
        System.out.println("取出:" + a);
        notifyAll();
    }


    public boolean doSomeThing() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + "开始获取锁");
            if (lock.tryLock(2000, TimeUnit.NANOSECONDS)) {
                Thread.sleep(5000L);
                System.out.println(name + "睡眠完成");
                return true;
            }
            else {
                System.out.println(name + "放弃");
                return false;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            System.out.println(name + "释放锁");
            lock.unlock();
        }
    }

    public void doSomeThing2() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + "开始获取锁");
            lock.lockInterruptibly();
            System.out.println(name + "获取锁");
            Thread.sleep(5000L);
            System.out.println(name + "睡眠完成");
        }
        catch (InterruptedException e) {
            System.out.println(name + "被中断");
        }
        finally {
            System.out.println(name + "释放锁");
            lock.unlock();
        }
    }

}
