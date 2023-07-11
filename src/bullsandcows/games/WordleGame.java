package bullsandcows.games;

import utility.Round;

import java.util.*;

public class WordleGame extends Game {
    private final ArrayList<String> dictionary;

    public WordleGame(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
        maxRound = 6;
        name = "Wordle";
    }

    private boolean guessIsValid(String guess) {
        return (guess.length() == 5 && guess.matches("[a-zA-Z]+"));
    }

    private String getRandomWordFromDictionary(ArrayList<String> dictionary) {
        return dictionary.get((int) (Math.random() * dictionary.size() + 1));
    }

    @Override
    public void play() {
        printWelcomeMessage();
        String word = getRandomWordFromDictionary(dictionary);

        String guess;
        boolean gameOver = false;
        while (!gameOver) {

            while (true) {
                guess = user.guessCode();
                if (guessIsValid(guess)) {
                    user.printBullsAndCows(guess, word);
                    break;
                }
                System.out.println("\nInvalid guess: Your guess must be exactly five characters in length and contain only letters between A - Z or a - z. Try again.\n");
            }

            roundResults.add(new Round(guess, word, user.getBulls(), user.getCows(), round, winner));

            round++;

            if (round == maxRound || guess.equalsIgnoreCase(word)) {
                gameOver = true;
            }
        }
        printGameWinner(user);

        if (fileWriter.saveToFile()) {
            fileWriter.saveGame(roundResults, this);
        }

        printExitMessage();
    }
}
