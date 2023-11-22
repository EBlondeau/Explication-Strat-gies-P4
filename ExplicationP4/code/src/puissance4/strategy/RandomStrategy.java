package puissance4.strategy;

import java.util.Random;

import puissance4.model.Game;
import puissance4.model.Player;
import puissance4.model.State;

public class RandomStrategy implements IStrategy {

    @Override
    public void playStrategy(Player p, Game g) {
        State s = g.getCurrentState();
        boolean[] b = s.getValidPlay();
        int r = new Random().nextInt(b.length);
        s.play(r);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playStrategy'");
    }

}
