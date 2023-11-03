import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        grille g = new grille(6, 7);
        g.showGrille();
        g.showState();
        System.out.println(Arrays.toString(g.getValidPlay()));
        g.play(1, 0);
        g.play(1, 0);
        g.play(1, 0);
        g.play(1, 0);
        g.play(1, 0);
        g.play(1, 0);

        g.showState();
        System.out.println(Arrays.toString(g.getValidPlay()));

    }
}