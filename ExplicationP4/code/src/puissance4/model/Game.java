package puissance4.model;

import puissance4.strategy.IStrategy;
import puissance4.strategy.RandomStrategy;

public class Game {

    public Player player1;
    public Player player2;
    public State currentState;
    public static final IStrategy DEFAULT_STRATEGY = new RandomStrategy();

    public Game() {
        this.player1 = new Player(1, DEFAULT_STRATEGY);
        this.player2 = new Player(2, DEFAULT_STRATEGY);
        this.currentState = new State(this);
    }

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentState = new State(this);
    }

    public Game(Player player1, Player player2, State currentState) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = currentState;
    }

    /*
     * public void initializeGame(){
     * Player p1 = new Player(1);
     * Player p2 = new Player(2);
     * State s = new State()
     * Game g= new Game(p1, p2, );
     * };
     */

    public Player getp1() {
        return this.player1;
    }

    public Player getp2() {
        return this.player2;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void updateState(State state){
        this.currentState=state;
    }

}
