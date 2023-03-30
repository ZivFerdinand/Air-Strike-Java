package Objects;

import Main.GameEngine;
import Main.GamePanel;
import Utils.*;
import Utils.Constants.Path;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class EnemyUFO extends Object {

    private int totalMvmt;
    private final int healthMax = 20;
    private int health = healthMax;
    private final int initPosY = -250;
    private int enemySpeed = 3;
    private GameEngine gameEngine;

    private BufferedImage imgShadow;

    public EnemyUFO(float posX, float posY, GameEngine gameEngine) {
        super(posX, posY, 0, 0, 200, 200, Path.ENEMY_UFO);
        this.gameEngine = gameEngine;
        totalMvmt = 0;
        importImgShadow();
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
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_UFO_LASER_POINT);
            posY = GamePanel.GAME_HEIGHT + 1000;

            GameEngine.audioPlayer.playDestroySound(0);
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_UFO_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT);
            posY = GamePanel.GAME_HEIGHT + 1000;

            GameEngine.audioPlayer.playDestroySound(0);
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_HIT_POINT);

            playerPlane.reduceHealth(7);
        }
    }

    private void importImgShadow() {
        InputStream is = getClass().getResourceAsStream(Path.ENEMY_UFO_SHADOW);
        try {
            imgShadow = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public void render(Graphics g) {
        g.drawImage(imgShadow, (int) posX - 50, (int) posY + 125, 150, 150, null);
        g.drawImage(img, (int) posX, (int) posY, 200, 200, null);
    }

}
