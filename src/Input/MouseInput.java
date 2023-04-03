package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import Main.*;

public class MouseInput implements MouseListener, MouseMotionListener {

    private final GamePanel gamePanel;

    public MouseInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case OPTIONS -> gamePanel.getGameEngine().getOption().mouseMoved(e);
            case MENU -> gamePanel.getGameEngine().getMenu().mouseMoved(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().mouseMoved(e);
            default -> {
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case SPLASH_SCREEN -> gamePanel.getGameEngine().getSplashScreen().mouseClicked(e);
            case OPTIONS -> gamePanel.getGameEngine().getOption().mouseClicked(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().mouseClicked(e);
            default -> {
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case OPTIONS -> gamePanel.getGameEngine().getOption().mousePressed(e);
            case MENU -> gamePanel.getGameEngine().getMenu().mousePressed(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().mousePressed(e);
            default -> {
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case OPTIONS -> gamePanel.getGameEngine().getOption().mouseReleased(e);
            case MENU -> gamePanel.getGameEngine().getMenu().mouseReleased(e);
            case PLAYING -> gamePanel.getGameEngine().getPlaying().mouseReleased(e);
            default -> {
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
