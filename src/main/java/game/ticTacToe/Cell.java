package game.ticTacToe;

public class Cell {
    private String representation = "|   ";
    private boolean isEmpty = true;

    public Cell() {
    }

    public String getRepresentation() {

        return representation;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void occupyCell(String playerRepresentation, boolean isNotEmpty) {
        if (isEmpty) {
            this.representation = playerRepresentation;
            this.isEmpty = false;
        }
    }

    public boolean isNotEmpty() {
        return this.isEmpty == false;
    }
}
