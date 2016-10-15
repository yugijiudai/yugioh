package com.practice;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/6/27.
 */
public class NoVisibility {

    private static AtomicInteger atomicInteger = new AtomicInteger(4);

    public static  AtomicReference<BigInteger> atomicReference;

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.print(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
        System.out.println(atomicInteger.getAndIncrement());
    }
}
