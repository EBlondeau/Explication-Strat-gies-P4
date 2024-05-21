package puissance4.model.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

public class Negamax extends AbstractAlgo {

    /**
     * 
     * @param state la valeur State were we want to compute the value
     * @param depth Max depth allowed
     * @return value of Node
     */
    public int algorithm(State state, int depth) {
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

        if (state.isFull() || depth == 0) {
            return 0;
        }

        int bestScore = -(gWidth * gHeight);
        for (int move : state.getValidPlay()) {
            State nextState = state.play(move, false);
            int score = -algorithm(nextState, depth - 1);
            if (score > bestScore)
                bestScore = score;
        }

        return bestScore;
    }

}
