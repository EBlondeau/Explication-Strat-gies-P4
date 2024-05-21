package puissance4.view.game;

import puissance4.model.Game;

public class GameDemo {
    public static void main(String[] args) {
        Game game= new Game("0_5_4_3");
        //Game game= new Game();
        GameGUI gGUI= new GameGUI(game);
    }
}
