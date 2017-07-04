package io.files;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public interface InputFileProcessing {
    /**
     * Read from file
     *
     * @param fileName file name
     * @return data from file as string
     */
    String readFile(String fileName);
}
