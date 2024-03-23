package puissance4.model.strategy;

import puissance4.model.Game;
import puissance4.model.Player;

public class StateBB {
    private long grille;
    private long mask;
    private Game game;
    private Player currentPlayer;
    public int[] colState;

    /**
     * Constructeur d'un état initial
     * 
     * @param game
     */
    public StateBB(Game game) {
        this.game = game;
        String zeros = "0".repeat(this.game.getWidth() * this.game.getHeight());
        this.grille = Integer.parseInt(zeros, 2);
        this.mask = Integer.parseInt(zeros, 2);

        this.currentPlayer = this.game.getp1();
        this.colState = new int[this.game.getWidth()];
    }

    public void play(int move) {
        this.grille ^= this.mask;
        this.mask |= this.mask + bottom_mask(move);

    }

    public int bottom_mask(int move) {
        return 1 << move * (this.game.getHeight() + 1);
    }

    public int top_mask(int move) {
        return (1 << this.game.getHeight() - 1) << move * (this.game.getHeight() + 1);
    }

    public int column_mask(int move) {
        return (1 << this.game.getHeight()) - 1 << move * (this.game.getHeight() + 1);
    }
}
