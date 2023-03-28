package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.*;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getPlayerPlane().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getPlayerPlane().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getPlayerPlane().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getPlayerPlane().setRight(false);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getPlayerPlane().setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getPlayerPlane().setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getPlayerPlane().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getPlayerPlane().setRight(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getPlayerPlane().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getPlayerPlane().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getPlayerPlane().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getPlayerPlane().setRight(true);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getPlayerPlane().setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getPlayerPlane().setLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getPlayerPlane().setDown(true);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getPlayerPlane().setRight(true);
                break;
        }
    }
}
