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

        while (!isOver()) {
            if (isPlayerOneTurn) {
                System.out.println("Tour du Joueur 1 (X)");
                getMoveFromPlayer(player);
            } else {
                System.out.println("Tour du Joueur 2 (O)");
                getMoveFromPlayer(player2);
            }

            display();
            moveCount++;
            isPlayerOneTurn = !isPlayerOneTurn;

            if (isOver()) {
                System.out.println("Jeu terminé !");
                break;
            }
        }
    }


    public boolean isOver() {
        return isBoardFull() || hasThreeAligned();
    }

    private boolean isBoardFull() {
        for (Cell cell : cellObjects) {
            if (cell.isEmpty()) {
                return false; // S'il y a au moins une cellule vide, le plateau n'est pas plein
            }
        }
        return true;
    }

    private boolean hasThreeAligned() {
        // Vérifie les lignes horizontales, verticales et diagonales
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < size; i++) {
            if (!cellObjects[i * size].isEmpty() &&
                    cellObjects[i * size].getRepresentation().equals(cellObjects[i * size + 1].getRepresentation()) &&
                    cellObjects[i * size + 1].getRepresentation().equals(cellObjects[i * size + 2].getRepresentation())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < size; i++) {
            if (!cellObjects[i].isEmpty() &&
                    cellObjects[i].getRepresentation().equals(cellObjects[i + size].getRepresentation()) &&
                    cellObjects[i + size].getRepresentation().equals(cellObjects[i + 2 * size].getRepresentation())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        // Première diagonale
        if (!cellObjects[0].isEmpty() &&
                cellObjects[0].getRepresentation().equals(cellObjects[size + 1].getRepresentation()) &&
                cellObjects[size + 1].getRepresentation().equals(cellObjects[2 * size + 2].getRepresentation())) {
            return true;
        }

        // Deuxième diagonale
        if (!cellObjects[size - 1].isEmpty() &&
                cellObjects[size - 1].getRepresentation().equals(cellObjects[2 * size - 1].getRepresentation()) &&
                cellObjects[2 * size - 1].getRepresentation().equals(cellObjects[3 * size - 3].getRepresentation())) {
            return true;
        }

        return false;
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

    public int getUserInput() {
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
