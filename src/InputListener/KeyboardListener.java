package InputListener;

import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardListener(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W -> gamePanel.addPosition(0, -1);
            case KeyEvent.VK_A -> gamePanel.addPosition(-1, 0);
            case KeyEvent.VK_S -> gamePanel.addPosition(0, 1);
            case KeyEvent.VK_D -> gamePanel.addPosition(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
