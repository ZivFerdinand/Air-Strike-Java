package Objects;

import Main.GamePanel;
import Utils.*;
import GameStates.Playing;
import Interfaces.IGameStandard;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static Utils.Constants.Path.*;

public class PlayerPlane extends Object implements IGameStandard {
    private final AudioPlayer audioPlayer;
    private int healthMax = Constants.Health.PLAYER_HEALTH;
    private int healthBackPos = 0;
    int healthPosX = 50;
    private BufferedImage imgShadow;
    private BufferedImage healthStatus;
    private BufferedImage upAnim, downAnim, leftAnim, rightAnim, leftUpAnim, rightUpAnim, leftDownAnim, rightDownAnim, idleAnim;
    private BufferedImage upAnimShadow, downAnimShadow, leftAnimShadow, rightAnimShadow, leftUpAnimShadow, rightUpAnimShadow, leftDownAnimShadow, rightDownAnimShadow, idleAnimShadow;
    private BufferedImage currAnimation;
    private BufferedImage currAnimationShadow;


    private final ArrayList<LaserPlane> laserPlaneShoot = new ArrayList<>();

    private boolean isUp;
    private boolean isDown;
    private boolean isRight;
    private boolean isLeft;

    public PlayerPlane() {
        super((GamePanel.GAME_WIDTH - 150) / 2, 600, 7, 40, 135, 75, PLAYER_PLANE, Constants.ObjectSizeData.PLAYER_PLANE, 0);
        this.audioPlayer = new AudioPlayer();
        importImgShadow();
        laserInstantiate();
        loadAnimations();
    }

    public void reset()
    {
        healthMax = Constants.Health.PLAYER_HEALTH;
        posX = (GamePanel.GAME_WIDTH - 150) / 2;
        posY = 600;
        healthBackPos = 0;
        healthPosX = 50;
        counterPassed = counterAudio = 0;
        laserPlaneShoot.clear();
        laserInstantiate();
    }

    private void loadAnimations() {
        BufferedImage[][] animations = new BufferedImage[3][3];
        BufferedImage[][] animationsShadow = new BufferedImage[3][3];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(200 + i * 200, 200 + j * 200, 200, 200);
                animationsShadow[j][i] = imgShadow.getSubimage(200 + i * 200, 200 + j * 200, 200, 200);
            }

        upAnim = animations[0][1];
        upAnimShadow = animationsShadow[0][1];

        downAnim = animations[2][1];
        downAnimShadow = animationsShadow[2][1];

        leftAnim = animations[1][0];
        leftAnimShadow = animationsShadow[1][0];

        rightAnim = animations[1][2];
        rightAnimShadow = animationsShadow[1][2];

        leftUpAnim = animations[0][0];
        leftUpAnimShadow = animationsShadow[0][0];

        rightUpAnim = animations[0][2];
        rightUpAnimShadow = animationsShadow[0][2];

        leftDownAnim = animations[2][0];
        leftDownAnimShadow = animationsShadow[2][0];

        rightDownAnim = animations[2][2];
        rightDownAnimShadow = animationsShadow[2][2];

        currAnimation = idleAnim = animations[1][1];
        currAnimationShadow = idleAnimShadow = animationsShadow[1][1];
    }

    private void importImgShadow() {
        imgShadow = ImageLoader.GetSpriteAtlas(PLAYER_PLANE_SHADOW);
        healthStatus = ImageLoader.GetSpriteAtlas(HEALTH_ICON);
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void resetMovement() {
        isLeft = isRight = isUp = isDown = false;
    }

    private void setAnimation() {
        currAnimation = idleAnim;
        currAnimationShadow = idleAnimShadow;

        int playerSpeedX = 3;
        if (isRight && !isLeft && posX + playerSpeedX + 150 <= GamePanel.GAME_WIDTH) {
            posX += playerSpeedX;
            currAnimation = rightAnim;
            currAnimationShadow = rightAnimShadow;
        } else if (!isRight && isLeft && posX - playerSpeedX >= 0) {
            posX -= playerSpeedX;
            currAnimation = leftAnim;
            currAnimationShadow = leftAnimShadow;
        }

        int playerSpeedY = 2;
        if (isUp && !isDown && posY - playerSpeedY >= 0) {
            posY -= playerSpeedY;
            currAnimation = upAnim;
            currAnimationShadow = upAnimShadow;
        } else if (!isUp && isDown && posY + 150 + playerSpeedY <= GamePanel.GAME_HEIGHT) {
            posY += playerSpeedY;
            currAnimation = downAnim;
            currAnimationShadow = downAnimShadow;
        }

        if (isUp && isRight) {
            currAnimation = rightUpAnim;
            currAnimationShadow = rightUpAnimShadow;
        } else if (isUp && isLeft) {
            currAnimation = leftUpAnim;
            currAnimationShadow = leftUpAnimShadow;
        } else if (isDown && isRight) {
            currAnimation = rightDownAnim;
            currAnimationShadow = rightDownAnimShadow;
        } else if (isDown && isLeft) {
            currAnimation = leftDownAnim;
            currAnimationShadow = leftDownAnimShadow;
        }
    }

    public void update() {
        updateHitBox();
        laserUpdateHitBox();
        setAnimation();

        if(healthMax == 0)
        {
            posX = posY = -1000;
        }
    }

    private void laserUpdateHitBox() {
        for (LaserPlane laserPlane : laserPlaneShoot) {
            laserPlane.updateHitBox();
        }
    }

    private void laserInstantiate() {
        for (int i = 0; i < 23; i++) {
            laserPlaneShoot.add(new LaserPlane((int) posX, (int) posY));
        }
    }

    public ArrayList<LaserPlane> getLaserShoot() {
        return laserPlaneShoot;
    }

    int counterAudio = 0;
    public int getHealth()
    {
        return healthMax;
    }
    public void reduceHealth(int health)
    {
        healthPosX = 40;
        healthBackPos = 0;
        this.healthMax -= health;
        this.healthMax = Math.max(this.healthMax, 0);
    }
    private void laserUpdate(Graphics g)
    {
        healthBackPos++;
        counterPassed++;
        counterAudio++;
        if(healthBackPos == 20)
        {
            healthBackPos=0;
            healthPosX=50;
        }
        if (counterAudio == 20)
        {
            Playing.score.setScore(1);
            counterAudio = 0;

            if(!Playing.isPaused() && !Playing.isGameOver() && healthMax!=0)
                audioPlayer.playAttackSound();
        }
        if (counterPassed >= 460)
            counterPassed = 460;
        for (int i = 0; i < counterPassed / 20; i++) {
            if (!laserPlaneShoot.get(i).checkHasMoved()) {
                laserPlaneShoot.get(i).resetPos((int) posX, (int) posY);
            }
            if (laserPlaneShoot.get(i).getTotalMvmt() >= 800) {
                laserPlaneShoot.get(i).setTotalMvmt(0);
                laserPlaneShoot.get(i).resetPos((int) posX, (int) posY);
            }
            laserPlaneShoot.get(i).updateHitBox();
            laserPlaneShoot.get(i).render(g);
        }
    }

    public void render(Graphics g) {

        g.drawImage(currAnimation, (int) posX, (int) posY, imageWidth, imageHeight, null);
        g.drawImage(currAnimationShadow, (int) posX - 50, (int) posY + 150, 95, 95, null);
        laserUpdate(g);

        g.drawImage(healthStatus, healthPosX, 45, 80, 80, null);
    }
}
