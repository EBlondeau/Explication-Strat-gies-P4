package puissance4.model.strategy;

import puissance4.model.Player;
import puissance4.model.State;
import puissance4.model.Game;

/**
 * Interface for players' strategies.
 */
public interface IStrategy {
    /**
     * Strategie de jeu
     * @param state l'état sur lequel appliquer la stratégies
     * @return un coup à jouer
     */
    public int playStrategy(State state); 
}