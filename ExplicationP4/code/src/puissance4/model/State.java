package puissance4.model;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import util.observer.AbstractListenableModel;

/*
 * Interface représentant un état d'un jeu
 */
public class State extends AbstractListenableModel {

    private int[][] grille;
    private Game game;
    private Player currentPlayer;
    public int[] colState;
    private State prevState;
    public String StateKey;

    /**
     * Constructeur d'un état initial
     * 
     * @param game
     */
    public State(Game game) {
        this.game = game;
        this.grille = new int[this.game.getWidth()][this.game.getHeight()]; // WIDTH columns of size HEIGHT
        this.currentPlayer = this.game.getp1();
        this.colState = new int[this.game.getWidth()];
        this.prevState = null;
        this.StateKey = this.getStateKey();
    }

    /**
     * State deepcopy
     * 
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
        this.prevState = state;
        this.StateKey = this.getStateKey();
    }

    /**
     * Constructor for a State
     * 
     * @param grille        the grid
     * @param colState      the state of each columns (are they full or not)
     * @param game          the game which the state is associated to
     * @param currentPlayer the current player on this state
     */
    public State(int[][] grille, int[] colState, Game game, Player currentPlayer, State prevState) {
        this.game = game;
        this.colState = colState;
        this.currentPlayer = currentPlayer;
        this.grille = grille;
<<<<<<< HEAD
        this.prevState = prevState;
=======
        this.prevState = null;
        this.StateKey = this.getStateKey();
>>>>>>> 302c6f580c2a05ac939fb8f881bbd7b188fde0ba
    }

    /**
     * Constructor for a state with serialized data
     * inputs all the move from a string passed in argument
     * Expects the string to be a valid sequence of moves
     * 
     * @param moves the sequence of move
     * @param game  the game associated
     */
    public State(String moves, Game game) {
        this.game = game;
        this.currentPlayer = this.game.getp1();
        String[] splitMoves = moves.split("");
        // System.out.println(Arrays.toString(splitMoves));
        this.grille = new int[this.game.getWidth()][this.game.getHeight()];
        this.colState = new int[this.game.getWidth()];
        // Inputs all the moves in the state
        for (int i = 0; i < splitMoves.length; i++) {
            int move = Integer.parseInt(splitMoves[i]);
            // System.out.println(move);
            this.grille[move][colState[move]] = this.currentPlayer.getId();
            this.colState[move] += 1;
            this.currentPlayer = this.getNextPlayer();
        }
        this.prevState = null;
        this.StateKey = this.getStateKey();
    }

