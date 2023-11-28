package puissance4.model;

import java.util.Arrays;
import java.util.Random;

import puissance4.strategy.RandomStrategy;
import puissance4.strategy.UserInputStrategy;
import puissance4.strategy.algorithms.Negamax;

public class DemoSmallGrid {
    public static void main(String[] args) {

        Game game= new Game(new Player(1, new UserInputStrategy()), new Player(2, new RandomStrategy()),4, 5);
        Negamax nega= new Negamax();
        Arrays.toString(nega.getAllScore(game.getCurrentState()));
        while(!game.getCurrentState().isDone()){
            game.getCurrentState().printStateFull();
            int move= game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
            
        }

        game.getCurrentState().printStateFull();
    }
}