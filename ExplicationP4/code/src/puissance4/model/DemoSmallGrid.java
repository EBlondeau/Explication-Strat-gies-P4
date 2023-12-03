package puissance4.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import puissance4.strategy.RandomStrategy;
import puissance4.strategy.UserInputStrategy;
import puissance4.strategy.algorithms.AbstractAlgo;
import puissance4.strategy.algorithms.Alphabeta;
import puissance4.strategy.algorithms.Negamax;

public class DemoSmallGrid {
    public static void main(String[] args) {

        Game game= new Game(new Player(1, new UserInputStrategy()), new Player(2, new UserInputStrategy()),6, 7);
        AbstractAlgo nega= new Negamax();
        AbstractAlgo alpha= new Alphabeta();
        //String scores= new ArrayList
        //int score= nega.getScore(game.getCurrentState(), 0);
        
        while(!game.getCurrentState().isDone()){
            game.getCurrentState().printStateFull();
            int move= game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
            //String scores= Arrays.toString(nega.getAllScore(game.getCurrentState()));
            String scores= Arrays.toString(alpha.getAllScore(game.getCurrentState()));
            System.out.println(scores);
            
        }

        game.getCurrentState().printStateFull();
    }
}