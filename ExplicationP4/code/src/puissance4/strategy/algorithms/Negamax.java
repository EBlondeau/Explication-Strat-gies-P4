package puissance4.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

public class Negamax {
    private int depth;
    public static final int DEFAULT_DEPTH = 4;

    public Negamax(int depth) {
        this.depth = depth;
    }

    public Negamax() {
        this.depth = DEFAULT_DEPTH;
    }

    public int getBestMove(State state) {
        int[] scores = this.getAllScore(state);
        int max = -1;
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
            } else
                res[i] = 0;
        }

        return res;
    }

    public int getScore(State state, int move) {
        State nextState = state.play(move, false);
        return this.negamax(nextState, this.depth);
    }

    public int negamax(State state, int depth) {
        int gWidth = state.getGame().getWidth();
        int gHeight = state.getGame().getHeight();
        // Check for a draw
        if (state.isDone() || depth == 0) {
            return 0;
        }
        for (int move : state.getValidPlay()) {
            // System.out.println("MOVE AAA: " + move);
            if (state.isWinningMove(move)) {
                return gWidth * gHeight + 1 - state.nbMoves() / 2;
            }
        }

        int bestScore = -(gWidth * gHeight);

        for (int move : state.getValidPlay()) {
            State nextState = state.play(move, false);
            int score = -(negamax(nextState, depth - 1));
            if (score > bestScore)
                bestScore = score;
        }

        return bestScore;
    }

}
