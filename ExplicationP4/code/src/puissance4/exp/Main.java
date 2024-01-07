package puissance4.exp;

import java.io.File;

import puissance4.model.StateGenerator;
import puissance4.model.strategy.algorithms.Alphabeta;
import puissance4.model.strategy.algorithms.Negamax;

public class Main {
    public static void main(String[] args) {

        File f = new File("../data/testsets/");
        File[] sets = f.listFiles();
        
        for(File set: sets){
            System.out.println(TestAlgo.launchTest(new Negamax(), set.getName()));
        }
        
        System.exit(1);
    }
}
