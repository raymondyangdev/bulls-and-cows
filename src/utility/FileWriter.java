package utility;

import bullsandcows.games.Game;

import java.io.*;
import java.util.*;

public class FileWriter {
    public void writeGameResults(String fileName, ArrayList<Round> rounds, Game game) {
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(fileName))) {
            writer.println(game.getName() + " Game Results");
            if (rounds.get(0).getUserCode() != null) {
                writer.println("Your code: " + rounds.get(0).getUserCode());
            }
            writer.println("Computer's code: " + rounds.get(0).getComputerCode());
            for (int i = 0; i < rounds.size(); i++) {
                Round round = rounds.get(i);
                writer.println("---");
                writer.println("Turn " + (i + 1) + ":");
                writer.println("You guessed " + round.getUserGuess() + ", scoring " + round.getUserBulls() + " bulls and " + round.getUserCows() + " cows");
                if (rounds.get(i).getComputerGuess() != null) {
                    writer.println("Computer guessed " + round.getComputerGuess() + ", scoring " + round.getComputerBulls() + " bulls and " + round.getComputerCows() + " cows");
                }
            }

            int lastIndex = rounds.size() - 1;
            writer.println(rounds.get(lastIndex).getGameOutcome());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean saveToFile() {
        String input;
        while (true) {
            System.out.print("Would you like to save your game results to a file? Y/N: ");
            input = Keyboard.readInput().toLowerCase();

            if (input.startsWith("y") || input.startsWith("n")) {
                break;
            }

            System.out.println("\nInvalid input: Please only enter \"Yes\" or \"No\". Try again.\n");
        }
        return input.startsWith("y");
    }

    public void saveGame(ArrayList<Round> roundResults, Game game) {
        System.out.print("File name: ");
        String fileName = Keyboard.readInput();
        writeGameResults(fileName + ".txt", roundResults, game);
        System.out.println("Game results successfully saved to: " + System.getProperty("user.dir") + "/" + fileName + ".txt");
    }
}
