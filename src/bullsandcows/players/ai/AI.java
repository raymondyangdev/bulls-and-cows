package bullsandcows.players.ai;

import bullsandcows.players.Player;

import java.util.*;

public abstract class AI extends Player {

    protected String generateFourUniqueNums() {
        HashSet<Integer> secretCode = new LinkedHashSet<>();

        while (secretCode.size() < 4) {
            int randomNum = (int) (Math.random() * 10);
            secretCode.add(randomNum);

            // Removes the first index if it is equal to 0
            // As the secret code cannot start with 0
            if (secretCode.iterator().next().equals(0)) {
                secretCode.remove(secretCode.iterator().next());
            }
        }

        return parseSetToString(secretCode);
    }

    private String parseSetToString(Set<Integer> secretCode) {
        StringBuilder secretCodeStr = new StringBuilder();
        for (Integer num : secretCode) {
            secretCodeStr.append(num);
        }

        return secretCodeStr.toString();
    }

    @Override
    public void getSecretCodeFromPlayer() {
        String secretCode = generateFourUniqueNums();
        super.setSecretCode(secretCode);
    }
}
