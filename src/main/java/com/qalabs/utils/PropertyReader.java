package com.qalabs.utils;

import com.qalabs.common.BaseElement;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lmrivas on 20/10/2017.
 */
public class PropertyReader {
    protected final static Logger logger = Logger.getLogger(PropertyReader.class);
    protected final static String rootPath = "src/main/resources/";

    public static String getProperty (String fileName, String name) {
        Properties properties = new Properties();
        InputStream input = null;
        String propertyValue = null;
        try {
            input = new FileInputStream(rootPath + fileName);
            properties.load(input);
            propertyValue = properties.getProperty(name);
        } catch (IOException exception) {
            logger.debug(exception.toString());
        } finally {
            closeInput(input);
            return propertyValue;
        }
    }



    public static Map<String, String> getProperties (String fileName, List<String> propertiesNames) {
        Properties properties = new Properties();
        Map<String, String> values = new HashMap<String, String>();
        InputStream input = null;
        try {
            input = new FileInputStream(rootPath + fileName);
            properties.load(input);
            for(String name: propertiesNames)
                values.put(name, properties.getProperty(name));
        } catch (IOException exception) {
            logger.error(exception.toString());
        } finally {
            closeInput(input);
            return values;
        }
    }

    private static void closeInput(InputStream input) {
        try {
            if (input != null)
                input.close();
        } catch (IOException exception) {
            logger.error(exception.toString());
        }
    }
}
