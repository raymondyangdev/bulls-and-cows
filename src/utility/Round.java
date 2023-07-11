package utility;

public class Round {
    private String userGuess, computerGuess, userCode, computerCode;
    private int userBulls, userCows, computerBulls, computerCows;
    private String gameOutcome;

    public Round(String userGuess, String computerGuess, String userCode, String computerCode, int userBulls, int userCows, int computerBulls, int computerCows, int roundNumber, String gameOutcome) {
        this.userGuess = userGuess;
        this.computerGuess = computerGuess;
        this.userCode = userCode;
        this.computerCode = computerCode;
        this.userBulls = userBulls;
        this.userCows = userCows;
        this.computerBulls = computerBulls;
        this.computerCows = computerCows;
        this.gameOutcome = gameOutcome;
    }

    public Round(String userGuess, String computerCode, int userBulls, int userCows, int roundNumber, String gameOutcome) {
        this.userGuess = userGuess;
        this.computerCode = computerCode;
        this.userBulls = userBulls;
        this.userCows = userCows;
        this.gameOutcome = gameOutcome;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getComputerCode() {
        return computerCode;
    }

    public String getUserGuess() {
        return userGuess;
    }

    public String getComputerGuess() {
        return computerGuess;
    }

    public int getUserBulls() {
        return userBulls;
    }

    public int getUserCows() {
        return userCows;
    }

    public int getComputerBulls() {
        return computerBulls;
    }

    public int getComputerCows() {
        return computerCows;
    }

    public String getGameOutcome() {
        return gameOutcome;
    }

    public void setGameOutcome(String gameOutcome) {
        this.gameOutcome = gameOutcome;
    }
}
