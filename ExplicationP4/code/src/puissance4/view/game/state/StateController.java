package puissance4.view.game.state;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class StateController implements MouseInputListener{

    protected AStateView stateView;
    protected int focusedRow=-1;

    public StateController(AStateView stateView){
        this.stateView=stateView;
        this.stateView.addMouseListener(this);
        this.stateView.addMouseMotionListener(this);
    }

    public void playMove(){
        boolean canPlay=false;
        
        for(int move:this.stateView.getState().getValidPlay()){
            if(move==this.focusedRow){
                canPlay=true;
                break;
            }
        }
        
        if(canPlay && this.stateView.getState().hasWon()==0){
            System.out.println(this.focusedRow);
            this.stateView.setState(this.stateView.getState().play(this.focusedRow));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.playMove();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int foc=e.getX()/StateView.TILE_SIZE;
        this.stateView.setFocusedRow(foc);
        this.focusedRow=foc;
        this.stateView.repaint();
    }
    
}
