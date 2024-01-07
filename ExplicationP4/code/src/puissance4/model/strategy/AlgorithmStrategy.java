package puissance4.model.strategy;

import java.util.Scanner;

import puissance4.model.State;
import puissance4.model.strategy.algorithms.AbstractAlgo;
import puissance4.model.strategy.algorithms.Alphabeta;
import puissance4.model.strategy.algorithms.Negamax;

public class AlgorithmStrategy implements IStrategy {

    private AbstractAlgo algo;

    public AlgorithmStrategy(AbstractAlgo algo){
        this.algo=algo;
    }

    @Override
    public int playStrategy(State state) {
        int move = algo.getBestMove(state);
        return move;
    }

}
