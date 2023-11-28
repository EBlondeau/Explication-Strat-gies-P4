package puissance4.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

public class Negamax extends AbstractAlgo {

    public int algorithm(State state, int depth) {
        int gWidth = state.getGame().getWidth();
        int gHeight = state.getGame().getHeight();
        // Check for a draw
        if (state.isFull() || depth == 0) {
            return 0;
        }
        for (int move : state.getValidPlay()) {
            // System.out.println("MOVE AAA: " + move);
            if (state.isWinningMove(move)) {
                //System.out.println(gWidth * gHeight + 1 - state.nbMoves() / 2);
                return gWidth * gHeight + 1 - state.nbMoves() / 2;
            }
        }

        int bestScore = -(gWidth * gHeight);
        //System.out.println(bestScore);
        for (int move : state.getValidPlay()) {
            State nextState = state.play(move, false);
            int score = -algorithm(nextState, depth - 1);
            //System.out.print(score<0 ? score : "");
            if (score > bestScore)
                bestScore = score;
        }

        return bestScore;
    }

}
