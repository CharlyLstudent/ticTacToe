package game.ticTacToe;
public class BoardManager {

    private int size;
    private Cell[] cellObjects;

    public int getSize() {
        return size;
    }

    public Cell[] getCellObjects() {
        return cellObjects;
    }
    public BoardManager() {
        this.size = 3;
        this.cellObjects = new Cell[size * size];
        initializeCells();
        display();
    }

    private void initializeCells(){
        for (int i = 0; i < size * size; i++) {
            this.cellObjects[i] = new Cell();
            cellObjects[i].isEmpty();
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
