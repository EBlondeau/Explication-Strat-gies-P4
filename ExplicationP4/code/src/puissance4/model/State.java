package puissance4.model;

/*
 * Interface représentant un état d'un jeu
 */
public class State {
    
    public static final int height=6;
    public static final int width=7;

    private int[][] grille;
    private Game game;
    private Player currentPlayer;

    /**
     * Constructeur d'un état initial
     * @param game
     */
    public State(Game game){
        this.game=game;
        this.grille=new int[width][height];
        this.currentPlayer=this.game.getp1();
    }

    /**
     * Deepcopy d'un état
     * @param state
     */
    public State(State state){
        this.game=state.getGame();
        this.currentPlayer=state.getCurrentPlayer();
        int [][] oldStateGrille= state.getGrille();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.grille[i][j] = oldStateGrille[i][j];
            }
        }
    }


    /**
     * Deepcopy d'un état de puissance quatre
     * @param etat l'état a copier
     */
    public EtatPuissanceQuatre(EtatPuissanceQuatre etat){

    }
    public boolean hasWon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasWon'");
    }

    public EtatPuissanceQuatre play(int move) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextState'");
    }

    public int[] getMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }

    public Player getCurrentPlayer(){ return this.currentPlayer;}
    public int[][] getGrille(){ return this.grille; }
    public Game getGame(){ return this.game; }


}
