package com.practice;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-07
 */
public class TestMe {


    @Test
    public void test() throws Exception {
        Callable<String> callable = () -> {
            Thread.sleep(5000L);
            return "444";
        };
        FutureTask<String> ft = new FutureTask<>(callable);
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始获取");
                System.out.println(ft.get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
        // ft.run();
        Thread thread1 = new Thread(task);
        thread1.start();
        Thread thread2 = new Thread(task);
        thread2.start();
        new Thread(ft).start();
        thread1.join();
        thread2.join();
    }

    public static void main(String[] args) throws Exception {

    }
}
