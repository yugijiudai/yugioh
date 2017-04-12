package com.practice;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-22
 */
public class TestExThreadFactory {

    @Test
    public void testPoolExpansion() throws InterruptedException, ExecutionException {
        int MAX_SIZE = 10;
        ExThreadFactory factory = new ExThreadFactory();
        ExecutorService exe = Executors.newFixedThreadPool(MAX_SIZE, factory);
        for (int i = 0; i < 2 * MAX_SIZE; i++) {
            //如果线程创建容量超过指定的,会停止创建
            exe.execute(() -> {
                try {
                    Thread.sleep(2000L);
                    System.out.println("执行完");
                }
                catch (InterruptedException e) {
                    System.out.println("线程中断");
                    Thread.currentThread().interrupt();
                }
            });
        }
        for (int i = 0; i < 20 && ExThreadFactory.numCreated.get() < MAX_SIZE; i++) {
            Thread.sleep(100);
        }
        //断言最终只创建了10个了
        Assert.assertEquals(ExThreadFactory.numCreated.get(), MAX_SIZE);
        exe.shutdown();
        System.out.println("完成" + ExThreadFactory.numCreated.get());
    }
}