    public int checkLigne(int i, int j, int length) {
        int c = grille[i][j];

        if (c != 0) {
            for (int k = 0; k < length; k++) {
                if (grille[i][j] != grille[i + k][j]) {
                    return 0;
                }
            }
            return grille[i][j];

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
    public int checkColonne(int i, int j, int length) {
        int c = grille[i][j];

        if (c != 0) {
            for (int k = 0; k < length; k++) {
                if (grille[i][j] != grille[i][j + k]) {
                    return 0;
                }
            }
            return grille[i][j];

        }

        return 0;
    }

    public int checkDiagonal(int i, int j, int length) {
        int c = grille[i][j];

        if (c != 0) {
            for (int k = 0; k < length; k++) {
                if (grille[i][j] != grille[i + k][j + k]) {
                    return 0;
                }
            }
            return grille[i][j];

        }

        return 0;
    }

    public int checkDiagonalBw(int i, int j, int length) {

        int c = grille[i][j];

        if (c != 0) {
            for (int k = 0; k < length; k++) {
                if (grille[i][j] != grille[i - k][j + k]) {
                    return 0;
                }
            }
            return grille[i][j];

        }

        return 0;
    }

    public int hasWon() {
        int winner = 0;
        int l = this.game.getWinningLength();
        int k = l - 1;

        for (int i = 0; i < this.game.getWidth(); i++) {
            for (int j = 0; j < this.game.getHeight(); j++) {

                if (grille[i][j] != 0) {
                    if (j < this.game.getHeight() - k) {
                        winner = checkColonne(i, j, l);

                        if (winner != 0) {

                            return winner;
                        }
                    }
                    if (i < this.game.getWidth() - k) {
                        winner = checkLigne(i, j, l);

                        if (winner != 0) {

                            return winner;
                        }

                    }
                    if (i < this.game.getWidth() - k && j < this.game.getHeight() - k) {
                        winner = checkDiagonal(i, j, l);
                        if (winner != 0) {

                            return winner;
                        }
                    }
                    if (i >= k && j < this.game.getHeight() - k) {
                        winner = checkDiagonalBw(i, j, l);
                        if (winner != 0) {

                            return winner;
                        }
                    }
                }
            }
        }
        return winner;
    }

    public boolean isDone() {
        return this.isFull() || this.hasWon() != 0;
    }

    public boolean isFull() {
        boolean full = true;
        for (int i = 0; i < this.game.getWidth(); i++) {
            if (colState[i] < this.game.getHeight()) {
                full = false;
            }
        }
        return full;
    }

    /**
     * Returns wether a move is a winning move
     * 
     * @param move the move
     * @return true if the current player wins by playing the move so, false
     *         otherwise
     */
    public boolean isWinningMove(int move) {
        State played = this.play(move, false);
        return played.hasWon() == this.currentPlayer.getId();
    }

    public int nbMoves() {
        int res = 0;
        for (int i = 0; i < colState.length; i++) {
            res += colState[i];
        }
        return res;
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
     * Function playing a move and returning the next state, aswell as updating
     * currentState in the game to the next state
     * 
     * @param move the move to play
     * @return the next state
     * @throws UnknownError if move is invalid
     */
    public State play(int move) throws UnknownError {
        return this.play(move, true);
    }

    /**
     * Function playing a move and returning the next state
     * 
     * @param move       the move to play
     * @param updateGame true if updates the game currentState, false otherwise
     * @return the next state
     * @throws UnknownError if move is invalid
     */
    public State play(int move, boolean updateGame) throws UnknownError {
        ArrayList<Integer> plays = this.getValidPlay();
        if (!plays.contains(move)) {
            System.out.println("gros naze");
            throw new UnknownError("move non valide");
        } else {
            // System.out.println(move);
            // System.out.println(colState[move]);

            int gWidth = this.game.getWidth();
            int gHeight = this.game.getHeight();

            // DeepCopy grille en premier
            int[][] newGrille = new int[gWidth][gHeight];
            for (int i = 0; i < gWidth; i++) {
                for (int j = 0; j < gHeight; j++) {
                    newGrille[i][j] = this.grille[i][j];
                }
            }

            // Applique le coup sur la nouvelle grille
            // le coup est joué en width "move", à l'index colState[move] de la colonne
            // height correspondante
            newGrille[move][colState[move]] = currentPlayer.getId();

            // DeepCopy colState
            int[] newColState = new int[gWidth];
            for (int i = 0; i < gWidth; i++) {
                newColState[i] = colState[i];
            }

            // Update le nouveau colState
            newColState[move] += 1;

            // Retourne un nouveau state correspondant au State courant après avoir effectué
            // le coup
            State nextState = new State(newGrille, newColState, this.game, this.getNextPlayer(), this);
            // Update l'état du game si demandé
            if (updateGame)
                this.game.updateState(nextState);
            fireChange();
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

    // old and wrong
    /*
     * public void printState() {
     * System.out.println(Arrays.deepToString(grille).replace("], ",
     * "]\n").replace("[[", "[").replace("]]", "]"));
     * }
     */

    /**
     * Print the state of the grid in a way that connect4 looks like
     */
    public void printStateClean() {
        // Go through the height in reverse, to have lowest (first indexes of height) on
        // bottom
        for (int i = this.game.getHeight() - 1; i >= 0; i--) {
            System.out.print("[");
            // Go through width in the right order
            for (int j = 0; j < this.game.getWidth(); j++) {
                System.out.print(this.grille[j][i] + (j != this.game.getWidth() - 1 ? "," : ""));
            }
            System.out.println("]");
        }
    }

    /**
     * Print the grid as raw lists
     */
    public void printRawState() {
        for (int i = 0; i < this.game.getWidth(); i++) {
            System.out.println(Arrays.toString(this.grille[i]));
        }
    }

    /**
     * Print column states
     */
    public void printColState() {
        System.out.println(Arrays.toString(colState));
    }

    /**
     * Print current player
     */
    public void printCurrentPlayer() {
        System.out.println(this.currentPlayer);
    }

    /**
     * Print winner
     */
    public void printWinner() {
        System.out.println(this.hasWon());
    }

    /**
     * Print the state of the game with all informations
     */
    public void printStateFull() {
        System.out.print("Current player: ");
        this.printCurrentPlayer();
        System.out.println("Grille:");
        this.printStateClean();
        System.out.println("Column states:");
        this.printColState();
        System.out.println(this.nbMoves());
        System.out.print("Winner: ");
        this.printWinner();
    }

    public String getStateKey() {
        int gWidth = this.game.getWidth();
        int gHeight = this.game.getHeight();
        String temp = "1";
        // DeepCopy grille en premier
        int[][] newGrille = new int[gWidth][gHeight];
        for (int i = 0; i < gWidth; i++) {
            for (int j = 0; j < gHeight; j++) {
                if (this.grille[i][j] == 0) {
                    temp += "00";
                } else if (this.grille[i][j] == 1) {
                    temp += "01";
                } else if (this.grille[i][j] == 2) {
                    temp += "11";
                }
            }
        }

        BigInteger key = new BigInteger(temp, 2);
        String key2 = key.toString(16);

        return key2;
    }

    public String getStateHashCode() {
        int temp = this.grille.hashCode();
        return Integer.toString(temp);
    }

    public State getPrevState() {
        return this.prevState;
    }

}
