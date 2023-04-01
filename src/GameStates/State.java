package GameStates;

import java.awt.event.MouseEvent;

import Main.GameEngine;
import UI.MenuButton;

public class State {
    protected GameEngine gameEngine;

    public State(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
    
	public GameEngine getGameEngine() {
		return gameEngine;
	}
}
