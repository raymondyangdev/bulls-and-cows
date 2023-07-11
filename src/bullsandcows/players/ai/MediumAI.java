package bullsandcows.players.ai;

import java.util.*;

public class MediumAI extends IntelligentAI {

    public MediumAI() {
        this.uniqueGuesses = new ArrayList<>();
    }

    @Override
    public String guessCode() {
        String guess;
        do {
            guess = super.guessCode();
        } while (guessIsDuplicate(guess, uniqueGuesses));

        uniqueGuesses.add(guess);

        return guess;
    }


}
