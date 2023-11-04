package puissance4.model;
import java.util.Arrays;

public class Grille {

    public int height;
    public int width;
    public int[][] grille;
    public int[] state;

    public Grille(int height, int width) {
        this.height = height;
        this.width = width;
        this.grille = new int[width][height];
        this.state = new int[width]; // liste de la hauteur actuelle de chaque colonnes (au début 0 et a chaque coup
                                     // joué sur la colonne +1)

    }

    /**
     * affiche la grille
     */
    public void showGrille() {
        System.out.println(Arrays.deepToString(grille));
    }

    /**
     * affiche la liste des etats des colonnes
     */
    public void showState() {
        System.out.println(Arrays.toString(state));
    }

    /**
     * renvoie une liste de taille width indiquant l'etat jouable ou non des
     * colonnes
     * 
     * @return list of valid plays
     */
    public boolean[] getValidPlay() {
        boolean[] res = new boolean[width];
        for (int i = 0; i < width; i++) {
            if (state[i] != height) {
                res[i] = true;
            }
        }
        return res;
    }

    /**
     * TEMPORAIRE
     * permet de jouer un coup sur la grille
     * 
     * 
     * @param playerID numéro du joueur
     * @param columns  numéro de colonnes sur laquelle jouer
     */
    public void play(int playerID, int columns) {
        boolean[] plays = this.getValidPlay();
        if (plays[columns] == false) {
            System.out.println("gros naze");
            return;
        } else {
            grille[state[columns] + 1][columns] = playerID;
            state[columns] += 1;
        }
    }

    /**
     * TEMPORAIRE POUR L'INSTANT C'EST A CHIER MM PAS SUR QUE ÇA MARCHE
     * 
     * regarde si il y a 4 valeurs en ligne du meme joueur
     * 
     * @return playerID du gagnant
     */
    public int checkLigne(int i, int j) {

        if (grille[i][j] != 0) {
            if (grille[i][j] == grille[i][j + 1] && grille[i][j + 1] == grille[i][j + 2]
                    && grille[i][j + 2] == grille[i][j + 3]) {
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

        if (grille[i][j] != 0) {
            if (grille[i][j] == grille[i + 1][j] && grille[i + 1][j] == grille[i + 2][j]
                    && grille[i + 2][j] == grille[i + 3][j]) {
                return grille[i][j];
            }
        }

        return 0;
    }

    public int chechDiagonal(int i, int j) {

        if (grille[i][j] != 0) {
            if (grille[i][j] == grille[i + 1][j + 1] && grille[i + 1][j + 1] == grille[i + 2][j + 2]
                    && grille[i + 2][j + 2] == grille[i + 3][j + 3]) {
                return grille[i][j];
            }
        }

        return 0;
    }

    /**
     * 
     * reconnait pattern gagnant et renvoie id du vainqueur
     * 
     * @return playerID
     */
    public int hasWon() {
        /*
         * for i height -4
         * for j width -4
         * if i> w-3 chechcolonne
         * if j>h-3 chechligne
         * else checkligne checkcolone check diag
         * 
         * 
         * 
         */

        return 0;
    }
}
