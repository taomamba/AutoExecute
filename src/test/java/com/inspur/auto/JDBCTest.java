package com.inspur.auto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        // 初始化

            // 不同的数据库有不同的驱动
            String driverName = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://192.168.171.151:3306/aotoExcute?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
            String user = "taotao";
            String password = "123456";
            try {
                // 加载驱动
                Class.forName(driverName);
                // 设置 配置数据
                // 1.url(数据看服务器的ip地址 数据库服务端口号 数据库实例)
                // 2.user
                // 3.password
                Connection conn = DriverManager.getConnection(url, user, password);
                if (conn!=null){
                    // 开始连接数据库
                    System.out.println("数据库连接成功..");
                }
                conn.close();

            } catch (ClassNotFoundException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
