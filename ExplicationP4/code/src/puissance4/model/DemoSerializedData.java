package puissance4.model;

import java.io.File;

public class DemoSerializedData {
    public static void main(String[] args) {

        File f = new File("../data/gen_files/");
        File[] gens = f.listFiles();
        
        for(File gen: gens){
            if(gen.getName().substring(gen.getName().length()-3).equals("gen")){
                StateGenerator.generateTestSet(gen);
            }
        }
         
        //System.out.println(gen);
        //Game g2= new Game(gen);
        //g2.getCurrentState().printStateFull();
    }
}
