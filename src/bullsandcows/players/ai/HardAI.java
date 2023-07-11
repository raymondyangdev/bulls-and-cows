package bullsandcows.players.ai;

import java.util.*;

public class HardAI extends IntelligentAI {
    private List<String> possibleCodes;

    public HardAI() {
        this.possibleCodes = generateAllPossibleCodes();
        this.uniqueGuesses = new ArrayList<>();
    }

    @Override
    public String guessCode() {
        int randomIndex = (int) (Math.random() * possibleCodes.size());
        String guess = possibleCodes.get(randomIndex);
        uniqueGuesses.add(guess);
        System.out.println("Computer guess: " + guess);
        return guess;
    }

    public void prunePossibleCodes(String bullsAndCowsResult) {
        List<String> newPossibleCodes = new ArrayList<>();
        String mostRecentGuess = uniqueGuesses.get(uniqueGuesses.size() - 1);
        for (String code : possibleCodes) {
            String guessFeedback = countBullsAndCows(mostRecentGuess, code);
            if (bullsAndCowsResult.equals(guessFeedback)) {
                newPossibleCodes.add(code);
            }
        }
        possibleCodes = newPossibleCodes;
    }

    private List<String> generateAllPossibleCodes() {
        List<String> possibleCodes = new ArrayList<>();

        // Start with 1 since first digit can't begin with 0
        // If statements check whether digit has been used previously
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j != i) {
                    for (int k = 0; k < 10; k++) {
                        if (k != i && k != j) {
                            for (int l = 0; l < 10; l++) {
                                if (l != i && l != j && l != k) {
                                    String code = "" + i + j + k + l;
                                    possibleCodes.add(code);
                                }
                            }
                        }
                    }
                }
            }
        }
        return possibleCodes;
    }
}
