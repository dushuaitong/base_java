package com.st.java.springtemplete;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class SpringTemplateUtils {
    private static JdbcTemplate tpl;

    static {
        try (InputStream is = SpringTemplateUtils.class.getClassLoader().getResourceAsStream("druid.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            tpl = new JdbcTemplate(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcTemplate getTpl() {
        return tpl;
    }
}
