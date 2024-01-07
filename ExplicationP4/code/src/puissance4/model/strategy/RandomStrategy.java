package puissance4.model.strategy;

import java.util.ArrayList;
import java.util.Random;

import puissance4.model.Game;
import puissance4.model.Player;
import puissance4.model.State;

public class RandomStrategy implements IStrategy {

    @Override
    public int playStrategy(State state) {
        State s = state;
        ArrayList<Integer> validMoves  = s.getValidPlay();
        int r = new Random().nextInt(validMoves.size());
        return validMoves.get(r);
    }

}
