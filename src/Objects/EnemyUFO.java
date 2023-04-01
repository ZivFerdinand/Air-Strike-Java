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
import java.util.ArrayList;


public class EnemyUFO extends Object implements Enemy{

    private int totalMvmt;
    private final int healthMax = 20;
    private int health = healthMax;
    private final int initPosY = -250;
    private int enemySpeed = 1;
    private GameEngine gameEngine;
    private BufferedImage imgShadow;
    private BufferedImage[] imgHitting = new BufferedImage[4];
    private BufferedImage imgHittingFull;
    private BufferedImage currAnimation;
    private boolean isHitting = false;
    private ArrayList<LaserEnemy> laserShoot = new ArrayList<LaserEnemy>();

    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }

    public EnemyUFO(float posX, float posY, GameEngine gameEngine) {
        super(posX, posY, 0, 0, 200, 200, Path.ENEMY_UFO);
        this.gameEngine = gameEngine;
        totalMvmt = 0;
        importImgShadow();
        laserInstantiate(5);
    }

    public void update() {
        updateHitBox();
        laserUpdateHitBox();
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
    private void laserUpdateHitBox() {
        for (int i = 0; i < laserShoot.size(); i++) {
            laserShoot.get(i).updateHitBox();
        }
    }

    private void laserInstantiate(int count) {

        laserShoot.add(new LaserEnemy((int) posX, (int) posY, 2, 4));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, -2, -2));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, 2, -2));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, -2, 4));
    }

    public ArrayList<LaserEnemy> getLaserShoot() {
        return laserShoot;
    }
    private void laserUpdate(Graphics g)
    {
//        counterPassed++;
//        counterAudio++;
//        if(healthBackPos == 20)
//        {
//            healthBackPos=0;
//            healthPosX=50;
//        }
//        if (counterAudio == 20)
//        {
//            GameEngine.score.setScore(1);
//            counterAudio = 0;
//            GameEngine.audioPlayer.playAttackSound(25);
//
//
//        }
//        if (counterPassed >= 80)
//            counterPassed = 80;
        for (int i = 0; i < laserShoot.size(); i++) {
            if (!laserShoot.get(i).checkHasMoved()) {
                laserShoot.get(i).resetPos((int) posX, (int) posY);
            }
            if (laserShoot.get(i).getTotalMvmt() >= 500) {
                laserShoot.get(i).setTotalMvmt(0);
                laserShoot.get(i).resetPos((int) posX, (int) posY);
            }
            laserShoot.get(i).updateHitBox();
            laserShoot.get(i).render(g);
        }
    }

    private void resetPosition() {
        health = healthMax;
        posY = initPosY;
        totalMvmt = 0;

        posX = Assist.getRandomNumber(300, GamePanel.GAME_WIDTH - 300);
    }

    public void destroyObjectFromScreen() {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_UFO_LASER_POINT, Constants.DamageDealer.UFO_REDUCE, false);
            posY = GamePanel.GAME_HEIGHT + 1000;

            GameEngine.audioPlayer.playDestroySound();
            GameEngine.score.setScore(Constants.DamageDealer.ENEMY_UFO_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            health = healthMax;
            gameEngine.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT, Constants.DamageDealer.UFO_REDUCE, true);
            posY = GamePanel.GAME_HEIGHT + 1000;

            GameEngine.audioPlayer.playDestroySound();
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
        laserUpdate(g);
        g.drawImage(imgShadow, (int) posX - 50, (int) posY + 125, 150, 150, null);
        g.drawImage(currAnimation, (int) posX, (int) posY, 200, 200, null);
    }

}
