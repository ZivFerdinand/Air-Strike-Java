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
    private BufferedImage[] imgHitting = new BufferedImage[4];
    private BufferedImage imgHittingFull;
    private BufferedImage currAnimation;
    private boolean isHitting = false;

    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }

    public EnemyUFO(float posX, float posY, GameEngine gameEngine) {
        super(posX, posY, 0, 0, 200, 200, Path.ENEMY_UFO);
        this.gameEngine = gameEngine;
        totalMvmt = 0;
        importImgShadow();
    }

    public void update() {
        updateHitBox();
        updateAnimation();
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
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_UFO_LASER_POINT, Constants.DamageDealer.UFO_REDUCE, false);
            posY = GamePanel.GAME_HEIGHT + 1000;

            GameEngine.audioPlayer.playDestroySound(0);
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_UFO_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT, Constants.DamageDealer.UFO_REDUCE, true);
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

        is = getClass().getResourceAsStream(Path.ENEMY_UFO_HIT);
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

        for (int i = 0; i < imgHitting.length; i++) {
            imgHitting[i] = imgHittingFull.getSubimage(i * 600, 0, 600, 600);
        }
    }

    int counterPassed = 0;
    int animIndex = 0;

    private void updateAnimation() {
        if (isHitting) {
            currAnimation = imgHitting[animIndex];
            if(counterPassed++ % 10 == 0){
                animIndex++; counterPassed=0;}
            if (animIndex >= imgHitting.length) {
                animIndex = 0;
                isHitting = false;
            }
        }
        else {
            currAnimation = this.img;
        }
    }


    public void render(Graphics g) {
        g.drawImage(imgShadow, (int) posX - 50, (int) posY + 125, 150, 150, null);
        g.drawImage(currAnimation, (int) posX, (int) posY, 200, 200, null);
    }

}
