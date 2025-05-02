package utilities.datautil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
    static Properties props;

    public static void setProperty(String filePath,String key, String value) {
        props=new Properties();
        props.setProperty(key, value);
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            props.store(fos, "Updated key: " + key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String filePath,String key) {
        props=new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props.getProperty(key);
    }

    /**
     * Reads properties from a file and returns them as a Map.
     *
     * @param filePath the path to the properties file
     * @return a Map containing the properties
     */
    public static Map<String, String> loadPropertiesAsMaps(String filePath) {
        Properties properties = new Properties();
        Map<String, String> propertiesMap = new HashMap<>();

        try (InputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            for (String key : properties.stringPropertyNames()) {
                propertiesMap.put(key, properties.getProperty(key)); // Store as Object (String)
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from " + filePath, e);
        }
        return propertiesMap;
    }
}
