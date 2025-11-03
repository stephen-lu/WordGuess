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
    ArrayList<String> guess;
    ArrayList<String> hints;
    int lives;
    ArrayList<String> incorrect_guesses;

    
    public Hangman() {
        this.lives = 6;
        this.guess = new ArrayList<String>();
    }

    public void runHangman() {

        Scanner scanner = null;
        FileReader file = new FileReader();
        ArrayList<String> words = file.readFromFile("./words.txt");

        try {
            scanner = new Scanner(System.in);
            System.out.println("Welcome to Hangman");
            Random random = new Random();
            this.word = "testing"; //words.get(random.nextInt(words.size()));
            int wordLength = this.word.length();
            for (int i = 0; i < wordLength; i++) {
                this.guess.add("_");
            }

            while (true) {
                for (int i = 0; i < this.guess.size(); i++) {
                    System.out.print(this.guess.get(i) + " ");
                }
                System.out.println();
                if (this.lives == 0) {
                    System.out.println("You lose");
                    return;
                }
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                if (userInput.isEmpty()) { System.out.println(); continue; }

                if (userInput == "quit" || userInput == "Quit") {
                        System.out.println("Leaving game");
                        return;
                } else if (this.word.contains(userInput)) {
                    if (userInput.length() == 1) {
                        for (int i = this.word.indexOf(userInput); i >= 0; i = this.word.indexOf(userInput, i + 1)) {
                            this.guess.set(i, userInput);
                        }
                        continue;
                    } else if (userInput.length() == wordLength) {
                        System.out.println("You Win");
                        return;
                    }
                } else if (userInput.length() == 0) {
                    continue;
                } else {
                    System.out.println("Incorrect");
                    this.lives--;
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
