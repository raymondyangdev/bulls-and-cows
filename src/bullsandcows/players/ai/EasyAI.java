package bullsandcows.players.ai;

public class EasyAI extends AI {
    @Override
    public String guessCode() {
        String guess = super.generateFourUniqueNums();
        System.out.println("Computer guess: " + guess);
        return guess;
    }
}
