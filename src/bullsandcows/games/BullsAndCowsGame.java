package bullsandcows.games;

import bullsandcows.players.*;
import bullsandcows.players.ai.*;
import utility.*;

public class BullsAndCowsGame extends Game {
    private Player ai;
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    public BullsAndCowsGame() {
        this.ai = null;
        maxRound = 7;
        name = "Bulls and Cows";
    }

    private void printDifficultyMenu() {
        System.out.println("Select your difficulty:");
        System.out.println("Please enter the corresponding number or type the difficulty to begin...");
        System.out.println(EASY + " - Easy");
        System.out.println(MEDIUM + " - Medium");
        System.out.println(HARD + " - Hard");
    }

    private int getAIDifficulty() {
        printDifficultyMenu();
        while (true) {
            System.out.print("\nDifficulty >>> ");
            String difficulty = Keyboard.readInput();

            switch (difficulty.toLowerCase()) {
                case "easy":
                    return EASY;
                case "medium":
                    return MEDIUM;
                case "hard":
                    return HARD;
            }

            try {
                int choice = Integer.parseInt(difficulty);
                if (choice >= EASY && choice <= HARD) {
                    return choice;
                }
                System.out.println("Enter Easy, Medium, Hard, or a number between 1 and 3.");
            } catch (NumberFormatException e) {
                System.out.println("Enter Easy, Medium, Hard, or a number.");
            }
        }
    }

    private AI setAIDifficulty() {
        int difficulty = getAIDifficulty();
        switch (difficulty) {
            case 1: {
                System.out.println("Easy difficulty selected...\n");
                return new EasyAI();
            }
            case 2: {
                System.out.println("Medium difficulty selected... Good luck!\n");
                return new MediumAI();
            }
            case 3: {
                System.out.println("Hard difficulty selected...\n");
                return new HardAI();
            }
        }
        throw new IllegalArgumentException("An error has occurred: Invalid difficulty set.");
    }

    private void requestSecretCodeFromPlayers() {
        this.user.getSecretCodeFromPlayer();
        this.ai.getSecretCodeFromPlayer();
    }

    @Override
    public void play() {
        printWelcomeMessage();
        ai = setAIDifficulty();
        requestSecretCodeFromPlayers();

        String aiCode = ai.getSecretCode();
        String userCode = user.getSecretCode();
        String playerGuess;
        String aiGuess;
        boolean gameOver = false;
        while (!gameOver) {

            while (true) {
                playerGuess = user.guessCode();
                if (user.codeIsValid(playerGuess)) {
                    user.printBullsAndCows(playerGuess, aiCode);
                    break;
                }
                System.out.println("\nInvalid guess: Your guess can only contain four unique numbers between 0 - 9. Try again.\n");
            }

            aiGuess = ai.guessCode();
            ai.printBullsAndCows(aiGuess, userCode);

            if (ai instanceof HardAI) {
                String bullsAndCowsResult = ai.countBullsAndCows(aiGuess, userCode);
                ((HardAI) ai).prunePossibleCodes(bullsAndCowsResult);
            }

            roundResults.add(new Round(playerGuess, aiGuess, userCode, aiCode, user.getBulls(), user.getCows(), ai.getBulls(), ai.getCows(), round, winner));

            round++;

            if (round == maxRound || playerGuess.equals(aiCode) || aiGuess.equals(userCode)) {
                gameOver = true;
            }
        }
        printGameWinner(user, ai);

        if (fileWriter.saveToFile()) {
            fileWriter.saveGame(roundResults, this);
        }

        printExitMessage();
    }
}
