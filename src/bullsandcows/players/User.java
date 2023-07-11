package bullsandcows.players;

import utility.Keyboard;

public class User extends Player {

    @Override
    public String guessCode() {
        System.out.println("---");
        System.out.print("You guess: ");
        return Keyboard.readInput();
    }

    @Override
    public void getSecretCodeFromPlayer() {
        String playersSecretCode;
        while (true) {
            System.out.print("Please enter your secret code: ");
            playersSecretCode = Keyboard.readInput();

            if (playersSecretCode.length() == 4 && codeIsValid(playersSecretCode)) {
                break;
            }

            System.out.println("\nInvalid code: Your secret code must contain four unique numbers between 0 - 9. Try again.\n");
        }

        super.setSecretCode(playersSecretCode);

    }
}
