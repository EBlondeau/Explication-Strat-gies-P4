package puissance4.model.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

/**
 * Classe abstraite représentant un Algoritme pour le puissance 4
 * Consiste en une fonction abstraite Algoritme qui permet d'implementer l'algo souhaité
 * Ainsi que de fonction permettant d'obtenir les scores retournés par cet algo
 */
public abstract class AbstractAlgo {

    private int depth;
    public static final int DEFAULT_DEPTH = 50;

    public AbstractAlgo(int depth) {
        this.depth = depth;
    }

    public AbstractAlgo() {
        this.depth = DEFAULT_DEPTH;
    }

    /**
     * Fonction implémentant un algoritme
     * @param state l'état sur lequel appliquer l'algoritme
     * @param depth la profondeur souhaitée
     * @return le score maximum trouvé
     */
    public abstract int algorithm(State state, int depth);

    /**
     * Fonction retournant le meilleur coup pour un état donné
     * @param state l'état donné
     * @return le meilleur coup pour cet état
     */
    public int getBestMove(State state) {
        int[] scores = this.getAllScore(state);
        int max = -(state.getGame().getWidth() * state.getGame().getHeight());
        int index = -1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Fonction retournant les scores de chaques coups possibles pour un état donné
     * @param state l'état donné
     * @return une liste des scores
     */
    public int[] getAllScore(State state) {
        ArrayList<Integer> validPlays = state.getValidPlay();
        int[] res = new int[state.getGame().getWidth()];
        for (int i = 0; i < state.getGame().getWidth(); i++) {
            if (validPlays.contains(i)) {
                res[i] = this.getScore(state, i);
            } else {
                res[i] = -100000;
            }

        }

        return res;
    }

    /**
     * Fonction retournant le score pour un coups donné sur un état donné
     * @param state l'état donné
     * @param move le coup correspondant
     * @return le score de ce coup
     */
    public int getScore(State state, int move) {
        State nextState = state.play(move, false);
        return -this.algorithm(nextState, this.depth);
    }
}
