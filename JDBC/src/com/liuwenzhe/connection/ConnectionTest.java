package com.liuwenzhe.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest{
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    @Test
    public void testConnection2() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    @Test
    public void testConnection3() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";

        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws Exception{

        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        String driverName = "com.mysql.jdbc.Driver";

        //2.加载驱动 （①实例化Driver ②注册驱动）
//        Class.forName(driverName);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }

    @Test
    public  void testConnection5() throws Exception {
        //1.加载配置文件
//        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        //2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //3.加载驱动
        Class.forName(driverClass);

        //4.获取连接
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);

    }
}
