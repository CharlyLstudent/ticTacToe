package game.ticTacToe;

import java.util.Scanner;

/**
 * This class represents a Tic Tac Toe game.
 */
public class TicTacToe {
    int size;
    Cell[] cellObjects;

    Player player1;
    Player player2;

    /**
     * Constructor to initialize the Tic Tac Toe game with a default size of 3x3.
     */
    public TicTacToe() {
        this.size = 3;
        this.cellObjects = new Cell[size * size];

        for (int i = 0; i < size * size; i++) {
            this.cellObjects[i] = new Cell();
            cellObjects[i].isEmpty();
            System.out.println(cellObjects[i].isEmpty);
        }
    }

    /**
     * Starts and manages the Tic Tac Toe game play.
     */
    public void play() {
        int moveCount = 0;
        boolean isPlayerOneTurn = true;

        getGameType(getGameChoice());
        while (!isOver()) {
            if (isPlayerOneTurn) {
                System.out.println("Tour du Joueur 1 (X)");
                System.out.println(player1);
                getMoveFromPlayer(player1);
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


    /**
     * Displays the current state of the game board.
     */
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

    /**
     * Handles the move of the current player.
     *
     * @param currentPlayer The player who is currently making a move.
     */
    public void getMoveFromPlayer(Player currentPlayer) {
        int x, y;
        boolean isValidMove = false;

        do {
            if (currentPlayer instanceof HumanPlayer) {
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
            } else if (currentPlayer instanceof ArtificialPlayer) {
                x = ((ArtificialPlayer) currentPlayer).randomPlay();
                if (!verifyUserInput(x)) {
                    continue; // Continue la boucle depuis le début
                }

                y = ((ArtificialPlayer) currentPlayer).randomPlay();
                if (!verifyUserInput(y)) {
                    continue; // Continue la boucle depuis le début
                }

                isValidMove = verifyIfCellIsEmpty(x, y);
                if (isValidMove) {
                    occupyCell(x, y, currentPlayer.getRepresentation());
                } else {
                    System.out.println("La case est déjà occupée. Veuillez choisir une autre case.");
                }
            }
        } while (!isValidMove);
    }


    /**
     * Reads user input from the console.
     *
     * @return An integer value representing the user input.
     */
    public int getUserInput() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Saisissez une coordonnée (entre 0 et 2).");
        while (!inputUser.hasNextInt()) {
            inputUser.next();
            System.out.println("Erreur : Veuillez saisir une information valide");
        }
        return inputUser.nextInt();
    }

    /**
     * Verifies if the user input is within the valid range for the game board.
     *
     * @param userInput The input from the user.
     * @return true if the input is valid, false otherwise.
     */
    public boolean verifyUserInput(int userInput) {
        return userInput >= 0 && userInput < size;
    }

    /**
     * Checks if the specified cell on the game board is empty.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return true if the cell is empty, false otherwise.
     */
    public boolean verifyIfCellIsEmpty(int x, int y) {
        int index = x * size + y;
        return cellObjects[index].isEmpty();
    }

    /**
     * Occupies a cell on the game board.
     *
     * @param x                    The x-coordinate of the cell.
     * @param y                    The y-coordinate of the cell.
     * @param playerRepresentation The representation of the player occupying the cell.
     */
    public void occupyCell(int x, int y, String playerRepresentation) {
        int index = x * size + y;
        cellObjects[index].occupyCell(playerRepresentation, false);
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isOver() {
        return isBoardFull() || hasThreeAligned();
    }

    // Private helper methods for checking game status
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

    /**
     * Sets up the game type based on user input.
     *
     * @param gameType The type of game to set up.
     */
    public void getGameType(int gameType) {

        switch (gameType) {
            case 0:
                System.out.println("Human vs Human");
                player1 = new HumanPlayer("| X ");
                player2 = new HumanPlayer("| O ");
                break;

            case 1:
                System.out.println("Human vs AI");
                player1 = new HumanPlayer("| X ");
                player2 = new ArtificialPlayer("| O ");
                break;
            case 2:
                System.out.println("AI vs AI");
                player1 = new ArtificialPlayer("| X ");
                player2 = new ArtificialPlayer("| O ");
                break;
        }
    }

    /**
     * Prompts the user to choose the game type.
     *
     * @return An integer representing the chosen game type.
     */
    public int getGameChoice() {
        System.out.println("choisissez un mode de jeu");
        System.out.println("0 - Human vs Human");
        System.out.println("1 - Human vs AI");
        System.out.println("2 - AI vs AI");
        return getUserInput();
    }

}