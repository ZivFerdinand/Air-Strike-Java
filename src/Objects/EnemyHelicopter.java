package Objects;

import Main.GameEngine;
import Main.GamePanel;
import Utils.*;
import Utils.Constants.Path;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class EnemyHelicopter extends Object {
    private int totalMvmt;
    private final int healthMax = 10;
    private int health = healthMax;
    private final int initPosY = -100;
    private int enemySpeed = 3;
    private BufferedImage[] animation = new BufferedImage[4];
    private BufferedImage[] imgHitting = new BufferedImage[4];
    private BufferedImage imgHittingFull;
    private boolean isHitting = false;
    
    private int counterPassed = 0;
    private int animIndex = 0;
    private GameEngine gameEngine;
    
    public EnemyHelicopter(float posX, float posY, GameEngine gameEngine) {
        super(posX, posY, 40, 1, 50, 129, Path.ENEMY_HELICOPTER);
        this.gameEngine = gameEngine;
        totalMvmt = 0;
        importImgAnimation();
    }
    
    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }

    private void importImgAnimation() {
        for (int i = 0; i < animation.length; i++) {
            animation[i] = img.getSubimage(i * 96, 0, 96, 128);
        }

        InputStream is = getClass().getResourceAsStream(Path.ENEMY_HELICOPTER_HIT);
        try {
            imgHittingFull = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < animation.length; i++) {
            imgHitting[i] = imgHittingFull.getSubimage(i * 96, 0, 96, 128);
        }
    }

    public void update() {
        updateAnimation();
        updateHitBox();
        posY += enemySpeed;
        totalMvmt += enemySpeed;

        if (totalMvmt >= GamePanel.GAME_HEIGHT + 100) {
            resetPosition();
        }
    }

    private void updateAnimation() {
        if (isHitting) {
            if (counterPassed % 10 == 0) {
                animIndex++;
                if (animIndex >= animation.length)
                {
                    animIndex = 0;
                    isHitting = false;
                }

                img = imgHitting[animIndex];
                counterPassed = 0;
            }
            counterPassed++;
        }
        else {
            if (counterPassed % 15 == 0) {
                animIndex++;
                if (animIndex >= animation.length)
                    animIndex = 0;
    
                img = animation[animIndex];
                counterPassed = 0;
            }
            counterPassed++;
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
            gameEngine.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT);
            posY = GamePanel.GAME_HEIGHT + 1000;
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT);
            posY = GamePanel.GAME_HEIGHT + 1000;
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_HIT_POINT);
            playerPlane.reduceHealth(5);
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) posX, (int) posY, 130, 160, null);
    }
}
