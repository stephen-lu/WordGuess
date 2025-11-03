package com.github.zipcodewilmington;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class Hangman {

    String word;
    String guess;
    int lives;
    int incorrect_guesses;
    
    public Hangman() {
        this.incorrect_guesses = 0;
    }

    public void runHangman() {

        Scanner scanner = null;
        FileReader file = new FileReader();
        ArrayList<String> words = file.readFromFile("./words.txt");

        try {
            scanner = new Scanner(System.in);
            System.out.println("Welcome to Hangman");
            Random random = new Random();
            this.word = words.get(random.nextInt(words.size()));

            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                if (userInput.isEmpty()) { System.out.println(); continue; }

                switch (userInput) {
                    case "quit" -> {
                        System.out.println("Leaving game");
                        return;
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
