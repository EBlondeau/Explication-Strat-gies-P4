package puissance4.strategy;

import java.util.Scanner;

import puissance4.model.State;
import puissance4.strategy.algorithms.Alphabeta;
import puissance4.strategy.algorithms.Negamax;

public class NegamaxStrategy implements IStrategy {

    @Override
    public int playStrategy(State state) {
        Alphabeta n = new Alphabeta();

        int move = n.getBestMove(state);
        return move;

    }

}
