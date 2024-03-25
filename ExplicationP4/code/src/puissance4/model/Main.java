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
        t.put(1, 1);
        t.put(2, 1);
        t.put(3, 1);
        t.put(4, 1);
        t.put(5, 1);
        System.out.println(t.get(5));
        t.put(6, 0);
        System.out.println(t.get(2));
        System.out.println(t.size());
        t.put(7, 0);
        System.out.println(t.get(14));
        System.out.println(t.size());

    }
}