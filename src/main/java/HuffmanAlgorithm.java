import service.FileReader;
import service.FileWriter;
import service.TextEncoder;

import java.util.*;

public class HuffmanAlgorithm {

    public static void main(String[] args) {
        // Get path to file with text for encoding
        Scanner scan = new Scanner(System.in);
        System.out.print("Take the path to the file: ");
        String pathToFile = scan.nextLine();

        // Get text for encoding
        FileReader fileReader = new FileReader();
        String textForEncoding = fileReader.getTextFromFile(pathToFile);
        System.out.println("Source text size: " + 8 * textForEncoding.length());

        //Encode the text
        TextEncoder textEncoder = new TextEncoder();
        String encoding = textEncoder.messageEncoding(textForEncoding);
        System.out.println("Compressed text size: " + encoding.length());

        // Write encoded text in file
        FileWriter fileWriter = new FileWriter();
        System.out.print("Give name for the new file: ");
        String newFileName = scan.nextLine();
        fileWriter.writeTextToFile(pathToFile, newFileName, encoding);

    }

}