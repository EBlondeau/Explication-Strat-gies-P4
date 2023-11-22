package puissance4.model;

public class Game {

    public Player player1;
    public Player player2;
    public State currentState;

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

    public State getCurrenState() {
        return this.currentState;
    }

}
