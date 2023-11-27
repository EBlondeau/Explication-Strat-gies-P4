package puissance4.model;

import java.util.Arrays;

import puissance4.strategy.RandomStrategy;
import puissance4.strategy.UserInputStrategy;

public class Main {
    public static void main(String[] args) {

        Game game= new Game(new Player(1, new UserInputStrategy()), new Player(2, new UserInputStrategy()));

        while(!game.getCurrentState().isDone()){
            game.getCurrentState().printStateFull();
            int move= game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
        }

        game.getCurrentState().printStateFull();
    }
}