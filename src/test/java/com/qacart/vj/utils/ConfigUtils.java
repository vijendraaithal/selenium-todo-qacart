package com.qacart.vj.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtils {
    private static ConfigUtils configUtils;
    private Properties properties;

    private ConfigUtils() {
        properties = readProperties();
    }

    public static ConfigUtils getInstance() {
        if(configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return  configUtils;
    }

    private Properties readProperties() {
        FileInputStream fileInputStream;
        try {
            String env = System.getProperty("env", "PRODUCTION");
            switch(env.toUpperCase()) {
                case "PRODUCTION" -> fileInputStream = new FileInputStream("src/test/resources/env/production.properties");
                case "LOCAL" -> fileInputStream = new FileInputStream("src/test/resources/env/local.properties");
                default ->  throw new RuntimeException(" Env Not Found ");
            }
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    public String getBaseUrl() {
        return properties.get("URL").toString();
    }
}