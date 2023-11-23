package puissance4.model;

import puissance4.strategy.IStrategy;

public class Player {
    private IStrategy strategy;
    private int id;
    private Game game;

    public Player(int id, IStrategy strategy){
        this.id = id;
        this.strategy = strategy;
    }

    public void play(){
        this.strategy.playStrategy(game.getCurrentState());
    }

    public int getId(){
        return this.id;
    }

    public IStrategy getStrategy(){
        return this.strategy;
    }

    public void setGame(Game game){
        this.game= game;
    }
}
