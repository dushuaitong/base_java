package com.st.java.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class DbUtils {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;

    static {
        try (InputStream is = DbUtils.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driverClassName = properties.getProperty("driverClassName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean update(String sql, Object... args) throws Exception {
        Class.forName(driverClassName);
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    public static <T> List<T> select(String sql, Mapper<T> mapper, Object... args) throws Exception {
        if (mapper == null) {
            return null;
        }
        Class.forName(driverClassName);
        List<T> res = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            ResultSet result = pstmt.executeQuery();
           for (int i = 0; result.next(); i++) {
               res.add(mapper.map(result, i));
           }
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public interface Mapper<T> {
        T map(ResultSet rs, int row) throws Exception;
    }
}
