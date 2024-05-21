package puissance4.model.strategy;

import puissance4.model.Player;
import puissance4.model.State;
import puissance4.model.Game;

/**
 * Interface for players' strategies.
 */
public interface IStrategy {
    /**
     * Game strategy
     * 
     * @param state State to apply the strategy
     * @return a move
     */
    public int playStrategy(State state);
}