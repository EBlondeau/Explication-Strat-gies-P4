package puissance4.model;

import java.util.Arrays;

import puissance4.model.strategy.RandomStrategy;
import puissance4.model.strategy.UserInputStrategy;

public class Main {
    public static void main(String[] args) {

        Game game = new Game(new Player(1, new UserInputStrategy()), new Player(2, new UserInputStrategy()));

        /*
         * while(!game.getCurrentState().isDone()){
         * game.getCurrentState().printStateFull();
         * int move= game.getCurrentState().getCurrentPlayer().play();
         * System.out.println("Played move:" + move);
         * game.getCurrentState().play(move);
         * System.out.println(game.getCurrentState().getValidPlay());
         * }
         * 
         * game.getCurrentState().printStateFull();
         */
        TranspositionTable t = new TranspositionTable();

        System.out.println(t.size());

        // int move = game.getCurrentState().getCurrentPlayer().play();
        game.getCurrentState().play(2);
        game.getCurrentState().play(2);
        game.getCurrentState().play(2);
        game.getCurrentState().play(2);
        System.out.println(game.getCurrentState().keyToString());
    }
}