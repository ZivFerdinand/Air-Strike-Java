package Input;

import java.awt.event.*;

import GameStates.GameState;
import Main.*;

public class KeyboardInput implements KeyListener {

    private final GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case MENU -> gamePanel.getGameEngine().getMenu().keyReleased(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().keyReleased(e);
            default -> {
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case MENU -> gamePanel.getGameEngine().getMenu().keyPressed(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().keyPressed(e);
            case SPLASH_SCREEN -> gamePanel.getGameEngine().getSplashScreen().keyPressed(e);
            default -> {
            }
        }
    }
}
