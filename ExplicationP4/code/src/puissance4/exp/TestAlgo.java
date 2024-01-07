package puissance4.exp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import puissance4.model.Game;
import puissance4.model.State;
import puissance4.model.strategy.algorithms.AbstractAlgo;

public class TestAlgo {

    public static boolean launchTest(AbstractAlgo algo, String setName){

        File setFolder = new File("../data/testsets/" + setName);

        File setResults = new File("../data/exp/" + setName + ".res");

        FileWriter setWriter=null;
        try {
            if(setResults.createNewFile()){
                System.out.println("Fichier exp créé : " + setName);
            }
            else{
                System.err.println("Le fichier existe déjà");
            }

            setWriter= new FileWriter(setResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        File[] sets = setFolder.listFiles();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(File set: sets){
            System.out.println(set.getName());
            System.out.println(set.getName().substring(set.getName().length()-3));
            if(set.getName().substring(set.getName().length()-3).equals("txt")){
                Future future = executor.submit(new TestRunnable(algo, set, setWriter));
                try {
                    future.get(10,TimeUnit.MINUTES);
                } catch (Exception e) {
                    System.out.println("Interrupted");
                }      
            }
        }

        try {
            setWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return true;
    }
}
