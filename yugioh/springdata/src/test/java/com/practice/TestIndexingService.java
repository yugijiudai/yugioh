package com.practice;

import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-16
 */
public class TestIndexingService {

    @Test
    public void testPoison() throws InterruptedException, ExecutionException {
        File file = new File("F://我的图片/");
        IndexingService c = new IndexingService(file);
        c.start();
        try {
            TimeUnit.MICROSECONDS.sleep(6000);// 停止ＸＸ时间，显示出消费速度慢于生产速度　
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.stop();
        c.awaitTermination();
    }
}
