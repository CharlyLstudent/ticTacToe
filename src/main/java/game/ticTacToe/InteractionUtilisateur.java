package game.ticTacToe;

import java.util.Scanner;

public class InteractionUtilisateur {

    public View view;
    public BoardManager boardManager;

    public InteractionUtilisateur(View view, BoardManager boardManager){
        this.boardManager = boardManager;
        this.view = view;
    }
    public int getUserInput() {
        Scanner inputUser = new Scanner(System.in);
        view.askUserCoordinate();
        while (!inputUser.hasNextInt()) {
            inputUser.next();
            view.invalidCoordinate();
        }
        return inputUser.nextInt();
    }

    public boolean verifyUserInput(int userInput) {
        return userInput >= 0 && userInput < boardManager.getSize();
    }

    public boolean verifyIfCellIsEmpty(int x, int y) {
        int index = x * boardManager.getSize() + y;
        return boardManager.getCellObjects()[index].isEmpty();
    }

    public void occupyCell(int x, int y, String playerRepresentation) {
        int index = x * boardManager.getSize() + y;
        boardManager.getCellObjects()[index].occupyCell(playerRepresentation, false);
    }
}
