package hw2.filehandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    public static final int BUFFER_SIZE = 1024;
    
    private File inputFile = null;

    private Map<Character, Integer> letterCounts;

    public void setFile(File file) {
        inputFile = file;
    }

    public String getAbsolutePath() {
        return inputFile.getAbsolutePath();
    }

    public void countCharacters() {
        letterCounts = new HashMap<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            letterCounts.put(c, 0);
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            letterCounts.put(c, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            char[] buffer = new char[BUFFER_SIZE];
            while ((reader.read(buffer, 0, buffer.length)) != -1) {
                for (int i = 0; i < buffer.length; ++i) {
                    if ((buffer[i] >= 'A' && buffer[i] <= 'Z') || (buffer[i] >= 'a' && buffer[i] <= 'z')) {
                        letterCounts.put(buffer[i], letterCounts.get(buffer[i]) + 1);
                    }
                }
            }
        } catch (IOException eIoException) {
            System.out.println("->file reading error<-");
            eIoException.printStackTrace();
        }
    }

    public void writeCounts(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException eIoException) {
                System.out.println("->error when creating a file to be written<-");
                eIoException.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (char key : letterCounts.keySet()) {
                writer.write(key + " " + letterCounts.get(key) + "\n");
            }
        } catch (IOException eIoException) {
            System.out.println("->file writing error<-");
            eIoException.printStackTrace();
        }
    }
}
