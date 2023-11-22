package puissance4.model;

import java.util.*;

public class State {
    public Player currentPlayer;
    public Grille grille;
    public Player[] players;

    public State(Player currentPlayer, Grille grille) {
        this.currentPlayer = currentPlayer;
        this.grille = grille;

    }

    public Player nextPlayer() {

        {
            return null;
        }
    }

    public State nextState(int coup) {

        grille.play(currentPlayer.id, coup);
        return new State(nextPlayer(), grille);
    }

    public State[] allNextStates() {
        State[] res = null;
        int width = grille.width;
        for (int i = 0; i < width; i++) {
            Grille g2 = new Grille(grille.height, grille.width);
            g2 = grille;
            g2.play(currentPlayer.id, i);
        }
        return res;
    }
}
