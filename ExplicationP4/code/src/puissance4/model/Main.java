package puissance4.model;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Game game= new Game();

        game.getCurrentState().printState();

        /*while(!game.getCurrentState().hasWon()){
            game.getCurrentState().getCurrentPlayer().play();
            game.getCurrentState().printState();
        }*/

        game.getCurrentState().play(1);
        game.getCurrentState().printState();


    }
}