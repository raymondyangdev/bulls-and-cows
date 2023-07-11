package bullsandcows.games;

import bullsandcows.players.Player;
import bullsandcows.players.User;
import utility.FileWriter;
import utility.Round;

import java.util.ArrayList;

public abstract class Game {
    protected int round, maxRound;
    protected String name, winner;
    protected FileWriter fileWriter;
    protected Player user;
    protected ArrayList<Round> roundResults;

    public Game() {
        this.round = 0;
        this.user = new User();
        this.fileWriter = new FileWriter();
        this.roundResults = new ArrayList<>();
        this.winner = null;
    }

    public String getName() {
        return name;
    }

    public void printWelcomeMessage() {
        System.out.println("\nWelcome to " + name + "!");
    }

    public void printExitMessage() {
        System.out.println("\nThanks for playing " + name + "!");
    }

    public void printGameWinner(Player user, Player computer) {
        String result;
        if (user.isWinner() == computer.isWinner()) {
            result = "It's a draw! :|";
        } else if (user.isWinner()) {
            result = "You win! :)";
        } else {
            result = "You lose! The computer wins! :(";
        }
        addGameWinnerToResults(result);
        System.out.println(result);
    }

    public void printGameWinner(Player user) {
        String result;
        if (user.isWinner()) {
            result = "You win! :)";
        } else {
            result = "You lose! :(";
        }
        addGameWinnerToResults(result);
        System.out.println(result);
    }

    private void addGameWinnerToResults(String winner) {
        roundResults.get(roundResults.size() - 1).setGameOutcome(winner);
    }

    public abstract void play();
}
