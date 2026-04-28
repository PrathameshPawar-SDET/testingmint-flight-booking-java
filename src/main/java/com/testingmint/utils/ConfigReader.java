package com.testingmint.utils;

import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties from classpath");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
