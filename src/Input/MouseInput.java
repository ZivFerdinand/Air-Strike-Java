package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import Main.*;

public class MouseInput implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameEngine().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().mouseMoved(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().mouseClicked(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameEngine().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().mousePressed(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameEngine().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGameEngine().getPlaying().mouseReleased(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
