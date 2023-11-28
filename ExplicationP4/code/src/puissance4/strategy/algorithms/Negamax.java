package puissance4.strategy.algorithms;

import java.util.ArrayList;

import puissance4.model.State;

public class Negamax {

    public int[] getAllScore(State state){
        ArrayList<Integer> validPlays= state.getValidPlay();
        int[] res= new int[state.getGame().getWidth()];
        for(int i=0; i<state.getGame().getWidth();i++){
            if(validPlays.contains(i)){
                res[i]= this.getScore(state, i);
            }
            else res[i]=0;
        }

        return res;
    }

    public int getScore(State state, int move){
        State nextState= state.play(move, false);
        return this.negamax(nextState);
    }

    public int negamax(State state){
        int gWidth= state.getGame().getWidth();
        int gHeight= state.getGame().getHeight();
        //Check for a draw
        if(state.isDone()){
            return 0;
        }

        for(int move:state.getValidPlay()){
            if(state.isWinningMove(move)){
                return gWidth*gHeight+1 -state.nbMoves()/2;
            }
        }

        int bestScore = -(gWidth*gHeight);

        for(int move: state.getValidPlay()){
            State nextState= state.play(move, false);
            int score= -(negamax(nextState));
            if(score>bestScore) bestScore=score;
        }

        return bestScore;
    }

}
