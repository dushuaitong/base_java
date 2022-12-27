package com.st.java.jdbc;

import java.sql.*;

/**
 * @author dushuaitong
 */
public class JDBC {
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.59.5:3306/test?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER_CLASS_NAME);
        testSelct();
        testInsert();
        testSelect01();
    }

    private static void testSelct() throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stamt = conn.createStatement()) {
             String sql = "SELECT id, name FROM user";
             ResultSet result = stamt.executeQuery(sql);

             while (result.next()) {
                 System.out.println(result.getInt("id"));
                 System.out.println(result.getString("name"));
             }
        }
    }

    private static void testInsert() throws Exception {
        String sql = "INSERT INTO user(name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "uu");
             int result = pstmt.executeUpdate();
             if (result > 0) {
                 System.out.println("insert success");
             }
        }
    }

    private static void testSelect01() throws Exception {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


