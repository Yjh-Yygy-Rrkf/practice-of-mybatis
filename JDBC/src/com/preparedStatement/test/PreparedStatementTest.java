package com.preparedStatement.test;

import com.jdbc.util.JDBCUtils;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;



public class PreparedStatementTest {
    @Test
    public void testInsert(){
//1.加载配置文件
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
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
            conn = DriverManager.getConnection(url,user,password);
            System.out.println(conn);


            String sql = "insert into customers(name, email, birth) values (?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, "蔡徐琨");
            ps.setString(2, "27942@outlook.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2002-06-14");
            ps.setDate(3, new java.sql.Date(date.getTime()));

            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testUpdate() throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1, "刘文哲");
            ps.setObject(2, 18);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn, ps);
        }

    }
}
