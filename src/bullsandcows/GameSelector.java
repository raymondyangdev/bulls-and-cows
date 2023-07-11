package bullsandcows;

import bullsandcows.games.*;
import utility.*;

import java.io.IOException;

public class GameSelector {
    private static final int BULLS_AND_COWS = 1;
    private static final int WORDLE = 2;
    private FileReader fileReader;

    public GameSelector() {
        this.fileReader = new FileReader();
    }

    private void printMenu() {
        System.out.println("Welcome!");
        System.out.println("Please enter the corresponding number or type the name of the game to begin...");
        System.out.println(BULLS_AND_COWS + " - Bulls and Cows");
        System.out.println(WORDLE + " - Wordle");
    }

    private int getGameMode() {
        printMenu();

        while (true) {
            System.out.print("Game >>> ");
            String input = Keyboard.readInput();

            switch (input.toLowerCase()) {
                case "bulls and cows":
                    return BULLS_AND_COWS;
                case "wordle":
                    return WORDLE;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= BULLS_AND_COWS && choice <= WORDLE) {
                    return choice;
                }
                System.out.println("Enter Bulls and Cows, Wordle, or a number between 1 and 2.");
            } catch (NumberFormatException e) {
                System.out.println("Enter Bulls and Cows, Wordle, or a number.");
            }
        }
    }

    private Game setGameMode(int gameMode) {
        switch (gameMode) {
            case 1: {
                return new BullsAndCowsGame();
            }
            case 2: {
                try {
                    return new WordleGame(fileReader.importDictionary("dictionary.txt"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Starting a game of Bulls & Cows...");
                    break;
                }
            }
        }
        return new BullsAndCowsGame();
    }

    public void start() {
        int gameMode = getGameMode();
        Game game = setGameMode(gameMode);
        game.play();
    }

    public static void main(String[] args) {
        GameSelector gm = new GameSelector();
        gm.start();
    }
}
