package game.ticTacToe;

import java.util.Scanner;

/**
 * This class represents a Tic Tac Toe game.
 */
public class TicTacToe {

    private GameManager gameManager;
    public TicTacToe() {
        PlayerManager playerManager = new PlayerManager();
        BoardManager boardManager = new BoardManager();
        View view = new View(playerManager, boardManager);
        InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view, boardManager);

        gameManager = new GameManager(boardManager, playerManager, view, interactionUtilisateur );
    }

    public void playGame(){
        gameManager.play();
    }







}