package Main;

import java.awt.*;

import javax.swing.JPanel;

import Input.*;

public class GamePanel extends JPanel {
    public final static int GAME_WIDTH = 1280, GAME_HEIGHT = 800;
    private final GameEngine gameEngine;

    public GamePanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        MouseInput mouseInput = new MouseInput(this);

        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameEngine.render(g);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}