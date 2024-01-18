package game.ticTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    int size;
    Cell[] cellObjects;

    Player player = new Player("| X ");
    Player player2 = new Player("| O ");

    public TicTacToe() {
        this.size = 3;
        this.cellObjects = new Cell[size * size];

        for (int i = 0; i < size * size; i++) {
            this.cellObjects[i] = new Cell();
            cellObjects[i].isEmpty();
            System.out.println(cellObjects[i].isEmpty);
        }
    }

    public void play() {
        int moveCount = 0;
        boolean isPlayerOneTurn = true;

        while(moveCount< size*size){
            if(isPlayerOneTurn){
                System.out.println("Joueur 1 joue (X)");
                getMoveFromPlayer(player);
            }else{
                System.out.println("Joueur 2 joue (O)");
                getMoveFromPlayer(player2);
            }
            display();
            moveCount++;
            isPlayerOneTurn = !isPlayerOneTurn;
        }
        System.out.println("Game over - Toutes les cases sont pleines !");
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

//    public void getMoveFromPlayer(Player currentPlayer) {
//        int x;
//        int y;
//        boolean validMove;
//        do {
//            System.out.println("Veuillez entrer la coordonnée X : ");
//            x = getUserInput();
//            validMove = verifyUserInput(x);
//
//            System.out.println("Veuillez entrer la coordonnée Y : ");
//            y = getUserInput();
//            validMove = validMove && verifyUserInput(y);
//            if (validMove && verifyIfCellIsEmpty(x, y)) {
//                occupyCell(x, y, currentPlayer.getRepresentation());
//            } else {
//                System.out.println("Veuillez choisir une autre case, celle-ci est déjà prise !");
//            }
//        }while(true);
//    }

    public void getMoveFromPlayer(Player currentPlayer) {
        int x, y;
        boolean isValidMove = false;

        do {
            System.out.println("Veuillez entrer la coordonnée X : ");
            x = getUserInput();
            if (!verifyUserInput(x)) {
                System.out.println("Coordonnée X invalide. Veuillez saisir une coordonnée entre 0 et " + (size - 1));
                continue; // Continue la boucle depuis le début
            }

            System.out.println("Veuillez entrer la coordonnée Y : ");
            y = getUserInput();
            if (!verifyUserInput(y)) {
                System.out.println("Coordonnée Y invalide. Veuillez saisir une coordonnée entre 0 et " + (size - 1));
                continue; // Continue la boucle depuis le début
            }

            isValidMove = verifyIfCellIsEmpty(x, y);
            if (isValidMove) {
                occupyCell(x, y, currentPlayer.getRepresentation());
            } else {
                System.out.println("La case est déjà occupée. Veuillez choisir une autre case.");
            }
        } while (!isValidMove);
    }

    static int getUserInput() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Saisissez une coordonnée (entre 0 et 2).");
        while (!inputUser.hasNextInt()) {
            inputUser.next();
            System.out.println("Erreur : Veuillez saisir une information valide");
        }
        return inputUser.nextInt();
    }

    public boolean verifyUserInput(int userInput) {
        return userInput >= 0 && userInput < size;
    }

    public boolean verifyIfCellIsEmpty(int x, int y) {
        int index = x * size + y;
        return cellObjects[index].isEmpty();
    }
    public void occupyCell(int x, int y, String playerRepresentation) {
        int index = x * size + y;
        cellObjects[index].occupyCell(playerRepresentation, false);
    }

}
