package com.keking.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 * <p>
 * Created by shiyanfei on 2018-03-14.
 */
public class PropertiesUtil {

    private static final String PATH_PROPERTIES = "config/path.properties";

    /**
     * 读取配置properties文件中key值对应的value值
     *
     * @param key properties的key值
     * @return properties的value值
     */
    public static String getPropertis(String key) {
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PATH_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getPropertis("tms_project_path"));
    }

}
