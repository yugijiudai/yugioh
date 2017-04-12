package com.practice;

import org.junit.Test;

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-15
 */
public class TestMyTask {


    @Test
    public void testRun() throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask();
        MyTask myTask2 = new MyTask();
        ExecutorService exec = Executors.newCachedThreadPool();
        Future submit = exec.submit(myTask.myRun);
        Future submit2 = exec.submit(myTask2.myRun);
        Thread.sleep(2000L);
        // 如果取消,用submit.get()则返回是null,用myTask.get()则返回CancellationException,
        // 如果用myTask取消,线程是main线程,如果是submit取消,线程是对应的线程,想获取返回值要用MyTask的对象,而不能用submit
        // myTask.cancel();
        submit.cancel(true);
        if (submit.isCancelled()){
            System.out.println("任务1被取消");
        }
        System.out.println(myTask2.get());
        // Object o = submit.get();
        // System.out.println(o);
        // System.out.println(myTask.get());
    }


    @Test
    public void testF() throws InterruptedException {
        ConcurrentLinkedQueue<Object> concurrent = new ConcurrentLinkedQueue<>();
        LinkedBlockingQueue<Object> block = new LinkedBlockingQueue<>(1);
        block.put(1);
        block.put(2);

    }


}
