package com.practice;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yugi
 * @apiNote 7.17 通过“毒丸”对象来关闭服务
 * @since 2017-03-16
 */
public class IndexingService {

    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private AtomicInteger consumerCount = new AtomicInteger();
    private AtomicInteger producerCount = new AtomicInteger();
    private final BlockingQueue<File> queue;
    //private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<File>(CAPACITY);

    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    //生产者
    class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            }
            catch (InterruptedException e) { /* fall through */
                // producerCount.decrementAndGet();
                // e.printStackTrace();
            }
            finally {
                while (true) {
                    try {
                        System.out.println("放入“毒丸”,总共放入:" + producerCount.get());
                        queue.put(POISON);
                        break;
                    }
                    catch (InterruptedException e1) { /* retry */
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    }
                    else if (!alreadyIndexed(entry)) {
                        //如果调用cancel,这时生产者最后放入队列中的东西消费者有可能会拿不到(前提是生产者要生产的物品没有全部生产完,即还没有对所有文件进行索引完)
                        // 这里先放入队列再自增和输出,如果放入队列成功后被取消,这样可以确保消费者能取到毒丸前的这个东西
                        queue.put(entry);
                        producerCount.getAndIncrement();
                        System.out.println("放入生产者队列文件：" + entry.getName() + " 来自线程：" + Thread.currentThread().getName());
                    }
                }
            }
        }
    }

    //消费者
    class IndexerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON) {
                        System.out.println("遇到“毒丸”，终止,总共处理:" + consumerCount.get());
                        break;
                    }
                    else {
                        indexFile(file);
                    }
                }
            }
            catch (InterruptedException consumed) {
            }
        }

        public void indexFile(File file) {
            System.out.println("消费者取出文件：" + file.getName() + " 来自线程：" + Thread.currentThread().getName());
            consumerCount.getAndIncrement();
        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException, ExecutionException {
        consumer.join();
    }

}