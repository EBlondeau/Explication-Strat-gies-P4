package puissance4.view.state;

import javax.swing.JPanel;

import puissance4.model.Game;
import puissance4.model.State;
import util.observer.ModelListener;

public abstract class AStateView extends JPanel implements ModelListener{

    protected State state;

    public AStateView(State state){
        this.state=state;
        state.addModelListener(this);
    }

    @Override
    public void somethingHasChanged(Object source) {
        this.repaint();
    }

    public abstract void setFocusedRow(int focusedRow);
    
    public State getState(){
        return this.state;
    }

    public void setState(State state){
        this.state=state;
    }

    
}
