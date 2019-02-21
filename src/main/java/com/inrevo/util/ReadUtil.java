package com.inrevo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2018/5/25.
 */
public class ReadUtil {

    // 全局配置文件
    private static Properties properties = null;

    //读取配置值
    public static String get(String name) {
        if (properties == null) {
            properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = ReadUtil.class.getClassLoader().getResourceAsStream("application.properties");
            // 使用properties对象加载输入流
            try {
                properties.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(name);
    }


}
