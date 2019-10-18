package com.inspur.auto;

import com.ibm.db2.jcc.am.Connection;
import com.ibm.db2.jcc.am.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db2JDBCTest {

    /**
     * 设置参数
     **/
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    /**
     * 构造方法，链接数据库
     **/
    public void DB2conn() {
        try {
            System.out.println("正在连接数据库..........");
            Class.forName("com.ibm.db2.jcc.DB2Driver");//加载mysql驱动程序类
            String url = "jdbc:db2://10.1.80.240:50002/v6db2";//url为连接字符串
            String user = "db2inst1";//数据库用户名
            String pwd = "xsgs@&#)";//数据库密码
            conn = (Connection) DriverManager.getConnection(url, user, pwd);
            if (null != conn) {
                System.out.println("数据库连接成功！！！");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Db2JDBCTest a = new Db2JDBCTest();//实例化对象，作用是调用构造方法
        a.DB2conn();
        conn.close();//先定义，后关闭
    }
}
