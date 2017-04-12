package com.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-15
 */
public abstract class AbstractMyTask<V> implements Runnable, Future<V> {

    public MyRun myRun = new MyRun();

    @Override
    public void run() {
        myRun.run();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (mayInterruptIfRunning) {
            System.out.println("任务取消了");
        }
        return myRun.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return myRun.isCancelled();
    }

    @Override
    public boolean isDone() {
        return myRun.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return myRun.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return myRun.get(timeout, unit);
    }


    private class MyRun extends FutureTask<V> {
        private MyRun() {
            //这里使用拉姆达表达式可能会报错
            super(AbstractMyTask.this::compute);
        }

        @Override
        protected final void done() {
            // ExecutorService exec = Executors.newCachedThreadPool();
            // exec.execute(() -> {
            //     System.out.println("我完成啦");
            // });
            System.out.println("我完成啦");
        }
    }

    abstract protected V compute() throws InterruptedException;

}
