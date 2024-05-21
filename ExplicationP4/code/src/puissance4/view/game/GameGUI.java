package puissance4.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.nimbus.State;

import puissance4.model.Game;
import puissance4.view.state.AStateView;
import puissance4.view.state.StateController;
import puissance4.view.state.StateHint;
import puissance4.view.state.StateView;
import util.observer.ModelListener;

public class GameGUI extends JFrame implements ModelListener, MouseInputListener{

    private Game game;
    private StateView stateView;
    private StateHint stateHint;

    public GameGUI(Game game){
        this.game=game;

        this.stateView= new StateView(this.game.getCurrentState());
        new StateController(stateView);

        this.stateHint= new StateHint(this.game.getCurrentState());

        setLayout(new BorderLayout());
        add(stateView, BorderLayout.NORTH);
        add(stateHint, BorderLayout.SOUTH);
        setResizable(true);
        this.game.addModelListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void somethingHasChanged(Object source) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}

    