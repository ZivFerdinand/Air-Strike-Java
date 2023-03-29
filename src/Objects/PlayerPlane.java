package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PlayerPlane extends Object {
    private int counterPassed = 0;
    private final int playerSpeed = 3;
    private BufferedImage img, imgShadow;
    private BufferedImage upAnim, downAnim, leftAnim, rightAnim, leftUpAnim, rightUpAnim, leftDownAnim, rightDownAnim, idleAnim;
    private BufferedImage upAnimShadow, downAnimShadow, leftAnimShadow, rightAnimShadow, leftUpAnimShadow, rightUpAnimShadow, leftDownAnimShadow, rightDownAnimShadow, idleAnimShadow;
    private BufferedImage currAnimation;
    private BufferedImage currAnimationShadow;


    private ArrayList<Laser> laserShoot = new ArrayList<Laser>();

    private boolean isUp;
    private boolean isDown;
    private boolean isRight;
    private boolean isLeft;

    public PlayerPlane(float posX, float posY) {
        super(posX, posY, 7, 40, 135, 75);
        laserInstantiate(23);
        importImg();
        loadAnimations();
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

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Plane-Blue.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream("/res/Plane-Shadow-40.png");
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

        if (isRight && !isLeft && posX + playerSpeed + 150 <= GamePanel.GAME_WIDTH) {
            posX += playerSpeed;
            currAnimation = rightAnim;
            currAnimationShadow = rightAnimShadow;
        } else if (!isRight && isLeft && posX - playerSpeed >= 0) {
            posX -= playerSpeed;
            currAnimation = leftAnim;
            currAnimationShadow = leftAnimShadow;
        }

        if (isUp && !isDown && posY - playerSpeed >= 0) {
            posY -= playerSpeed;
            currAnimation = upAnim;
            currAnimationShadow = upAnimShadow;
        } else if (!isUp && isDown && posY + 150 + playerSpeed <= GamePanel.GAME_HEIGHT) {
            posY += playerSpeed;
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


    public void updateGame() {
        updateHitBox();
        laserUpdateHitBox();

        setAnimation();
    }

    private void laserUpdateHitBox() {
        for (int i = 0; i < laserShoot.size(); i++) {
            laserShoot.get(i).updateHitBox();
        }
    }

    private void laserInstantiate(int count) {
        for (int i = 0; i < count; i++) {
            laserShoot.add(new Laser((int) posX, (int) posY));
        }
    }

    public ArrayList<Laser> getLaserShoot() {
        return laserShoot;
    }

    private void laserUpdate(Graphics g)
    {
        counterPassed++;
        if (counterPassed >= 460)
            counterPassed = 460;
        for (int i = 0; i < counterPassed / 20; i++) {
            if (!laserShoot.get(i).checkHasMoved()) {
                laserShoot.get(i).resetPos((int) posX, (int) posY);
            }
            if (laserShoot.get(i).getTotalMvmt() >= 800) {
                laserShoot.get(i).setTotalMvmt(0);
                laserShoot.get(i).resetPos((int) posX, (int) posY);
            }
            laserShoot.get(i).updateHitBox();
            laserShoot.get(i).render(g);
        }
    }
    public void render(Graphics g) {
        drawHitBox(g);

        g.drawImage(currAnimation, (int) posX, (int) posY, 150, 150, null);
        g.drawImage(currAnimationShadow, (int) posX - 50, (int) posY + 150, 95, 95, null);

        laserUpdate(g);
    }

}
