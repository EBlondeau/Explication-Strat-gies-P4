package puissance4.model;

import puissance4.model.strategy.AlgorithmStrategy;
import puissance4.model.strategy.IStrategy;
import puissance4.model.strategy.algorithms.AbstractAlgo;
import puissance4.model.strategy.algorithms.Alphabeta;
import puissance4.model.strategy.algorithms.AlphabetaTT;

import java.io.EOFException;

import puissance4.model.State;

public class Player {
    private IStrategy strategy;
    private int id;
    private Game game;

    public Player(int id, IStrategy strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public int play() {
        return this.strategy.playStrategy(game.getCurrentState());
    }

    public int getId() {
        return this.id;
    }

    public IStrategy getStrategy() {
        return this.strategy;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getLastMove(Game game) {
        return this.strategy.playStrategy(game.getCurrentState().getPrevState());

    }

    public int[] getLastMoveSet() {
        if (this.strategy.getClass().equals(AlgorithmStrategy.class)) {

            AlgorithmStrategy as = (AlgorithmStrategy) this.strategy;
            AbstractAlgo aa = as.getAlgo();
            State cs = this.game.getCurrentState();
            return aa.getAllScore(cs.getPrevState());
        } else {
            Alphabeta a = new Alphabeta();
            return a.getAllScore(this.game.getCurrentState().getPrevState());

        }
    }

    @Override
    public String toString() {
        return ("pID: " + this.id + " strat: " + this.strategy.getClass().getSimpleName());
    }
}
