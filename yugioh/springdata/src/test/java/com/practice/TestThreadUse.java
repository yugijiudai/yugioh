package com.practice;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-13
 */
public class TestThreadUse {
    ExecutorService exec = Executors.newFixedThreadPool(3);

    private class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "header";
                }
            });
            footer = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "footer";
                }
            });
            return header.get() + "-" + footer.get();
        }
    }


    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        Future<String> submit = ex.submit(new RenderPageTask());
        String s = submit.get();
        System.out.println(s);
        System.out.println(Runtime.getRuntime().availableProcessors());
        ex.shutdown();
    }

    @Test
    public void testTimingThreadPool() throws ExecutionException, InterruptedException {
        TimingThreadPool test = new TimingThreadPool(2, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        Future<Object> submit = test.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return 1;
            }
        });
        test.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        //如果用main函数跑,不shutdown会一直运行下去
        test.shutdown();
        test.awaitTermination(2L, TimeUnit.SECONDS);
        System.out.println(submit.get());
    }


    @Test
    public void MyThreadFactory() throws ExecutionException, InterruptedException {
        MyThreadFactory threadFactory = new MyThreadFactory("爱勃池");
        ExecutorService ex = Executors.newFixedThreadPool(3, threadFactory);
        Future<Object> submit = ex.submit(() -> "返回1");
        Future<Object> submit2 = ex.submit(() -> "返回2");
        System.out.println(MyThread.getThreadCreated());
        System.out.println(submit.get());
        System.out.println(submit2.get());
        ex.shutdown();
    }

    @Test
    public void testGui(){
        GuiExecutor instance = GuiExecutor.getInstance();
        instance.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完");
            }
        });
        System.out.println("fin");
    }
}



