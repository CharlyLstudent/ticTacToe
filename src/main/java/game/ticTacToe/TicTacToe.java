package game.ticTacToe;

import java.util.Arrays;

public class TicTacToe {
    int size;
    Cell[] cellObjects;

    public TicTacToe() {
        this.size = 3;
        this.cellObjects = new Cell[size * size];

        for (int i = 0; i < size * size; i++) {
            this.cellObjects[i] = new Cell();
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println("-------------");
            for (int j = 0; j < size; j++) {
                System.out.print(cellObjects[i * size + j].getRepresentation());
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }
}
