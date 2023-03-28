package Main;

import java.awt.*;

import javax.swing.JPanel;

import Input.*;
import Objects.*;

public class GamePanel extends JPanel{
    public static int GAME_WIDTH = 1280, GAME_HEIGHT = 800;
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

    int posX = 300;
    Rectangle r1 = new Rectangle(posX, 300, 150, 150);
    Color x = new Color(255, 0, 0);
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        gameEngine.getBackground().render(g);
        gameEngine.getPlayerPlane().render(g);
        r1 = new Rectangle(posX, 300, 150, 150);
        g.setColor(x);
        g.fillRect(posX, 300, 150, 150);
        if (r1.intersects(getPlayerPlane().getHitBox())) {
            System.out.println("Collision");
            posX = 100;
        }
        for (int i = 0; i < getPlayerPlane().getLaserShoot().size(); i++) {
            if (r1.intersects(getPlayerPlane().getLaserShoot().get(i).getHitBox())) {
                x = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
            }
        }
    }
}