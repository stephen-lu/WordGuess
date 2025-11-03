package com.github.zipcodewilmington;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author stephen
 * @version 1.0.0
 * @date 11/3/25s
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
        this.hints = new ArrayList<String>();
    }

    public void runHangman() {

        Scanner scanner = null;
        FileReader file = new FileReader();
        ArrayList<String> words = file.readFromFile("./words.txt");

        try {
            scanner = new Scanner(System.in);
            welcome();
            this.word = getRandomWord(words);
            int wordLength = this.word.length();

            printGuess(wordLength);

            while (true) {
                currentLives();
                if (this.lives == 0) {
                    gameLose();
                    return;
                }
                System.out.print("Wrong Letters: ");
                for (int i = 0; i < this.hints.size(); i++) {
                    printLetter(this.hints.get(i));
                }
                System.out.println();
                for (int i = 0; i < this.guess.size(); i++) {
                    printLetter(this.guess.get(i));
                }
                if (String.join("", this.guess).equals(this.word)) {
                    System.out.println();
                    gameWin();
                    return;
                }
                System.out.println();
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                if (userInput.isEmpty()) { System.out.println(); continue; }

                if (userInput == "quit" || userInput == "Quit") {
                        leaveGame();
                        return;
                } else if (this.word.contains(userInput)) {
                    if (userInput.length() == 1) {
                        for (int i = this.word.indexOf(userInput); i >= 0; i = this.word.indexOf(userInput, i + 1)) {
                            this.guess.set(i, userInput);
                        }
                        continue;
                    } else if (userInput.length() == wordLength) {
                        gameWin();
                        return;
                    }
                } else if (userInput.length() == 0) {
                    continue;
                } else {
                    if (userInput.length() == 1) {
                        if (Character.isLetter(userInput.charAt(0))) {
                            if (!this.hints.contains(userInput)) {
                                wrongGuess((userInput));
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void welcome() {
        System.out.println("Welcome to Hangman");
    }

    private String getRandomWord(ArrayList<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private void printGuess(int wordLength) {
        for (int i = 0; i < wordLength; i++) {
            this.guess.add("_");
        }   
    }

    private void gameWin() {
        System.out.println("You Win");
    }

    private void gameLose() {
        System.out.println("The word was " + this.word);
        System.out.println("You lose");
    }

    private void leaveGame() {
        System.out.println("Leaving game");
    }

    private void printLetter(String letter) {
        System.out.print(letter + " ");
    }

    private void currentLives() {
        System.out.println();
        System.out.println("Current Lives: " + Integer.toString(this.lives));
    }

    private void wrongGuess(String s) {
        this.hints.add(s);
        System.out.println("Incorrect");
        this.lives--;
    }
}
