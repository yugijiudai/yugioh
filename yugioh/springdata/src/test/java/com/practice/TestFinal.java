package com.practice;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-10
 */
public class TestFinal {

    private final ExecutorService exe = Executors.newFixedThreadPool(5);

    public void stopMe() {
        try {
            exe.shutdown();
            exe.awaitTermination(5000, TimeUnit.MILLISECONDS);
            System.out.println("等待线程关闭完成");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("最后关闭");
        }
    }

    public void handle(){
        exe.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行等待...");
                try {
                    Thread.sleep(3000L);
                    System.out.println("睡眠完成");
                }

                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                stopMe();
            }
        });
    }

    @Test
    public void tt() {
        this.handle();
        //这里会让程序结束,然后会执行关闭钩子
        System.exit(1);
        System.out.println("停止后");
    }
}
