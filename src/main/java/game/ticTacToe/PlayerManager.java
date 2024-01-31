package game.ticTacToe;

import java.util.Scanner;

public class PlayerManager {
   private Player player1;
   private Player player2;

   private Player currentPlayer;


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
        currentPlayer = player1;
    }

    public Player getCurentPlayer(){
        return this.currentPlayer;
    }
    public int getGameChoice() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("choisissez un mode de jeu");
        System.out.println("0 - Human vs Human");
        System.out.println("1 - Human vs AI");
        System.out.println("2 - AI vs AI");

        return inputUser.nextInt();
    }



    public void playerSwitch(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }else{
            currentPlayer = player1;
        }
    }
}
