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

import GameStates.Playing;
import Interfaces.IEnemy;
import Interfaces.IGameStandard;

public class EnemyHelicopter extends Object implements IEnemy, IGameStandard {

    private BufferedImage imgHittingSprite;
    private BufferedImage[] animation = new BufferedImage[4];
    private BufferedImage[] imgHitting = new BufferedImage[4];

    private boolean isHitting = false;
    
    private int counterPassed = 0;
    private int animIndex = 0;
    private int health;
    private int enemySpeed = 3;
    private int totalMvmt;
    private Playing playing;
    
    public EnemyHelicopter(Playing playing) {
        super(50, Constants.InitialPosition.HELICOPTER_INITIAL_POS_Y, 40, 1, 50, 129, Path.ENEMY_HELICOPTER, Constants.ObjectSizeData.ENEMY_HELICOPTER);
        this.playing = playing;

        totalMvmt = 0;
        healthReset();
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
            imgHittingSprite = ImageIO.read(is);
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
            imgHitting[i] = imgHittingSprite.getSubimage(i * 96, 0, 96, 128);
        }
    }

    public void update() {
        updateAnimation();
        updateHitBox();
        updatePosition();

    }
    private void updatePosition()
    {

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
        healthReset();
        posY = Constants.InitialPosition.HELICOPTER_INITIAL_POS_Y;
        totalMvmt = 0;
        enemySpeed = Assist.getRandomNumber(2, 3);

        posX = Assist.getRandomNumber(100, GamePanel.GAME_WIDTH - 100);
    }

    public void destroyObjectFromScreen() {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT, Constants.DamageDealer.HELICOPTER_REDUCE, false);
            posY = GamePanel.GAME_HEIGHT + 1000;
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT, Constants.DamageDealer.HELICOPTER_REDUCE, true);
            posY = GamePanel.GAME_HEIGHT + 1000;
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_HIT_POINT);
            playerPlane.reduceHealth(5);
        }
    }
    public void healthReset()
    {
        health = Constants.Health.HELICOPTER_HEALTH;
    }
    public void render(Graphics g) {
        g.drawImage(img, (int) posX, (int) posY, imageWidth, imageHeight, null);
    }
}
