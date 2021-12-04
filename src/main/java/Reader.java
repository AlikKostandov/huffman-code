import java.io.FileReader;
import java.io.IOException;



public class Reader {

    public static String readTxtFile(String fileName) {
        String text = null;
        try {
            FileReader tfr = new FileReader(fileName);
            char[] buffer = new char[8096];
            int chars = tfr.read(buffer);
            while (chars != -1) {
                text = (String.valueOf(buffer, 0, chars));
                chars = tfr.read(buffer);
            }
            tfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}




