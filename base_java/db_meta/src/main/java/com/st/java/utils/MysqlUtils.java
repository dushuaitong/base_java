package com.st.java.utils;

import com.st.java.cfg.DBMetaConfig;
import com.st.java.constant.DBMetaConstant;

import java.sql.*;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class MysqlUtils {
    public static Connection getConnection(DBMetaConfig dbMetaConfig) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = new Properties();
            // mysql获取表注释需要加上这个属性
            props.put("useInformationSchema", "true");
            props.put("user", dbMetaConfig.getUserName());
            props.put("password", dbMetaConfig.getPassword());
            return DriverManager.getConnection(dbMetaConfig.getUrl(),props);
        } catch (Exception e) {
            return null;
        }
    }

    public static DatabaseMetaData getMetaData(Connection connection) throws SQLException {
        return connection.getMetaData();
    }

    public static String getColumnName(ResultSet column) throws SQLException {
        return column.getString(DBMetaConstant.COLUMN_NAME);
    }

    public static String getColumnType(ResultSet column) throws SQLException {
        return column.getString(DBMetaConstant.TYPE_NAME);
    }

    public static int getColumnSize(ResultSet column) throws SQLException {
        return column.getInt(DBMetaConstant.COLUMN_SIZE);
    }

    public static int getColumnDecimalDigits(ResultSet column) throws SQLException {
        return column.getInt(DBMetaConstant.DECIMAL_DIGITS);
    }

    public static int getNullable(ResultSet column) throws SQLException {
        return column.getInt(DBMetaConstant.NULLABLE);
    }

    public static String getRemarks(ResultSet column) throws SQLException {
        return column.getString(DBMetaConstant.REMARKS);
    }
}
