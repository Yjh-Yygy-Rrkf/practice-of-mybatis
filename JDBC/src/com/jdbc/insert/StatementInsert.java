package com.jdbc.insert;

import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

public class StatementInsert {
    @Test
    public void insert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Statement st = conn.createStatement();
        for(int i = 1;i <= 20000;i++){
//            String sql = "insert into goods(name) values('name_' + "+ i +")";
            String sql = "insert into goods(name) values('name_ "+i+"')";
            st.executeUpdate(sql);
        }
    }
}
