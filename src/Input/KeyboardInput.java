package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameStates.GameState;
import Main.*;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameEngine().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameEngine().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }
}
