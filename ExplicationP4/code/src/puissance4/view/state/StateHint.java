package puissance4.view.state;

import javax.swing.JPanel;

import java.awt.*;

import puissance4.model.State;
import puissance4.model.strategy.algorithms.AbstractAlgo;
import puissance4.model.strategy.algorithms.Alphabeta;
import puissance4.model.strategy.algorithms.AlphabetaTT;
import util.observer.ModelListener;

public class StateHint extends JPanel implements ModelListener{

    private State state;
    private AbstractAlgo algo;


    public StateHint(State state, AbstractAlgo algo){
        this.state=state;
        this.algo=algo;
        this.setPreferredSize(new Dimension(this.state.getGame().getWidth()*StateView.TILE_SIZE, StateView.TILE_SIZE));
    }

    public StateHint(State state){
        this(state, new Alphabeta());
    }


    @Override
    public void somethingHasChanged(Object source) {
        repaint();
        System.out.println("repainting");
    }

    @Override
    public void paintComponent(Graphics g){
        int[] hints = this.algo.getAllScore(state.getGame().getCurrentState());
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        
        int gWidth= this.state.getGame().getWidth();
        for(int i=0; i<gWidth; i++){
            g.drawString(Integer.toString(hints[i]), i*StateView.TILE_SIZE, 10);
        }
        
    }
    
}
