package puissance4.model;

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
     * Deepcopy d'un état
     * 
     * @param state
     */
    public State(State state) {
        this.game = state.getGame();
        this.currentPlayer = state.getCurrentPlayer();
        int[][] oldStateGrille = state.getGrille();
        for (int i = 0; i < this.game.getWidth(); i++) {
            for (int j = 0; j < this.game.getHeight(); j++) {
                this.grille[i][j] = oldStateGrille[i][j];
            }
        }
        this.game.updateState(this);
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
                        if (res == currentPlayer.id) {
                            return r;
                        }
                    }
                    if (i < this.game.getWidth() - 3) {
                        res = checkColonne(i, j);
                        if (res == currentPlayer.id) {
                            return r;
                        }

                    }
                    if (i < this.game.getWidth() - 3 && j < this.game.getHeight() - 3) {
                        res = checkDiagonal(i, j);
                        if (res == currentPlayer.id) {
                            return r;
                        }
                    }
                    if (i > 2 && j <= this.game.getHeight() - 3) {

                        res = checkDiagonalBw(i, j);
                        if (res == currentPlayer.id) {
                            return r;
                        }
                    }
                }
            }
        }
        return r;
    }

    /**
     * renvoie une liste de taille this.game.getWidth() indiquant l'etat jouable ou non des
     * colonnes
     * 
     * @return list of valid plays
     */
    public boolean[] getValidPlay() {
        boolean[] res = new boolean[this.game.getWidth()];
        for (int i = 0; i < this.game.getWidth(); i++) {
            if (colState[i] != this.game.getHeight()) {
                res[i] = true;
            }
        }
        return res;
    }

    public State play(int move) throws UnknownError {
        boolean[] plays = this.getValidPlay();
        if (plays[move] == false) {
            System.out.println("gros naze");
            throw new UnknownError("move non valide");
        } else {
            grille[this.game.getHeight() - colState[move]][move] = currentPlayer.id;
            colState[move] += 1;
            return new State(this);
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int[][] getGrille() {
        return this.grille;
    }

    public Game getGame() {
        return this.game;
    }

}
