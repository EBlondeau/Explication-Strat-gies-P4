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
        // t.table = t.LoadHashMapFromInternalStorage("all2.tt");
        System.out.println(t.size());

        Game game = new Game(new Player(1, new AlgorithmStrategy(new AlphabetaTT(t))),
                new Player(2, new AlgorithmStrategy(new AlphabetaTT(t))), 4, 4);
        game.setWinningLength(4);
        while (!game.getCurrentState().isDone()) {
            game.getCurrentState().printStateFull();
            int move = game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
            System.out.println(game.getCurrentState().getValidPlay());
            System.out.println("table" + t.FindFirstEntryWithArrayMethod());
            System.out.println("table size " + t.size());
        }
        // System.out.println("Victoire: " + game.VictoryType());

        game.getCurrentState().printStateFull();
        t.SaveHashMapToInternalStorage("all2.tt", t.table);
        System.out.println(t.size());

        TranspositionTable t2 = new TranspositionTable();
        System.out.println(t2.LoadHashMapFromInternalStorage("all2.tt").size());
        System.out.println(t2.size());
    }
}