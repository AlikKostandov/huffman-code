package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * FileReader.
 */
public class FileReader {

    public String getTextFromFile(String filePath) {
        return readTxtFile(filePath).toLowerCase(Locale.ROOT);
    }

    private String readTxtFile(String filePath) {
        StringBuilder textContent = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(filePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                textContent.append(new String(buffer, 0, bytesRead));
            }

        } catch (IOException e) {
            return "Произошла ошибка при чтении файла: " + e.getMessage();
        }
        return textContent.toString();
    }
}




