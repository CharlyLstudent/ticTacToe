package game.ticTacToe;

public class View {
    PlayerManager playerManager;
    BoardManager boardManager;


    public View(PlayerManager playerManager, BoardManager boardManager) {
        this.playerManager = playerManager;
        this.boardManager = boardManager;
    }

    public void playerTurn() {
        System.out.println("Tour du Joueur" + playerManager.getCurentPlayer().getRepresentation());
    }

    public void gameIsOver() {
        System.out.println("Jeu terminé !");
    }

    public void defineCoordinateToAsk(String coordinate) {
        System.out.println("Veuillez entrer la coordonnée" + coordinate + " : ");
    }

    public void invalidCoordinate(String coordinate) {
        System.out.println("Coordonnée " + coordinate + " invalide. Veuillez saisir une coordonnée entre 0 et " + (boardManager.getSize() - 1));
    }

    public void cellIsAlreadyOccupied() {
        System.out.println("La case est déjà occupée. Veuillez choisir une autre case.");
    }

    public void askUserCoordinate() {
        System.out.println("Saisissez une coordonnée (entre 0 et 2).");
    }

    public void invalidCoordinate() {
        System.out.println("Erreur : Veuillez saisir une information valide");
    }
}
