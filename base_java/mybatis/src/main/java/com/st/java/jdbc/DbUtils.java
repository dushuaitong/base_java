package com.st.java.jdbc;

import java.io.InputStream;
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


}
