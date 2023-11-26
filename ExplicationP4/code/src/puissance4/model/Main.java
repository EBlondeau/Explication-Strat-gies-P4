package puissance4.model;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Game game= new Game();

        while(!game.getCurrentState().isDone()){
            game.getCurrentState().printStateFull();
            int move= game.getCurrentState().getCurrentPlayer().play();
            System.out.println("Played move:" + move);
            game.getCurrentState().play(move);
        }

        game.getCurrentState().printStateFull();
    }
}