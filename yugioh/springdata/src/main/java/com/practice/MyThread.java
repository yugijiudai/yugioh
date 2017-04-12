package com.practice;

import org.apache.ibatis.executor.ReuseExecutor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-13
 */
public class MyThread extends Thread {

    private static final String DEFAULT_NAME = "麻痹!";

    private static final AtomicInteger CREATED = new AtomicInteger();

    private static final AtomicInteger ALIVE = new AtomicInteger();

    public MyThread(Runnable target) {
        this(target, DEFAULT_NAME);
    }

    public MyThread(Runnable target, String name) {
        super(target, name + "-" + CREATED.incrementAndGet());
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "-" + e.getMessage());
            }
        });
    }

    @Override
    public void run() {
        try {
            ALIVE.incrementAndGet();
            System.out.println(this.getName());
            super.run();
        }
        finally {
            ALIVE.decrementAndGet();
        }
    }

    public static int getThreadCreated() {
        return CREATED.get();
    }

    public static int getAlive() {
        return ALIVE.get();
    }
}
