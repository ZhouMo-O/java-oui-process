package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

public class Transaction {

    private Connection connect() {
        String url = "jdbc:sqlite:D:/xiaowu/ProjectForHBuiderl/javaOui/db/data/learn.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addInventory(Map<String, String> ouidata) {
        String sqlInventory = "INSERT INTO warehouses(key,inc)" + "VALUES(?,?)";

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = this.connect();
            if (conn == null)
                return;

            conn.setAutoCommit(false);

            pstmt2 = conn.prepareStatement(sqlInventory);
            Iterator<String> iterator = ouidata.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next(); // 获取key
                String value = ouidata.get(key).toString(); // 获取value
                pstmt2.setString(1, key);
                pstmt2.setString(2, value);
                pstmt2.executeUpdate();
            }
            conn.commit();

        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }
    }

    public static void main(String[] argv) {
        // Transaction app = new Transaction();

    }
}