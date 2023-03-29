package Objects;

import Main.GameEngine;
import Main.GamePanel;
import Utils.*;

import java.awt.*;

public class EnemyUFO extends Object {

    private int totalMvmt;
    private final int healthMax = 10;
    private int health = healthMax;
    private final int initPosY = -200;
    private int enemySpeed = 3;
    private GameEngine gameEngine;

    public EnemyUFO(float posX, float posY, GameEngine gameEngine) {
        super(posX, posY, 0, 0, 200, 200, "/res/Enemy-UFO.png");
        this.gameEngine = gameEngine;
        totalMvmt = 0;
    }

    public void update() {
        updateHitBox();
        posY += enemySpeed;
        totalMvmt += enemySpeed;

        if (totalMvmt % 30 == 0) {

            img = Assist.rotate(img, -90);
        }
        if (totalMvmt >= GamePanel.GAME_HEIGHT + 300) {
            resetPosition();
        }
    }

    private void resetPosition() {
        health = healthMax;
        posY = initPosY;
        totalMvmt = 0;
        enemySpeed = Assist.getRandomNumber(2, 3);

        posX = Assist.getRandomNumber(100, GamePanel.GAME_WIDTH - 100);
    }

    public void destroyObjectFromScreen() {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionUFO().startAnimation(posX, posY);
            posY = GamePanel.GAME_HEIGHT + 1000;
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) posX, (int) posY, 200, 200, null);
    }

}