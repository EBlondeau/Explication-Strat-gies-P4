package puissance4.model;

import puissance4.strategy.IStrategy;
import puissance4.strategy.RandomStrategy;

public class Game {

    public static final int DEFAULT_HEIGHT=7;
    public static final int DEFAULT_WIDTH=6;

    private int height;
    private int width;
    private Player player1;
    private Player player2;
    private State currentState;
    private static final IStrategy DEFAULT_STRATEGY = new RandomStrategy();


    /**
     * Constructeur par défaut d'une partie
     */
    public Game() {
        this(new Player(1, DEFAULT_STRATEGY), new Player(2, DEFAULT_STRATEGY));
    }

    /**
     * Constructeur d'une partie avec des joueurs définis, à l'état de base
     * @param p1 le joueur 1 (rouge)
     * @param p2 le joueur 2 (jaune)
     */
    public Game(Player p1, Player p2) {
        this(p1, p2, DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    /**
     * Constructeur d'une partie avec des joueurs définis et un état défini
     * @param player1 le joueur 1 (rouge)
     * @param player2 le joueur 2 (jaune)
     * @param currentState l'état prédifini
     */
    public Game(Player player1, Player player2, State currentState) {
        this(player1, player2, DEFAULT_HEIGHT, DEFAULT_WIDTH, currentState);
    }

    /**
     * Constructeur d'une partie avec des joueurs et une taille définie, à l'état initial
     * @param player1 le joueur 1 (rouge)
     * @param player2 le joueur 2 (jaune)
     * @param height la hauteur de la grille
     * @param width la largeur de la grille
     */
    public Game(Player player1, Player player2, int height, int width){
        this(player1, player2, DEFAULT_HEIGHT, DEFAULT_WIDTH, null);
        this.currentState = new State(this);
    }

    /**
     * Constructeur complet d'une partie
     * @param player1
     * @param player2
     * @param height
     * @param width
     * @param currentState
     */
    public Game(Player player1, Player player2, int height, int width, State currentState){
        this.width=width;
        this.height=height;
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = currentState;
        this.player1.setGame(this);
        this.player2.setGame(this);
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

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void updateState(State state){
        this.currentState=state;
    }

}
