package ua.com.epam.config;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Log4j2
public class PropertyFile {
    private final static String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static Properties properties;

    private PropertyFile() {
    }

    private static void loadProperties() {
        try (FileInputStream obj = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(obj);
        } catch (IOException e) {
            log.error("Exception, file is missing");
        }
    }

    public static String getProperty(String key) {
        if (Objects.isNull(properties)) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}
