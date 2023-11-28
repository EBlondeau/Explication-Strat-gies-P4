package puissance4.strategy;

import java.util.Scanner;

import puissance4.model.State;
import puissance4.strategy.algorithms.Negamax;

public class NegamaxStrategy implements IStrategy {

    @Override
    public int playStrategy(State state) {
        Negamax n = new Negamax();

        int move = n.getBestMove(state);
        return move;

    }

}
