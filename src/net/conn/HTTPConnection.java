package net.conn;

import java.net.URL;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public interface HTTPConnection {
    /**
     * Get connection response.
     * response after http get request
     *
     * @param url URL
     * @return response as string
     */
    String get(URL url);

}
