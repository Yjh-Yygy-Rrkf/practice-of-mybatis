package com.jdbc.blob;

import com.jdbc.bean.Customers;
import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

public class BlobTest {
    @Test
    public void testInsert() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into customers(name, email, birth, photo)values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1,"刘文哲");
        ps.setObject(2,"liuwenzhe@qq.com");
        ps.setObject(3,"2002-06-14");
        FileInputStream photo = new FileInputStream(new File("8a17ce5e44541db74e3be7b59cbe1304601921c2.jpg@518w.webp"));
        ps.setBlob(4, photo);

        ps.execute();

        JDBCUtils.closeResource(connection, ps);
    }

    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        InputStream is = null;
        FileOutputStream maixiangji = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select name, email, birth, photo from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,14);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString(1);
                String email = resultSet.getString(2);
                Date brith = resultSet.getDate(3);

                Customers customers = new Customers(name, email, brith);
                System.out.println(customers);

                Blob blob = resultSet.getBlob(4);
                is = blob.getBinaryStream();
                maixiangji = new FileOutputStream("maixiangji");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    maixiangji.write(buffer, 0 , len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
            if(is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(maixiangji != null)
                    maixiangji.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection,ps, resultSet);
        }

    }
}
