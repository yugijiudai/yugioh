package com;

import com.practice.JDBCUtil;
import com.practice.ThreadService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Administrator on 2016/3/19.
 */
public class ThreadServiceTest {

    private Connection connection;
    private int size = 10;
    private Thread threads[] = new Thread[size];

    @Before
    public void setUp() throws Exception {
        connection = JDBCUtil.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        System.out.println("结束");
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        final ThreadService service = new ThreadService();
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread() {
                public void run() {
                    try {
                        //要同一个service才对synchronized方法有效
                        service.update(connection);
                    }
                    catch (Exception e) {
                    }
                }
            };
        }
        for (int i = 0; i < size; i++)
            threads[i].start();
    }

    @Test
    public void testUpdate2() throws Exception {
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread() {
                public void run() {
                    try {
                        //不同对象
                        ThreadService service = new ThreadService();
                        service.update2(connection);
                    }
                    catch (Exception e) {
                    }
                }
            };
        }
        for (int i = 0; i < size; i++)
            threads[i].start();
    }
}