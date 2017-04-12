package com.practice;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-22
 */
public class ExThreadFactory implements ThreadFactory {

    public static final AtomicInteger numCreated = new AtomicInteger();

    private final ThreadFactory factory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        numCreated.incrementAndGet();
        return factory.newThread(r);
    }
}
