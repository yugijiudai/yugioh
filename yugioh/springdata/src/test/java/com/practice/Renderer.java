package com.practice;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-08
 */
public class Renderer  {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void timeOut() {
        Future<Long> submit = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Thread.sleep(4000L);
                return 1L;
            }
        });
        try {
            Long result = submit.get(200, TimeUnit.MILLISECONDS);
            System.out.println(result);
        }
        catch (InterruptedException e) {
            System.out.println("中断");
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (TimeoutException e) {
            System.out.println("超时");
            submit.cancel(true);
            e.printStackTrace();
        }
    }

    public void cal() {
        //通过ExecutorCompletionService执行任务,Future调用get()方法时候就算设置等待时间也无效!!!
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 5; i++) {
            completionService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Thread.sleep(200L);
                    return 2L;
                }
            });
        }
        for (int i = 0; i < 5; i++) {
            // Future<Long> future = null;
            try {
                //这里take会从阻塞队列里取结果,take会等待任务直到完成,无论get配置不配置时间等待都没用,如果要设置等待时间只能用poll,如果超时的话,future会返回空
                // Future<Long> future = completionService.take();
                Future<Long> future = completionService.poll(500, TimeUnit.MILLISECONDS);
                Long result = future.get(100L, TimeUnit.MILLISECONDS);
                System.out.println(result);

            }
            catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException");
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
            catch (TimeoutException e) {
                System.out.println("超时");
                // if (future != null) {
                //     future.cancel(true);
                // }
            }

        }
    }

    @Test
    public void test() {
        this.cal();
    }


    @Test
    public void test2() {
        this.timeOut();
    }


}
