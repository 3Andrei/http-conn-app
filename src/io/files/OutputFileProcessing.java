package io.files;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public interface OutputFileProcessing {

    /**
     * Write a file
     *
     * @param fileName file name
     * @param data     data to be written as string
     */
    void writeFile(String fileName, String data);
}
