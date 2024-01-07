package puissance4.view.state;

import puissance4.model.State;
import util.observer.ModelListener;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class StateView extends AStateView{

    public static final int TILE_SIZE=40;
    private int focusedRow=1;

    public StateView(State state) {
        super(state);
        setFocusable(true);
        this.setPreferredSize(new Dimension(this.state.getGame().getWidth()*TILE_SIZE, this.state.getGame().getHeight()*TILE_SIZE));
    }

    @Override
    public void somethingHasChanged(Object source){
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        int [][] grille = this.state.getGrille();
        int gHeight= this.state.getGame().getHeight();
        int gWidth= this.state.getGame().getWidth();
        g.setColor(new Color(0, 0, 0));
        for(int i=0; i<gWidth; i++){
            for(int j = 0 ; j<gHeight; j++){
                int tilePosI=i*TILE_SIZE;
                int tilePosJ=(gHeight-1-j)*TILE_SIZE;
                g.setColor(new Color(0, 0, 0));
                g.drawRect(tilePosI, tilePosJ, TILE_SIZE, TILE_SIZE);
                switch (grille[i][j]) {
                    case 1:
                        g.setColor(Color.RED);
                        g.fillRect(tilePosI+1, tilePosJ+1, TILE_SIZE-1, TILE_SIZE-1);
                        break;
                    case 2:
                        g.setColor(Color.YELLOW);
                        g.fillRect(tilePosI+1, tilePosJ+1, TILE_SIZE-1, TILE_SIZE-1);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        g.fillRect(tilePosI+1, tilePosJ+1, TILE_SIZE-1, TILE_SIZE-1);
                        break;
                }
                if(i==this.focusedRow){
                    g.setColor(new Color(0,0,0,70));
                    g.fillRect(tilePosI+1, tilePosJ+1, TILE_SIZE-1, TILE_SIZE-1);
                }
            }
        }

    }

    public void setFocusedRow(int focusedRow){
        this.focusedRow=focusedRow;
        this.repaint();
    }

    
    
}
