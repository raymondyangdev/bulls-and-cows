package bullsandcows.players.ai;

import java.util.*;

public abstract class IntelligentAI extends EasyAI {

    protected ArrayList<String> uniqueGuesses;

    protected boolean guessIsDuplicate(String guess, ArrayList<String> guessesAlreadyMade) {
        return guessesAlreadyMade.contains(guess);
    }
}
