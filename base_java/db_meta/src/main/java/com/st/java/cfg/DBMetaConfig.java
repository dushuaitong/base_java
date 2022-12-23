package com.st.java.cfg;

import com.st.java.constant.DBMetaConstant;
import com.st.java.utils.PropertiesUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class DBMetaConfig {
    private Properties properties;
    private String userName;
    private String password;
    private String url;

    public DBMetaConfig() {
        try {
            properties = PropertiesUtils.loadProperties("/" + DBMetaConstant.DEFAULT_CFG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initProperties();
    }

    private void initProperties() {
        try {
            this.userName = PropertiesUtils.getProp(DBMetaConstant.userName, properties);
            this.password = PropertiesUtils.getProp(DBMetaConstant.password, properties);
            this.url = PropertiesUtils.getProp(DBMetaConstant.url, properties);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
