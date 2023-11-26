package puissance4.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Interface représentant un état d'un jeu
 */
public class State {

    private int[][] grille;
    private Game game;
    private Player currentPlayer;
    public int[] colState;

    /**
     * Constructeur d'un état initial
     * 
     * @param game
     */
    public State(Game game) {
        this.game = game;
        this.grille = new int[this.game.getWidth()][this.game.getHeight()];
        this.currentPlayer = this.game.getp1();
        this.colState = new int[this.game.getWidth()];
    }

    /**
     * State deepcopy
     * @param state the state to deepcopy
     */
    public State(State state) {
        this.game = state.getGame();
        this.colState = state.colState;
        this.currentPlayer = state.getCurrentPlayer();
        int[][] oldStateGrille = state.getGrille();
        this.grille = new int[this.game.getWidth()][this.game.getHeight()];
        for (int i = 0; i < this.game.getWidth(); i++) {
            for (int j = 0; j < this.game.getHeight(); j++) {
                this.grille[i][j] = oldStateGrille[i][j];
            }
        }
    }

    /**
     * Constructor for a State
     * @param grille the grid
     * @param colState the state of each columns (are they full or not)
     * @param game the game which the state is associated to
     * @param currentPlayer the current player on this state
     */
    public State(int[][] grille, int[] colState, Game game, Player currentPlayer){
        this.game= game;
        this.colState= colState;
        this.currentPlayer = currentPlayer;
        this.grille= grille;
    }

    public int checkLigne(int i, int j) {
        int c = grille[i][j];

        if (c != 0) {
            if (c == grille[i][j + 1] && c == grille[i][j + 2]
                    && c == grille[i][j + 3]) {
                return grille[i][j];
            }
        }

        return 0;
    }

    /**
     * TEMPORAIRE POUR L'INSTANT C'EST A CHIER MM PAS SUR QUE ÇA MARCHE
     * 
     * regarde si il y a 4 valeurs en colonnes du meme joueur
     * 
     * @return playerID du gagnant
     */
    public int checkColonne(int i, int j) {
        int c = grille[i][j];

        if (c != 0) {
            if (c == grille[i + 1][j] && c == grille[i + 2][j]
                    && c == grille[i + 3][j]) {
                return grille[i][j];
            }
        }

        return 0;
    }

    public int checkDiagonal(int i, int j) {
        int c = grille[i][j];

        if (c != 0) {
            if (c == grille[i + 1][j + 1] && c == grille[i + 2][j + 2]
                    && c == grille[i + 3][j + 3]) {
                return grille[i][j];
            }
        }

        return 0;
    }

    public int checkDiagonalBw(int i, int j) {

        int c = grille[i][j];

        if (c != 0) {
            if (c == grille[i - 1][j + 1] && c == grille[i - 2][j + 2]
                    && c == grille[i - 3][j + 3]) {
                return grille[i][j];
            }
        }
        return 0;
    }

    public boolean hasWon() {
        boolean r = false;
        int res = 0;
        for (int i = 0; i < this.game.getWidth(); i++) {
            for (int j = 0; j < this.game.getHeight(); j++) {

                if (grille[i][j] != 0) {
                    if (j < this.game.getHeight() - 3) {
                        res = checkLigne(i, j);
                        if (res == currentPlayer.getId()) {
                            return r;
                        }
                    }
                    if (i < this.game.getWidth() - 3) {
                        res = checkColonne(i, j);
                        if (res == currentPlayer.getId()) {
                            return r;
                        }

                    }
                    if (i < this.game.getWidth() - 3 && j < this.game.getHeight() - 3) {
                        res = checkDiagonal(i, j);
                        if (res == currentPlayer.getId()) {
                            return r;
                        }
                    }
                    if (i > 2 && j <= this.game.getHeight() - 3) {

                        res = checkDiagonalBw(i, j);
                        if (res == currentPlayer.getId()) {
                            return r;
                        }
                    }
                }
            }
        }
        return r;
    }

    /**
     * renvoie une liste de taille this.game.getWidth() indiquant l'etat jouable ou
     * non des
     * colonnes
     * 
     * @return list of valid plays
     */
    public ArrayList<Integer> getValidPlay() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.game.getWidth(); i++) {
            if (colState[i] < this.game.getHeight()) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * Function playing a move and returning the next state, aswell as updating currentState in the game to the next state
     * @param move the move to play
     * @return the next state
     * @throws UnknownError if move is invalid
     */
    public State play(int move) throws UnknownError{
        return this.play(move, true);
    }

    /**
     * Function playing a move and returning the next state
     * @param move the move to play
     * @param updateGame true if updates the game currentState, false otherwise
     * @return the next state
     * @throws UnknownError if move is invalid
     */
    public State play(int move, boolean updateGame) throws UnknownError {
        ArrayList<Integer> plays= this.getValidPlay();
        if (!plays.contains(move)) {
            System.out.println("gros naze");
            throw new UnknownError("move non valide");
        } else {
            //System.out.println(move);
            //System.out.println(colState[move]);

            int gWidth= this.game.getWidth();
            int gHeight= this.game.getHeight();
        
            // DeepCopy grille en premier
            int[][] newGrille= new int[gWidth][gHeight];
            for (int i = 0; i < gWidth; i++) {
                for (int j = 0; j < gHeight; j++) {
                    newGrille[i][j] = this.grille[i][j];
                }
            }

            // Applique le coup sur la nouvelle grille
            newGrille[gHeight-2 - colState[move]][move] = currentPlayer.getId(); /** TODO: Fix this, seems broken */

            //DeepCopy colState
            int[] newColState= new int[gWidth];
            for(int i =0; i<gWidth; i++){
                newColState[i]=colState[i];
            }

            //Update le nouveau colState
            newColState[move] += 1;

            // Retourne un nouveau state correspondant au State courant après avoir effectué le coup
            State nextState= new State(newGrille, newColState, this.game, this.getNextPlayer());
            if(updateGame) this.game.updateState(nextState);
            return nextState;
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Player getNextPlayer() {
        if (currentPlayer.getId() == 1) {
            return this.game.getp2();
        } else {
            return this.game.getp1();
        }
    }

    public int[][] getGrille() {
        return this.grille;
    }

    public Game getGame() {
        return this.game;
    }

    /** UTILS **/

    public void printState() {
        System.out.println(Arrays.deepToString(grille).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
