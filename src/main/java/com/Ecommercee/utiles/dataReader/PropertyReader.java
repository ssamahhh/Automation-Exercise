package com.Ecommercee.utiles.dataReader;

import com.Ecommercee.utiles.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {

    public final static String ENVIRONMENT_PATH = "src/test/resources/test-data/environment.properties";

    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();

            Collection<File> propertiesFiles =
                    FileUtils.listFiles(
                            new File("src/main/resources"),
                            new String[]{"properties"},
                            true);

            for (File file : propertiesFiles) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                }
            }

            // مهم جداً: تحميل في System properties
            System.getProperties().putAll(properties);

            return properties;

        } catch (Exception e) {
            logsManager.error("Error loading properties", e.getMessage());
            return null;
        }
    }


    public static String getProperty(String key) {
        try {
            String value = System.getProperty(key);

            if (value == null) {
                loadProperties(); // fallback
                value = System.getProperty(key);
            }

            return value;

        } catch (Exception e) {
            logsManager.error("Error getting property for key: " + key, e.getMessage());
            return "";
        }
    }

    public static String getEnvironmentPropertyValue(String key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(ENVIRONMENT_PATH));
            return properties.getProperty(key);
        } catch (Exception e) {
            logsManager.error(e.getMessage());
            return "";
        }

    }
}

