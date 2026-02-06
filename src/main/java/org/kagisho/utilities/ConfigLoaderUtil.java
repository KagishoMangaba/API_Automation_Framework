package org.kagisho.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoaderUtil {

    private static Properties properties;

    private ConfigLoaderUtil() {}

    public static Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() {
        try (InputStream input = ConfigLoaderUtil.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in resources!");
            }

            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }
}
