package net.conn;

import constants.StandardConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public class HTTPConnectionImpl implements HTTPConnection {

    @Override
    public String get(URL url) {
        if (url == null) {
            return null;
        }
        int responseCode;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(StandardConstants.REQUEST_METHOD_GET);
            responseCode = urlConnection.getResponseCode();

            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, StandardConstants.ROMANIAN_CHARSET);
                bufferedReader = new BufferedReader(inputStreamReader);

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch(IOException e) {
               e.printStackTrace();
            }
            urlConnection.disconnect();
        }

        return stringBuilder.toString();
    }
}
