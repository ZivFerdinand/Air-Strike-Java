package Objects;

import Main.GamePanel;
import Utils.*;
import Utils.Constants.Path;
import GameStates.Playing;
import Interfaces.IEnemy;
import Interfaces.IGameStandard;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static Utils.Constants.Path.*;

public class EnemyUFO extends Object implements IEnemy, IGameStandard {
    private final AudioPlayer audioPlayer;
    private final FontGenerator fontGenerator;

    private BufferedImage imgShadow;
    private BufferedImage[] imgHitting;
    private BufferedImage currAnimation;

    private boolean isHitting = false;

    private int totalMvmt;
    private int health;

    private final Playing playing;

    private final ArrayList<LaserEnemy> laserShoot = new ArrayList<>();

    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }

    public EnemyUFO(Playing playing) {
        super(1000, -200, 0, 0, 200, 200, Path.ENEMY_UFO, Constants.ObjectSizeData.ENEMY_UFO, 1);
        this.audioPlayer = new AudioPlayer();
        this.playing = playing;
        fontGenerator = new FontGenerator();
        healthReset();
        totalMvmt = 0;
        importImgShadow();
        laserInstantiate();
    }

    public void reset() {
        resetPosition();
    }

    public void update() {
        updateHitBox();
        laserUpdateHitBox();
        updateAnimation();
        posY += speed;
        totalMvmt += speed;

        if (totalMvmt % 30 == 0) {

            img = ImageLoader.rotate(img, -90);
        }
        if (totalMvmt >= GamePanel.GAME_HEIGHT + 600) {
            resetPosition();
        }
    }

    private void laserUpdateHitBox() {
        for (LaserEnemy laserEnemy : laserShoot) {
            laserEnemy.updateHitBox();
        }
    }

    private void laserInstantiate() {

        laserShoot.add(new LaserEnemy((int) posX, (int) posY, 2, 4));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, -2, -2));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, 2, -2));
        laserShoot.add(new LaserEnemy((int) posX, (int) posY, -2, 4));
    }

    public ArrayList<LaserEnemy> getLaserShoot() {
        return laserShoot;
    }

    private void laserUpdate(Graphics g) {
        for (LaserEnemy laserEnemy : laserShoot) {
            if (!laserEnemy.checkHasMoved()) {
                laserEnemy.resetPos((int) posX, (int) posY);
            }
            if (laserEnemy.getTotalMvmt() >= 500) {
                laserEnemy.setTotalMvmt(0);
                laserEnemy.resetPos((int) posX, (int) posY);
            }
            laserEnemy.updateHitBox();
            laserEnemy.update();
            laserEnemy.render(g);
        }
    }

    private void resetPosition() {
        healthReset();
        posY = Constants.InitialPosition.UFO_INITIAL_POS_Y;
        totalMvmt = 0;

        posX = Assist.getRandomNumber(300, GamePanel.GAME_WIDTH - 300);
    }

    public void destroyObjectFromScreen() {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_UFO_LASER_POINT,
                    Constants.DamageDealer.UFO_REDUCE, false);
            playing.getCoins().get(0).startAnimation(posX + Assist.getRandomNumber(0, 200),
                    posY + Assist.getRandomNumber(0, 192));
            playing.getCoins().get(1).startAnimation(posX + Assist.getRandomNumber(0, 200),
                    posY + Assist.getRandomNumber(0, 192));
            playing.getCoins().get(2).startAnimation(posX + Assist.getRandomNumber(0, 200),
                    posY + Assist.getRandomNumber(0, 192));
            posY = GamePanel.GAME_HEIGHT + 1000;

            audioPlayer.playDestroySound();
            Playing.score.setScore(Constants.DamageDealer.ENEMY_UFO_LASER_POINT);
        }
    }

    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionUFO().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT,
                    Constants.DamageDealer.UFO_REDUCE, true);
            posY = GamePanel.GAME_HEIGHT + 1000;

            audioPlayer.playDestroySound();
            Playing.score.setScore(Constants.DamageDealer.ENEMY_HIT_POINT);

            playerPlane.reduceHealth(7);
        }
    }

    private void importImgShadow() {
        imgHitting = new BufferedImage[4];
        imgShadow = ImageLoader.GetSpriteAtlas(ENEMY_UFO_SHADOW);
        BufferedImage imgHittingSprite = ImageLoader.GetSpriteAtlas(ENEMY_UFO_HIT);
        for (int i = 0; i < imgHitting.length; i++) {
            imgHitting[i] = imgHittingSprite.getSubimage(i * 600, 0, 600, 600);
        }
    }

    int counterPassed = 0;
    int animIndex = 0;

    private void updateAnimation() {
        if (isHitting) {
            currAnimation = imgHitting[animIndex];
            if (counterPassed++ % 10 == 0) {
                animIndex++;
                counterPassed = 0;
            }
            if (animIndex >= imgHitting.length) {
                animIndex = 0;
                isHitting = false;
            }
        } else {
            currAnimation = this.img;
        }
    }

    public void render(Graphics g) {
        laserUpdate(g);
        g.drawImage(imgShadow, (int) posX - 50, (int) posY + 125, 150, 150, null);
        g.drawImage(currAnimation, (int) posX, (int) posY, imageWidth, imageHeight, null);

        if (posY >= Constants.InitialPosition.UFO_INITIAL_POS_Y && posY <= -imageHeight) {
            fontGenerator.drawExclamationMark(g, (int) posX + 20);
        }
    }

    public void healthReset() {
        health = Constants.Health.UFO_HEALTH;
    }
}
