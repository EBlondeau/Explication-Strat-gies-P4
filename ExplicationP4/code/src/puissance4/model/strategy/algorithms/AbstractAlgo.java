package puissance4.model.strategy.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import puissance4.model.State;

/**
 * Abstract class representing an algorithm for the Connect 4
 * Has a abstract function algorithm to implement the wanted algorithm
 * (negamax,alphabeta...)
 * Also has a function to get the scores returned by the algorithm
 */
public abstract class AbstractAlgo {

    private int depth;
    protected int cpt;
    public static final int DEFAULT_DEPTH = 50;

    public AbstractAlgo(int depth) {
        this.depth = depth;
    }

    public AbstractAlgo() {
        this.depth = DEFAULT_DEPTH;
    }

    /**
     * Fonction implémentant un algoritme
     * Function implementing the algorithm
     * 
     * @param state State to apply the algorithm
     * @param depth Wanted Depth
     * @return Best score
     */
    public abstract int algorithm(State state, int depth);

    /**
     * Fonction retournant le meilleur coup pour un état donné
     * Function returning the best move for a given state
     * 
     * @param state Given State
     * @return Best move for this state
     */

    public int getBestMove(State state) {
        int[] scores = this.getAllScore(state);
        int max = -(state.getGame().getWidth() * state.getGame().getHeight());
        int res = -1;
        int steps = (scores.length);
        int i = (0 + scores.length) >> 1;
        for (int q = 0; q < steps; q++) {
            int index = i + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));
            if (scores[index] > max) {
                max = scores[index];
                res = index;
            }
        }
        return res;
    }

    /**
     * Function returning the scores of each valid moves for a given state
     * 
     * @param state Given stae
     * @return list of scores
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
     * Function returning the score of a move for a given State
     * 
     * @param state Given State
     * @param move  Corresponding Move
     * @return Score of the move
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

    public int getCpt() {
        return this.cpt;
    }

    public void resetCpt() {
        this.cpt = 0;
    }

    public void setCpt(int c) {
        this.cpt = c;
    }
}
