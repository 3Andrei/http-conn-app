package net.url;

import java.net.URL;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public interface URLProcessing {

    /**
     * Build an URL from a base address and a query string
     *
     * @param baseAddress base address
     * @param queryString query string
     * @return URL
     */

    URL buildURL(String baseAddress, String queryString);

    /**
     * Encode a query string parameter by a specific charset
     *
     * @param queryStringParameters query string parameter
     * @param charset               charset
     * @return encoded query string
     */
    String queryStringEncoder(String queryStringParameters, String charset);
}
