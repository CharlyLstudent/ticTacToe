package game.ticTacToe;

import java.util.Random;

public class ArtificialPlayer extends Player {

    private Random random;

    public ArtificialPlayer(String representation) {
        super(representation, false);

    }

    public int randomPlay(){
        this.random = new Random();
        return random.nextInt(3);
    }
}
