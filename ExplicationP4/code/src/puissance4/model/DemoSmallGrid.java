package puissance4.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import puissance4.model.strategy.NegamaxStrategy;
import puissance4.model.strategy.RandomStrategy;
import puissance4.model.strategy.UserInputStrategy;
import puissance4.model.strategy.algorithms.AbstractAlgo;
import puissance4.model.strategy.algorithms.Alphabeta;
import puissance4.model.strategy.algorithms.Negamax;

public class DemoSmallGrid {
    public static void main(String[] args) {

        Game game = new Game(new Player(1, new NegamaxStrategy()), new Player(2, new NegamaxStrategy()), 4, 4);
        AbstractAlgo nega = new Alphabeta();
        // AbstractAlgo alpha = new Alphabeta();
        // String scores= new ArrayList
        // int score= nega.getScore(game.getCurrentState(), 0);

        while (!game.getCurrentState().isDone()) {
            game.getCurrentState().printStateFull();
            int move = game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
            // String scores= Arrays.toString(nega.getAllScore(game.getCurrentState()));
            String scores = Arrays.toString(nega.getAllScore(game.getCurrentState()));
            System.out.println(game.getCurrentState().hasWon());
            System.out.println(game.getCurrentState().getCurrentPlayer() + " " + scores);

        }

        game.getCurrentState().printStateFull();
    }
}