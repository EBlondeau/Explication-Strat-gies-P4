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
        System.out.println(Arrays.deepToString(grille).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
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
            grille[height - state[columns]][columns] = playerID;
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

    /**
     * 
     * @param i
     * @param j
     * @return
     */
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

    /**
     * 
     * reconnait pattern gagnant et renvoie id du vainqueur
     * 
     * @return playerID
     */
    public int hasWon() {
        int res = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (grille[i][j] != 0) {
                    if (j < height - 3) {
                        res = checkLigne(i, j);
                        if (res != 0) {
                            return res;
                        }
                    }
                    if (i < width - 3) {
                        res = checkColonne(i, j);
                        if (res != 0) {
                            return res;
                        }

                    }
                    if (i < width - 3 && j < height - 3) {
                        res = checkDiagonal(i, j);
                        if (res != 0) {
                            return res;
                        }
                    }
                    if (i > 2 && j <= height - 3) {

                        res = checkDiagonalBw(i, j);
                        if (res != 0) {
                            return res;
                        }
                    }
                }
            }
        }

        return -1;
    }

    /**
     * copie une grille
     * 
     * @param g grille à copier
     * @return une copie de la grille
     */
    public Grille copyGrille(Grille g) {
        Grille res = new Grille(g.height, g.width);
        for (int i = 0; i < g.width; i++) {
            for (int j = 0; j < g.height; j++) {
                res.grille[i][j] = g.grille[i][j];
            }
        }

        return res;
    }
}
