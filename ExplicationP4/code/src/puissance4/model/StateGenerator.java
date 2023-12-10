package puissance4.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class StateGenerator {

    public final static String testSetsPath="../data/testsets/";
    
    /**
     * Generate a valid board, after a number of moves between two values
     * @param nbMoveMin
     * @param nbMoveMax
     * @param height
     * @param width
     * @param winningLength
     * @return
     */
    public static String generate(int nbMoveMin, int nbMoveMax, int width, int height, int winningLength, int seed){
        Random r= new Random(seed);
        int nbMove= r.nextInt(nbMoveMax-nbMoveMin) + nbMoveMin;
        Game game= new Game(new Player(1, null), new Player(2, null), height, width);
        game.setWinningLength(winningLength);
        State state= game.getCurrentState();
        String moveList= "";

        //For each generated move, play a non-winning move if it exists, return an error if all moves
        //are winning moves for this state.
        for(int i=0; i<nbMove; i++){
            ArrayList<Integer> plays= state.getValidPlay();
            boolean isWinningMove=true;
            int move = -1;
            while(isWinningMove && !plays.isEmpty()){
                //System.out.println(state.getValidPlay());
                int moveIndex=r.nextInt(plays.size());
                move = plays.get(moveIndex);
                //System.out.println(move);
                isWinningMove= state.isWinningMove(move);
                plays.remove(moveIndex);
            }
            if(plays.isEmpty())
                return "error";
            State nextState= state.play(move);
            moveList+=Integer.toString(move);
            state=nextState;
        }

        //System.out.println(state.hasWon());

        return moveList;
    }


    public static void generateTestPart(String setPartName, String subPath, int nb, int nbMoveMin, int nbMoveMax, int width, int height, int winningLength, int seed){
        int generated=0;
        try {
            String fileName = ""+ setPartName + "_" + nb + "_" + nbMoveMin + "_" + nbMoveMax +
                             "_" + width + "_" + height +
                             "_" + winningLength + ".txt";
            File testSet= new File(testSetsPath + subPath + "/" + fileName);

            if(testSet.createNewFile()){
                System.out.println("File created: " + fileName);
            }
            else{
                System.out.println("Error: file already exists");
            }

            Random r= new Random(seed);
            FileWriter testSetWriter= new FileWriter(testSet);
            while(generated<nb){
                int currSeed= r.nextInt();
                String g=generate(nbMoveMin, nbMoveMax, width, height, winningLength, currSeed);
                if(!g.equals("error")){
                    //System.out.println(g);
                    testSetWriter.write(g + "\n");
                    generated+=1;
                }
            }
            testSetWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateTestSet(File f){

        try{

            Random initR= new Random();

            //Reader for the setting file
            BufferedReader setReader = new BufferedReader(new FileReader(f));
            String line=null;

            
            // Fetch setname
            line=setReader.readLine();

            // Set attributes
            String[] setAttributes= line.split("_");
            String setName=setAttributes[0];
            int setSeed=-1;
            if(setAttributes.length==2){
                setSeed=Integer.parseInt(setAttributes[1]);
            }
            else{
                setSeed=initR.nextInt();
            }

            Random r = new Random(setSeed);
            
            Path setPath= Paths.get(testSetsPath + setName + "/");

            if(Files.createDirectories(setPath) !=null){
                System.out.println("Folder created: " + setName);
            } else{
                System.out.println("Error: Folder already exists");
            }

            File settingFile= new File(testSetsPath + setName + "/" + "settings.gen");

            if(settingFile.createNewFile()){
                System.out.println("File created: settings");
            }
            else{
                System.out.println("Error");
            }

            FileWriter settingsWriter= new FileWriter(settingFile);
            settingsWriter.write(setName+"_"+setSeed+"\n");
            
            while((line=setReader.readLine()) != null){
                settingsWriter.write(line+"\n");
                int currSeed= r.nextInt();
                String[] testAttributes= line.split("_");
                //assert(testAttributes.length==4);
                String setPartName= testAttributes[0];
                int nb= Integer.parseInt(testAttributes[1]);
                int nbMoveMin= Integer.parseInt(testAttributes[2]);
                int nbMoveMax = Integer.parseInt(testAttributes[3]);
                int width = Integer.parseInt(testAttributes[4]);
                int height = Integer.parseInt(testAttributes[5]);
                int winningLength = Integer.parseInt(testAttributes[6]);
                generateTestPart(setPartName, setName, nb, nbMoveMin, nbMoveMax, width, height, winningLength, currSeed);
            }

            settingsWriter.close();
            setReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

}
