package puissance4.exp;

import puissance4.strategy.algorithms.Alphabeta;

public class Main {
    public static void main(String[] args) {
        System.out.println(TestAlgo.launchTest(new Alphabeta(), "7-6-3"));
        System.exit(1);
    }
}
