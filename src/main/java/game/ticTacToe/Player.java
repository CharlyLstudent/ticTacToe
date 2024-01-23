package game.ticTacToe;

public abstract class Player {
    public String representation;
    private boolean isHuman;

    public Player(String representation, boolean isHuman){

        if(!"| X ".equals(representation) && !"| O ".equals(representation)){
            throw new IllegalArgumentException("Representation invalide");
        }
        this.representation = representation;
        this.isHuman = isHuman;
    }
    public String getRepresentation(){

        return representation;
    }

}
