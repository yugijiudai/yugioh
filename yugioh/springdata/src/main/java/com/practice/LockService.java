package com.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/1/8.
 */
public class LockService {


    public void update1(Connection connection){
        String sql = "select deptno,name from dept where deptno = 3 for update";
        String update = "update dept set name = 'f1' where deptno= 3";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt("deptno"));
                System.out.println(rs.getString("name"));
            }
            for (long i = 0L; i < 10000000000L; i++) {

            }
            System.out.println("开始更新1");
            psmt = connection.prepareStatement(update);
            psmt.executeUpdate();
            System.out.println("结束更新1");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update2(Connection connection){
        String sql = "select deptno,name from dept where deptno = 3 for update";
        String update = "update dept set name = 'f2' where deptno = 3";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt("deptno"));
                System.out.println(rs.getString("name"));
            }
            for (int i = 0; i < 100; i++) {

            }
            System.out.println("开始更新2");
            psmt = connection.prepareStatement(update);
            psmt.executeUpdate();
            System.out.println("结束更新2");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock(Connection connection){
        //整一行都锁
        String sql = "select deptno,name from dept where deptno = 3 for update";
        String update = "update dept set name = 'lock1' where deptno = 3";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(sql);
            psmt.executeQuery();
            psmt = connection.prepareStatement(update);
            psmt.executeUpdate();
            Thread.sleep(8000);
            Integer.parseInt("fefe");
//            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock2(Connection connection){
        String update = "update dept set name = 'lock2' where deptno = 3";
        PreparedStatement psmt;
        try {
            psmt = connection.prepareStatement(update);
            psmt.executeUpdate();
            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
