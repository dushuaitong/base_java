package com.st.java.utils;

import com.st.java.cfg.DBMetaConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class MysqlUtils {
    public Connection getConnection(DBMetaConfig dbMetaConfig) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = new Properties();
            //mysql获取表注释需要加上这个属性
            props.put("useInformationSchema", "true");
            props.put("user", dbMetaConfig.getUserName());
            props.put("password", dbMetaConfig.getPassword());
            return DriverManager.getConnection(dbMetaConfig.getUrl(),props);
        } catch (Exception e) {
            return null;
        }
    }

    public DatabaseMetaData getMetaData(Connection connection) throws SQLException {
        return connection.getMetaData();
    }
}
