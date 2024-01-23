package game.ticTacToe;

import java.util.Scanner;

public class GameManager {
    private BoardManager board;

    private PlayerManager playerManager;

    private View view;

    private InteractionUtilisateur interactionUtilisateur;
    public GameManager(BoardManager boardManager, PlayerManager playerManager, View view, InteractionUtilisateur interactionUtilisateur) {
        this.board = boardManager;
        this.playerManager = playerManager;
        this.view = view;
        this.interactionUtilisateur = interactionUtilisateur;
    }

    public void play() {

        int moveCount = 0;

        playerManager.getGameType(playerManager.getGameChoice());
        while (!isOver()) {
            view.playerTurn();
            getMoveFromPlayer(playerManager.getCurentPlayer());
            board.display();
            moveCount++;
            playerManager.playerSwitch();
            if (isOver()) {
                view.gameIsOver();
                break;
            }
        }
    }

    public boolean isOver() {
        return isBoardFull() || hasThreeAligned();
    }

    public void getMoveFromPlayer(Player currentPlayer) {
        int x, y;
        boolean isValidMove = false;
        do {
            if (currentPlayer instanceof HumanPlayer) {
                view.defineCoordinateToAsk("x");
                x = interactionUtilisateur.getUserInput();
                if (!interactionUtilisateur.verifyUserInput(x)) {
                   view.invalidCoordinate("x");
                    continue; // Continue la boucle depuis le début
                }

                view.defineCoordinateToAsk("y");
                y = interactionUtilisateur.getUserInput();
                if (!interactionUtilisateur.verifyUserInput(y)) {
                   view.invalidCoordinate("y");
                    continue; // Continue la boucle depuis le début
                }

                isValidMove = interactionUtilisateur.verifyIfCellIsEmpty(x, y);
                if (isValidMove) {
                   interactionUtilisateur.occupyCell(x, y, currentPlayer.getRepresentation());
                } else {
                  view.cellIsAlreadyOccupied();
                }
            } else if (currentPlayer instanceof ArtificialPlayer) {
                x = ((ArtificialPlayer) currentPlayer).randomPlay();
                if (!interactionUtilisateur.verifyUserInput(x)) {
                    continue; // Continue la boucle depuis le début
                }

                y = ((ArtificialPlayer) currentPlayer).randomPlay();
                if (!interactionUtilisateur.verifyUserInput(y)) {
                    continue; // Continue la boucle depuis le début
                }

                isValidMove = interactionUtilisateur.verifyIfCellIsEmpty(x, y);
                if (isValidMove) {
                   interactionUtilisateur.occupyCell(x, y, currentPlayer.getRepresentation());
                } else {
                   view.cellIsAlreadyOccupied();
                }
            }
        } while (!isValidMove);
    }

    private boolean isBoardFull() {
        for (Cell cell : board.getCellObjects()) {
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
        for (int i = 0; i < board.getSize(); i++) {
            if (!board.getCellObjects()[i * board.getSize()].isEmpty() &&
                    board.getCellObjects()[i * board.getSize()].getRepresentation().equals(board.getCellObjects()[i * board.getSize() + 1].getRepresentation()) &&
                    board.getCellObjects()[i * board.getSize() + 1].getRepresentation().equals(board.getCellObjects()[i * board.getSize() + 2].getRepresentation())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < board.getSize(); i++) {
            if (!board.getCellObjects()[i].isEmpty() &&
                    board.getCellObjects()[i].getRepresentation().equals(board.getCellObjects()[i + board.getSize()].getRepresentation()) &&
                    board.getCellObjects()[i + board.getSize()].getRepresentation().equals(board.getCellObjects()[i + 2 * board.getSize()].getRepresentation())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        // Première diagonale
        if (!board.getCellObjects()[0].isEmpty() &&
                board.getCellObjects()[0].getRepresentation().equals(board.getCellObjects()[board.getSize() + 1].getRepresentation()) &&
                board.getCellObjects()[board.getSize() + 1].getRepresentation().equals(board.getCellObjects()[2 * board.getSize() + 2].getRepresentation())) {
            return true;
        }

        // Deuxième diagonale
        if (!board.getCellObjects()[board.getSize() - 1].isEmpty() &&
                board.getCellObjects()[board.getSize() - 1].getRepresentation().equals(board.getCellObjects()[2 * board.getSize() - 1].getRepresentation()) &&
                board.getCellObjects()[2 * board.getSize() - 1].getRepresentation().equals(board.getCellObjects()[3 * board.getSize() - 3].getRepresentation())) {
            return true;
        }

        return false;
    }


}
