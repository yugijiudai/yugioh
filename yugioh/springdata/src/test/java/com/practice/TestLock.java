package com.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-24
 */
public class TestLock {
    private ExecutorService exec = Executors.newCachedThreadPool();
    private TestTryLock testTryLock = new TestTryLock();

    @Test
    public void testTryLock() throws InterruptedException, ExecutionException {
        Future submit = exec.submit((Callable<Object>) () -> testTryLock.doSomeThing());
        Thread.sleep(1000L);
        Future submit2 = exec.submit((Callable<Object>) () -> testTryLock.doSomeThing());
        System.out.println(submit.get());
        try {
            System.out.println(submit2.get());
            System.out.println("呵呵");
        }
        catch (InterruptedException e) {
            System.out.println(1);
        }
        catch (ExecutionException e) {
            System.out.println(2);
        }
        // submit2.get()会报错
        // System.out.println(submit2.get());
        exec.shutdown();
        System.out.println("完成");
    }

    @Test
    public void testLockInterrupt() throws InterruptedException, ExecutionException {
        TestTryLock testTryLock = new TestTryLock();
        Future submit = exec.submit((Callable<Object>) () -> {
            testTryLock.doSomeThing2();
            return true;
        });
        Thread.sleep(1000L);
        Future submit2 = exec.submit((Callable<Object>) () -> {
            testTryLock.doSomeThing2();
            return true;
        });
        Thread.sleep(1000L);
        submit2.cancel(true);
        System.out.println(submit.get());
        if (!submit2.isCancelled()) {
            System.out.println(submit2.get());
        }
        exec.shutdown();
        System.out.println("完成");
    }


    @Test
    public void testPutAndTake() throws InterruptedException, ExecutionException {
        TestTryLock testTryLock = new TestTryLock();
        List<Future<Object>> list = new ArrayList<>();
        //2个取出
        for (int i = 0; i < 2; i++) {
            Future<Object> submit = exec.submit(() -> {
                testTryLock.take();
                return true;
            });
            list.add(submit);
        }
        Thread.sleep(1000);
        //5个放入
        for (int i = 0; i < 5; i++) {
            Future<Object> submit = exec.submit(() -> {
                testTryLock.put();
                return true;
            });
            list.add(submit);
        }

        // 5个放入2个取出后，当放入第3个会出现阻塞,这里等待5秒就是看阻塞，等待完成取出一个，最后一个就可以放进去
        exec.awaitTermination(5, TimeUnit.SECONDS);
        testTryLock.take();
        for (Future<Object> future : list) {
            future.get();
        }
        exec.shutdown();
        System.out.println("完成");
    }

    @Test
    public void testPutAndTakeCondition() throws InterruptedException, ExecutionException {
        TestTryLock testTryLock = new TestTryLock();
        List<Future<Object>> list = new ArrayList<>();
        //2个取出
        for (int i = 0; i < 2; i++) {
            Future<Object> submit = exec.submit(() -> {
                testTryLock.takeCondition();
                return true;
            });
            list.add(submit);
        }
        Thread.sleep(1000);
        //5个放入
        for (int i = 0; i < 5; i++) {
            Future<Object> submit = exec.submit(() -> {
                testTryLock.putCondition();
                return true;
            });
            list.add(submit);
        }

        // 5个放入2个取出后，当放入第3个会出现阻塞,这里等待5秒就是看阻塞，等待完成取出一个，最后一个就可以放进去
        exec.awaitTermination(5, TimeUnit.SECONDS);
        testTryLock.takeCondition();
        for (Future<Object> future : list) {
            future.get();
        }
        exec.shutdown();
        System.out.println("完成");
    }


}
