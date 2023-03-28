package Planes;

import Main.GamePanel;
import Objects.Laser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PlayerPlane extends Plane {
    private final int playerSpeed = 2;
    private BufferedImage img;
    private BufferedImage upAnim, downAnim, leftAnim, rightAnim, leftUpAnim, rightUpAnim, leftDownAnim, rightDownAnim, idleAnim;
    private BufferedImage currAnimation;


    private boolean isUp;
    private boolean isDown;
    private boolean isRight;


    private boolean isLeft;

    public PlayerPlane(float posX, float posY) {
        super(posX, posY);

        importImg();
        loadAnimations();
    }

    private void loadAnimations() {
        BufferedImage[][] animations = new BufferedImage[3][3];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(200 + i * 200, 200 + j * 200, 200, 200);

        upAnim = animations[0][1];
        downAnim = animations[2][1];
        leftAnim = animations[1][0];
        rightAnim = animations[1][2];
        leftUpAnim = animations[0][0];
        rightUpAnim = animations[0][2];
        leftDownAnim = animations[2][0];
        rightDownAnim = animations[2][2];
        currAnimation = idleAnim = animations[1][1];
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

        if (isRight && !isLeft && posX + playerSpeed + 150 <= GamePanel.GAME_WIDTH) {
            posX += playerSpeed;
            currAnimation = rightAnim;
        } else if (!isRight && isLeft && posX - playerSpeed >= 0) {
            posX -= playerSpeed;
            currAnimation = leftAnim;
        }

        if (isUp && !isDown && posY - playerSpeed >= 0) {
            posY -= playerSpeed;
            currAnimation = upAnim;
        } else if (!isUp && isDown && posY + 150 + playerSpeed <= GamePanel.GAME_HEIGHT) {
            posY += playerSpeed;
            currAnimation = downAnim;
        }

        if (isUp && isRight) {
            currAnimation = rightUpAnim;
        } else if (isUp && isLeft) {
            currAnimation = leftUpAnim;
        } else if (isDown && isRight) {
            currAnimation = rightDownAnim;
        } else if (isDown && isLeft) {
            currAnimation = leftDownAnim;
        }
    }


    public void updateGame() {
        setAnimation();
    }

    Laser x = new Laser((int) posX, (int) posY);

    public void render(Graphics g) {

        g.drawImage(currAnimation, (int) posX, (int) posY, 150, 150, null);
        g.drawImage(currAnimation, (int) posX - 100, (int) posY + 150, 75, 75, null);

        x.render(g);
        if(x.getPosY() < -100)
        {
            x.resetPos((int) posX, (int) posY);
        }
    }
}
