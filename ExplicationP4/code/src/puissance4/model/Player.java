package puissance4.model;

import puissance4.strategy.IStrategy;

public class Player {
    protected IStrategy strategy;
    public int id;

    public Player(int id, IStrategy strategy) {
        this.id = id;
        this.strategy = strategy;
    }
}
