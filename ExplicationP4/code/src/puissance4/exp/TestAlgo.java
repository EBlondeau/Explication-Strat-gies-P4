package puissance4.exp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import puissance4.model.Game;
import puissance4.model.State;
import puissance4.strategy.algorithms.AbstractAlgo;

public class TestAlgo {

    public static boolean launchTest(AbstractAlgo algo, String setName){

        File setFolder = new File("../data/testsets/" + setName);
        File[] sets = setFolder.listFiles();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(File set: sets){
            System.out.println(set.getName());
            System.out.println(set.getName().substring(set.getName().length()-3));
            if(set.getName().substring(set.getName().length()-3).equals("txt")){
                Future future = executor.submit(new TestRunnable(algo, set));
                try {
                    future.get(1,TimeUnit.HOURS);
                } catch (Exception e) {
                    executor.shutdown();
                    System.out.println("Interrupted");
                }      
            }
        }

        executor.shutdown();
        return true;
    }
}
