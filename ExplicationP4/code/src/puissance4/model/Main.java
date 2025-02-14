package puissance4.model;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import puissance4.model.strategy.AlgorithmStrategy;
import puissance4.model.strategy.RandomStrategy;
import puissance4.model.strategy.UserInputStrategy;
import puissance4.model.strategy.algorithms.AlphabetaTT;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        TranspositionTable t = new TranspositionTable();
        System.out.println(t.size());

        Game game = new Game(new Player(1, new AlgorithmStrategy(new AlphabetaTT(t))),
                new Player(2, new AlgorithmStrategy(new AlphabetaTT(t))), 5, 5);
        game.setWinningLength(4);
        while (!game.getCurrentState().isDone()) {
            game.getCurrentState().printStateFull();
            int move = game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
            System.out.println(game.getCurrentState().getValidPlay());
            System.out.println("table" + t.getFirstEntry());
            System.out.println("table size " + t.size());
        }
        System.out.println(game.VictoryType());
        game.getCurrentState().printStateFull();
        System.out.println("Victoire: " + game.VictoryType());

    }
}