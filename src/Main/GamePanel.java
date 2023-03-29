package Main;

import java.awt.*;

import javax.swing.JPanel;

import Input.*;
import Objects.*;

public class GamePanel extends JPanel{
    public final static int GAME_WIDTH = 1280, GAME_HEIGHT = 800;
    private MouseInput mouseInput;
    private GameEngine gameEngine;

    public GamePanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        mouseInput = new MouseInput(this);

        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    public PlayerPlane getPlayerPlane() {
        return gameEngine.getPlayerPlane();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }


    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        gameEngine.getBackground().render(g);
        gameEngine.getPlayerPlane().render(g);
        gameEngine.getCollisionManager().render(g);
        gameEngine.getEnemyPlane().render(g);
    }
}