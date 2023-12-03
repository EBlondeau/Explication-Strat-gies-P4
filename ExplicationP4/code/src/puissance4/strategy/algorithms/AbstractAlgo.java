package puissance4.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

public abstract class AbstractAlgo {

    private int depth;
    public static final int DEFAULT_DEPTH = 25;

    public AbstractAlgo(int depth) {
        this.depth = depth;
    }

    public AbstractAlgo() {
        this.depth = DEFAULT_DEPTH;
    }

    public abstract int algorithm(State state, int depth);

    public int getBestMove(State state) {
        int[] scores = this.getAllScore(state);
        int max = -(state.getGame().getWidth()*state.getGame().getHeight());
        int index = -1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
                index = i;
            }
        }
        return index;
    }

    public int[] getAllScore(State state) {
        ArrayList<Integer> validPlays = state.getValidPlay();
        int[] res = new int[state.getGame().getWidth()];
        for (int i = 0; i < state.getGame().getWidth(); i++) {
            if (validPlays.contains(i)) {
                res[i] = this.getScore(state, i);
            } 
            else{
                res[i] = -100000;
            }
                
        }

        return res;
    }

    public int getScore(State state, int move) {
        State nextState = state.play(move, false);
        return -this.algorithm(nextState, this.depth);
    }
}
