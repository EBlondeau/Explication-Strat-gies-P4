package puissance4.exp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import puissance4.model.Game;
import puissance4.model.State;
import puissance4.strategy.algorithms.AbstractAlgo;

public class TestRunnable implements Runnable {

    private AbstractAlgo algo;
    private File set;

    public TestRunnable(AbstractAlgo algo, File set){
        this.algo=algo;
        this.set=set;
    }

    @Override
    public void run() {
        int[] results;

         try {
            double totalDelta=0;
            double delta;
            double lines=0;
            BufferedReader setReader = new BufferedReader(new FileReader(set));
            String line;
            while((line=setReader.readLine()) !=null){
                Game g = new Game(line);
                State s = g.getCurrentState();
                
                double t1= System.nanoTime();
                results = algo.getAllScore(s);
                double t2= System.nanoTime();

                
                delta = (t2 / 1000000)-(t1 / 1000000);

                totalDelta+=delta;
                lines++;
            }
            System.out.println(totalDelta/lines);
            setReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
    
}
