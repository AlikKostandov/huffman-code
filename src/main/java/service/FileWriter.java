package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * FileWriter.
 */
public class FileWriter {

    public static final String TXT_FORMAT = ".txt";

    public void writeTextToFile(String filePath, String fileName, String fileContent) {
        String fullPath = createLink(filePath);
        writeToFile(fullPath, fileName, fileContent);
    }

    private String createLink(String path){

        ArrayList<String> list = new ArrayList<>(Arrays.asList(path.split("\\\\")));
        list.remove(list.get(list.size() - 1));
        return String.join("\\", list) + "\\";

    }

    private void writeToFile(String filePath, String fileName, String content) {

        File compressedFile = new File(filePath + fileName + TXT_FORMAT);
        try (java.io.FileWriter writer = new java.io.FileWriter(compressedFile, false)) {

            writer.write(content);

        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи в файл: " + filePath);
        }
    }

}
