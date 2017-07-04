package main;

import constants.StandardConstants;
import io.files.InputFileProcessing;
import io.files.InputFileProcessingImpl;
import io.files.OutputFileProcessing;
import io.files.OutputFileProcessingImpl;
import net.conn.HTTPConnection;
import net.conn.HTTPConnectionImpl;
import net.url.URLProcessing;
import net.url.URLProcessingImpl;

import java.net.URL;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public class Main {

    public static void main(String[] args) {
        String queryString;
        String response;
        URL url;
        InputFileProcessing inputFileProcessing;
        URLProcessing urlProcessing;
        HTTPConnection httpConnection;
        OutputFileProcessing outputFileProcessing;

        // get query string from properties file
        inputFileProcessing = new InputFileProcessingImpl();
        queryString = inputFileProcessing.readFile(StandardConstants.INPUT_FILE_NAME);
        System.out.println(queryString);

        // build url
        urlProcessing = new URLProcessingImpl();
        url = urlProcessing.buildURL(StandardConstants.URL, queryString);

        // get http response
        httpConnection = new HTTPConnectionImpl();
        response = httpConnection.get(url);

        // write output file
        outputFileProcessing = new OutputFileProcessingImpl();
        outputFileProcessing.writeFile(StandardConstants.OUTPUT_FILE_NAME, response);
        System.out.println(response);
    }
}
