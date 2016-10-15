package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2016/1/8.
 */
public class JDBCUtil {


    public static Connection getConnection() {
        Connection con = null;
        try {
            //加载MySql的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/zhong";
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
