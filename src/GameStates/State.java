package GameStates;

import java.awt.*;
import java.awt.event.MouseEvent;

import UI.MenuButton;

public class State {

    public State() {
        
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public boolean isIn(MouseEvent e, Rectangle bound) {
        return bound.contains(e.getX(), e.getY());
    }
}
