package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public ArrayList<String> importDictionary(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            ArrayList<String> dictionary = new ArrayList<>();

            scanner.useDelimiter(",|\\r\\n");
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (wordIsValid(word)) {
                    dictionary.add(word.toLowerCase());
                }
            }

            return dictionary;
        } catch (IOException e) {
            throw new IOException("Error: " + e.getMessage());
        }
    }

    private boolean wordIsValid(String word) {
        return word.matches("[a-zA-Z]+") && word.length() == 5;
    }
}
