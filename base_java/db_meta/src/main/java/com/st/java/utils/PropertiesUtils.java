package com.st.java.utils;

import com.st.java.cfg.DBMetaConfig;
import com.st.java.constant.DBMetaConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

/**
 * @author dushuaitong
 */
public class PropertiesUtils {

    private PropertiesUtils() {

    }

    public static Properties loadProperties(String propertiesPath) throws IOException {
        Properties properties = new Properties();
        URL url = DBMetaConfig.class.getResource(propertiesPath);
        File file = new File(url.getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        fileInputStream.close();
        return properties;
    }

    public static String getProp(String propName, Properties properties) throws IllegalAccessException {
        if (Objects.isNull(properties)) {
            throw new IllegalAccessException("【properties】error");
        }
        if (!properties.containsKey(propName)) {
            throw new IllegalAccessException("【propName】error");
        }
        return properties.getProperty(propName);
    }
}
