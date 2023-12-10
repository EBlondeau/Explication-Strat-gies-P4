package puissance4.model;

import java.io.File;

public class DemoSerializedData {
    public static void main(String[] args) {

        File f = new File("../data/gen_files/example.gen");
        StateGenerator.generateTestSet(f);
        //System.out.println(gen);
        //Game g2= new Game(gen);
        //g2.getCurrentState().printStateFull();
    }
}
