package com.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2016/3/18.
 */
public class ThreadService {

    private static byte[] lock = new byte[0];

    /**
     * synchronized方法和synchronized(this)效果一样
     *
     * @param connection
     */
    public synchronized void update(Connection connection) {
        String update = "update test set val = ? where id = 1";
        String sql = "select val from test where id = 1 ";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                int val = rs.getInt(1);
                if (val > 0) {
                    val--;
                    psmt = connection.prepareStatement(update);
                    psmt.setInt(1, val);
                    psmt.executeUpdate();
                    psmt = connection.prepareStatement(sql);
                    rs = psmt.executeQuery();
                    if (rs.next()) {
                        System.out.print(val + ",");
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }

    public void update2(Connection connection) {
        String update = "update test set val = ? where id = 1";
        String sql = "select val from test where id = 1 ";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(sql);
            //如果要保持即使不同对象也只能有一个线程来访问可以创建一个特殊的instance变量（它得是一个对象）来充当锁
            //用byte数组对象比Object Object lock = new Object() 高效  注意这个得是static的，让不同对象竞争同一个 byte数组对象的锁
            //效果相当于synchronized (ThreadService.class)
            synchronized (lock) {
//            synchronized (ThreadService.class) {
                System.out.print(Thread.currentThread().getName() + ",");
                ResultSet rs = psmt.executeQuery();
                if (rs.next()) {
                    int val = rs.getInt(1);
                    if (val > 0) {
                        val--;
                        psmt = connection.prepareStatement(update);
                        psmt.setInt(1, val);
                        psmt.executeUpdate();
                        psmt = connection.prepareStatement(sql);
                        rs = psmt.executeQuery();
                        if (rs.next()) {
                            System.out.println(val + ",");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }


}
