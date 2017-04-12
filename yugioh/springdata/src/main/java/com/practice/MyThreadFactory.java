package com.practice;

import java.util.concurrent.ThreadFactory;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-13
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyThread(r, poolName);
    }


}
