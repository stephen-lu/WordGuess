package com.github.zipcodewilmington;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;

public class FileReader {

    public FileReader() {

    }

    public ArrayList<String> readFromFile(String file) {

        URL path = Hangman.class.getResource(file);
        ArrayList<String> wordsSet = new ArrayList<String>();

        try (Scanner scanner = new Scanner(new File(path.getFile()))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (!word.isEmpty()) { // Add only non-empty words
                    wordsSet.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found at " + file);
            e.printStackTrace();
        }

        return wordsSet;
    }
}