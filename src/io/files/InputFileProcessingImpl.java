package io.files;

import constants.StandardConstants;
import net.url.URLProcessing;
import net.url.URLProcessingImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public class InputFileProcessingImpl implements InputFileProcessing {

    @Override
    public String readFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);

        } catch (FileNotFoundException e) {
            System.out.println("The input file not found");
            System.out.println("Enter a valid name and path for the input file");
            e.printStackTrace();
            Properties defaultProperties = loadDefaultProperties();
            return parseProperties(defaultProperties);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return parseProperties(properties);
    }

    /**
     * Parse Properties object and converting to String
     *
     * @param properties Properties object
     * @return parsed string
     */
    private String parseProperties(Properties properties) {
        if (properties == null || properties.isEmpty()) {
            return null;
        }
        QueryString[] queryStrings = QueryString.values();
        StringBuilder stringBuilder = new StringBuilder();
        URLProcessing urlProcessing = new URLProcessingImpl();
        String propertyKey;
        String propertyValue;
        String propertyValueEncoded = null;
        int length;

        for (QueryString queryString : queryStrings) {
            propertyKey = queryString.toString();
            propertyValue = properties.getProperty(propertyKey);

            if (propertyValue != null) {
                propertyValueEncoded = urlProcessing.queryStringEncoder(propertyValue, StandardConstants.UTF8_CHARSET);
            }
            if (propertyValueEncoded != null) {
                stringBuilder.append(propertyKey);
                stringBuilder.append(StandardConstants.EQUAL_SIGN);
                stringBuilder.append(propertyValue);
                stringBuilder.append(StandardConstants.AMPERSAND_SIGN);
            }
        }

        length = stringBuilder.length();
        if (length > StandardConstants.AMPERSAND_SIGN.length()) {
            stringBuilder.setLength(length - StandardConstants.AMPERSAND_SIGN.length());
        }

        return stringBuilder.toString();
    }

    /**
     * Load default properties if file not found
     *
     * @return a properties object
     */
    private Properties loadDefaultProperties() {
        Properties properties = new Properties();

        // set deafault properties
        properties.setProperty(QueryString.hl.toString(), "ro");
        properties.setProperty(QueryString.as_q.toString(), "programator");
        properties.setProperty(QueryString.lr.toString(), "lang_ro");
        properties.setProperty(QueryString.cr.toString(), "countryRO");
        properties.setProperty(QueryString.as_qdr.toString(), "all");
        properties.setProperty(QueryString.as_occt.toString(), "body");
        properties.setProperty(QueryString.safe.toString(), "images");
        properties.setProperty(QueryString.gws_rd.toString(), "ssl#lr=lang_ro");
        properties.setProperty(QueryString.q.toString(), "programator");

        return properties;
    }
}
