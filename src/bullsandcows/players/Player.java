package bullsandcows.players;

import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Player {
    private int bulls, cows;
    private String secretCode;

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public abstract String guessCode();

    public abstract void getSecretCodeFromPlayer();

    public boolean codeIsValid(String code) {
        HashSet<Character> playersCode = new LinkedHashSet<>();

        for (int i = 0; i < code.length(); i++) {
            playersCode.add(code.charAt(i));
        }

        return playersCode.size() == 4 && code.matches("[0-9]+") && !code.startsWith("0");
    }

    public String countBullsAndCows(String guess, String code) {
        int bulls = 0;
        int cows = 0;
        boolean[] checkedChar = new boolean[code.length()]; // tracks which letters have been checked

        // Ignore case
        guess = guess.toLowerCase();
        code = code.toLowerCase();

        // Count bulls
        for (int i = 0; i < code.length(); i++) {
            if (guess.charAt(i) == code.charAt(i)) {
                bulls++;
                checkedChar[i] = true;
            }
        }

        // Count cows
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            for (int j = 0; j < code.length(); j++) {
                if (!checkedChar[j] && c == code.charAt(j)) {
                    cows++;
                    checkedChar[j] = true;
                    break;
                }
            }
        }

        return "" + bulls + cows;
    }

    public void printBullsAndCows(String guess, String code) {
        String numOfBullsAndCows = countBullsAndCows(guess, code);
        int bulls = numOfBullsAndCows.charAt(0) - '0';
        int cows = numOfBullsAndCows.charAt(1) - '0';
        this.bulls = bulls;
        this.cows = cows;

        System.out.printf("Result: %d bulls and %d cows\n\n", bulls, cows);
    }


    public boolean isWinner() {
        return this.bulls == 4 || this.bulls == 5;
    }

}
