package com.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-15
 */
public class MyTask {

    public MyRun myRun = new MyRun();

    private class MyRun extends FutureTask<String> {
        private MyRun() {
            //这里使用拉姆达表达式可能会报错
            super(MyTask.this::compute);
        }

        @Override
        protected final void done() {
            // ExecutorService exec = Executors.newCachedThreadPool();
            // exec.execute(() -> {
            //     System.out.println("我完成啦");
            // });
            System.out.println(Thread.currentThread().getName() + ":我完成啦");
        }
    }

    public String get() throws ExecutionException, InterruptedException {
        return myRun.get();
    }

    public void cancel() {
        myRun.cancel(true);
    }

    public String compute() throws InterruptedException {
        int i = 0;
        while (!myRun.isCancelled() && i < 4) {
            i++;
            System.out.println(Thread.currentThread().getName() + ":执行任务......." + i);
            Thread.sleep(1000L);
        }
        return "fefe";
    }

}
