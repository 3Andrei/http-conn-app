package io.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Nicolescu on 02.04.2017.
 */
public class OutputFileProcessingImpl implements OutputFileProcessing {

    @Override
    public void writeFile(String fileName, String data) {
        if (fileName == null || data == null) {
            return;
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
