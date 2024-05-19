package puissance4.model.strategy.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import puissance4.model.State;

/**
 * Classe abstraite représentant un Algoritme pour le puissance 4
 * Consiste en une fonction abstraite Algoritme qui permet d'implementer l'algo
 * souhaité
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
     * 
     * @param state l'état sur lequel appliquer l'algoritme
     * @param depth la profondeur souhaitée
     * @return le score maximum trouvé
     */
    public abstract int algorithm(State state, int depth);

    /**
     * Fonction retournant le meilleur coup pour un état donné
     * 
     * @param state l'état donné
     * @return le meilleur coup pour cet état
     */
    /*
     * public int getBestMove(State state) {
     * int[] scores = this.getAllScore(state);
     * int max = -(state.getGame().getWidth() * state.getGame().getHeight());
     * int index = -1;
     * for (int i = 0; i < scores.length; i++) {
     * if (scores[i] > max) {
     * max = scores[i];
     * index = i;
     * }
     * }
     * 
     * return index;
     * }
     */
    public int getBestMove(State state) {
        int[] scores = this.getAllScore(state);
        int max = -(state.getGame().getWidth() * state.getGame().getHeight());
        int res = -1;
        int steps = (scores.length);
        int i = (0 + scores.length) >> 1;
        for (int q = 0; q < steps; q++) {
            int index = i + (q % 2 == 0 ? q / 2 : -(q / 2 + 1)); // index lookup here
            if (scores[index] > max) {
                max = scores[index];
                res = index;
            }
        }
        return res;
    }

    /**
     * Fonction retournant les scores de chaques coups possibles pour un état donné
     * 
     * @param state l'état donné
     * @return une liste des scores
     */
    /*
     * public int[] getAllScore(State state) {
     * ArrayList<Integer> validPlays = state.getValidPlay();
     * int[] res = new int[state.getGame().getWidth()];
     * for (int i = 0; i < state.getGame().getWidth(); i++) {
     * if (validPlays.contains(i)) {
     * res[i] = this.getScore(state, i);
     * } else {
     * res[i] = -100000;
     * }
     * 
     * }
     * 
     * return res;
     * }
     */
    public int[] getAllScore(State state) {
        ArrayList<Integer> validPlays = state.getValidPlay();
        int[] res = new int[state.getGame().getWidth()];
        int steps = (state.getGame().getWidth() - 0);
        int i = (0 + state.getGame().getWidth()) >> 1;
        for (int q = 0; q < steps; q++) {
            int index = i + (q % 2 == 0 ? q / 2 : -(q / 2 + 1)); // index lookup here

            if (validPlays.contains(index)) {
                res[index] = this.getScore(state, index);
            } else {
                res[index] = -100000;
            }
        }
        return res;
    }

    /**
     * Fonction retournant le score pour un coups donné sur un état donné
     * 
     * @param state l'état donné
     * @param move  le coup correspondant
     * @return le score de ce coup
     */
    public int getScore(State state, int move) {
        State nextState = state.play(move, false);
        return -this.algorithm(nextState, this.depth);
    }
    /*
     * public int getScoreIterativeDeepening(State state, int move) {
     * State nextState = state.play(move, false);
     * int height = nextState.getGame().getHeight();
     * int width = nextState.getGame().getWidth();
     * int min = (width * height - Arrays.stream(nextState.colState).sum()) / 2;
     * int max = (width * height + 1 - Arrays.stream(nextState.colState).sum()) / 2;
     * while (min < max) {
     * int med = min + (max - min) / 2;
     * if (med <= 0 && min / 2 < med) {
     * med = min / 2;
     * } else if (med >= 0 && max / 2 > med) {
     * med = max / 2;
     * }
     * int res = -this.algorithm(nextState, med);
     * }
     * return -this.algorithm(nextState, this.depth);
     * }
     */
}
