package net.url;

import constants.StandardConstants;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public class URLProcessingImpl implements URLProcessing {

    @Override
    public URL buildURL(String baseAddress, String queryString) {
        if (baseAddress == null || queryString == null) {
            return null;
        }
        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(baseAddress);
        stringBuilder.append(StandardConstants.QUESTION_SIGN);
        stringBuilder.append(queryString);

        try {
            url = new URL(stringBuilder.toString());
        } catch (MalformedURLException e) {
            System.out.println("The string could not be parsed");
            try {
                return new URL(StandardConstants.URL);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public String queryStringEncoder(String queryStringParameters, String charset) {
        if (queryStringParameters == null || queryStringParameters.isEmpty()) {
            return null;
        }
        if (charset == null || charset.isEmpty()) {
            charset = StandardConstants.UTF8_CHARSET;
        }
        String encodedQueryString = null;

        try {
            encodedQueryString = URLEncoder.encode(queryStringParameters, charset);
        } catch (UnsupportedEncodingException e) {
            System.out.println("The query string parameter cannot be encoded");
            e.printStackTrace();
            return null;
        }

        return encodedQueryString;
    }
}
