package com;

import com.practice.JDBCUtil;
import com.practice.LockService;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Administrator on 2016/1/8.
 */
public class MyTest {

    @Test
    public void test() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        connection2.setAutoCommit(false);
        Update1 update1 = new Update1(connection);
        Update2 update2 = new Update2(connection2);
        update1.start();
        Thread.sleep(1000);
        update2.start();
        update2.join();
        System.out.println("完成");
    }

    @Test
    public void testLock() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        LockService service = new LockService();
        System.out.println("开始");
        service.lock(connection);
        System.out.println("完成");
    }


    @Test
    public void testLock2() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        LockService service = new LockService();
        System.out.println("开始");
        service.lock2(connection);
        System.out.println("完成");
    }

    private boolean fun1(String abc) {
        System.out.print(abc);
        return true;
    }

    @Test
    public void test3() {
        int i = 0;
        for (fun1("A"); fun1("B") && i < 2; fun1("C")) {
            System.out.print("D");
            i++;
        }
    }

    class Update1 extends Thread {
        Connection connection;

        public Update1(Connection connection) {
            this.connection = connection;
        }

        public void run() {
            LockService lock = new LockService();
            try {
                lock.update1(connection);
                connection.commit();
            }
            catch (Exception e) {
            }
        }
    }


    class Update2 extends Thread {
        Connection connection;

        public Update2(Connection connection) {
            this.connection = connection;
        }

        public void run() {
            LockService lock = new LockService();
            try {
                lock.update2(connection);
                connection.commit();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
