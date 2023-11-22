package puissance4.strategy;

import puissance4.model.Player;
import puissance4.model.Game;

/**
 * Interface for players' strategies.
 */
public interface IStrategy {
    public void playStrategy(Player p, Game g);
}