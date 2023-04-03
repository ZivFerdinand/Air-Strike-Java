package Objects;

import Main.GamePanel;
import Utils.*;
import Utils.Constants.Path;

import java.awt.*;
import java.awt.image.BufferedImage;
import GameStates.Playing;
import Interfaces.*;
import static Utils.Constants.Path.*;

public class EnemyHelicopter extends Object implements IEnemy, IGameStandard {
    private final FontGenerator fontGenerator;
    private BufferedImage[] animation;
    private BufferedImage[] imgHitting;

    private boolean isHitting = false;
    
    private int animIndex = 0;
    private int health;
    private int totalMvmt;
    private final Playing playing;
    
    public EnemyHelicopter(Playing playing) {
        super(50, Constants.InitialPosition.HELICOPTER_INITIAL_POS_Y, 40, 1, 50, 129, Path.ENEMY_HELICOPTER,
                Constants.ObjectSizeData.ENEMY_HELICOPTER, 3);
        this.playing = playing;
        this.fontGenerator = new FontGenerator();
        totalMvmt = 0;
        healthReset();
        importImgAnimation();
    }
    
    public void reset()
    {
        resetPosition();
    }
    
    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }

    private void importImgAnimation() {
        animation = new BufferedImage[4];
        imgHitting = new BufferedImage[4];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = img.getSubimage(i * 96, 0, 96, 128);
        }
        BufferedImage imgHittingSprite = ImageLoader.GetSpriteAtlas(ENEMY_HELICOPTER_HIT);
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

        posY += speed;
        totalMvmt += speed;
        if (totalMvmt >= GamePanel.GAME_HEIGHT + 400) {
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
        }
        else {
            if (counterPassed % 15 == 0) {
                animIndex++;
                if (animIndex >= animation.length)
                    animIndex = 0;
    
                img = animation[animIndex];
                counterPassed = 0;
            }
        }
        counterPassed++;
    }

    private void resetPosition() {
        healthReset();
        posY = Constants.InitialPosition.HELICOPTER_INITIAL_POS_Y;
        totalMvmt = 0;
        speed = Assist.getRandomNumber(2, 3);

        posX = Assist.getRandomNumber(100, GamePanel.GAME_WIDTH - 100);
    }

    public void destroyObjectFromScreen() {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT, Constants.DamageDealer.HELICOPTER_REDUCE, false);
            playing.getCoins().get(3).startAnimation(posX + Assist.getRandomNumber(0, 100), posY + Assist.getRandomNumber(0, 96));
            playing.getCoins().get(4).startAnimation(posX+ Assist.getRandomNumber(0, 100), posY+Assist.getRandomNumber(0, 96));

            posY = GamePanel.GAME_HEIGHT + 1000;
            Playing.score.setScore(Constants.DamageDealer.ENEMY_HELICOPTER_LASER_POINT);
        }
    }
    public void destroyObjectFromScreen(PlayerPlane playerPlane) {
        health--;

        if (health < 0) {
            healthReset();
            playing.getExplosionHelicopter().startAnimation(posX, posY, Constants.DamageDealer.ENEMY_HIT_POINT, Constants.DamageDealer.HELICOPTER_REDUCE, true);
            posY = GamePanel.GAME_HEIGHT + 1000;
            Playing.score.setScore(Constants.DamageDealer.ENEMY_HIT_POINT);
            playerPlane.reduceHealth(5);
        }
    }
    public void healthReset()
    {
        health = Constants.Health.HELICOPTER_HEALTH;
    }
    public void render(Graphics g) {

        g.drawImage(img, (int) posX, (int) posY, imageWidth, imageHeight, null);
        if (posY >= Constants.InitialPosition.HELICOPTER_INITIAL_POS_Y && posY <= -20)
        {
            fontGenerator.drawExclamationMark(g, (int)posX);
        }
    }
}
