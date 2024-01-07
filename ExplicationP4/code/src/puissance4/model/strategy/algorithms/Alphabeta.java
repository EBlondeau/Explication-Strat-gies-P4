package puissance4.model.strategy.algorithms;

import puissance4.model.State;

public class Alphabeta extends AbstractAlgo {

    public static final int alpha = -1000000;
    public static final int beta = 1000000;

    public int negAlphabeta(State state, int depth, int alpha, int beta) {
        int gWidth = state.getGame().getWidth();
        int gHeight = state.getGame().getHeight();

        int winner = state.hasWon();
        if (winner != 0) {
            int i = -1;
            if (winner == state.getCurrentPlayer().getId()) {
                i = 1;
            }
            return i * ((gWidth * gHeight) + 1 - state.nbMoves()) / 2;
        }

        // Check for a draw
        if (state.isFull() || depth == 0) {
            return 0;
        }

        int value = -1000000;

        for (int move : state.getValidPlay()) {
            State nextState = state.play(move, false);
            value = Math.max(value,
             -negAlphabeta(nextState, depth - 1, -beta, -alpha));
            if (value >= beta)
                return value;
            if (value > alpha)
                alpha = value;
        }

        return value;
    }

    @Override
    public int algorithm(State state, int depth) {
        return this.negAlphabeta(state, depth, alpha, beta);
    }

}
