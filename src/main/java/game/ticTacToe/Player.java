package game.ticTacToe;

public class Player {
    public String representation;

    public Player(String representation){
        if(!"| X ".equals(representation) && !"| O ".equals(representation)){
            throw new IllegalArgumentException("Representation invalide");
        }
        this.representation = representation;
    }
    public String getRepresentation(){

        return representation;
    }
}
