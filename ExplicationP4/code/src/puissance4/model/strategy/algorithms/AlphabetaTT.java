package puissance4.model.strategy.algorithms;

import java.util.ArrayList;
import java.util.List;

import puissance4.model.State;
import puissance4.model.TranspositionTable;

public class AlphabetaTT extends AbstractAlgo {

    public static final int alpha = -1000000;
    public static final int beta = 1000000;

    public int negAlphabeta(State state, int depth, int alpha, int beta, TranspositionTable tt) {
        int alphOrig = alpha;
        int stKey = state.getStateKey();

        int gWidth = state.getGame().getWidth();
        int gHeight = state.getGame().getHeight();

        List<Integer> ttEntry = tt.getVal(stKey);
        int ttDepth = tt.getDepth(stKey);
        int ttValue = tt.getValue(stKey);
        if (ttEntry != null && ttDepth >= depth) {
            if (tt.getFlag(stKey) == 0) {
                return tt.getValue(stKey);
            } else if (tt.getFlag(stKey) == -1) {
                alpha = Math.max(alpha, tt.getValue(stKey));

            } else if (tt.getFlag(stKey) == 1) {
                beta = Math.min(beta, tt.getValue(stKey));
            }
        }

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
                    -negAlphabeta(nextState, depth - 1, -beta, -alpha, tt));

            if (value > alpha)
                alpha = value;
            if (alpha >= beta) {
                break;
            }
        }

        List<Integer> lTemp = new ArrayList();
        lTemp.add(depth);
        lTemp.add(value);
        if (value <= alphOrig) {
            lTemp.add(1);
        } else if (value >= beta) {
            lTemp.add(-1);
        } else {
            lTemp.add(0);
        }
        tt.put(stKey, lTemp);

        return value;
    }

    @Override
    public int algorithm(State state, int depth) {
        return this.negAlphabeta(state, depth, alpha, beta, null);
    }

}
