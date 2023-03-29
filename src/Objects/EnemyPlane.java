package Objects;

import Main.GamePanel;
import Utils.MathAssist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class EnemyPlane extends Object{
    private int totalMvmt;
    private final int healthMax = 5;
    private int health = healthMax;
    private final int initPosY = -100;
    private int enemySpeed = 3;
    private BufferedImage img;
    public EnemyPlane(float posX, float posY) {
        super(posX, posY, 7, 40, 135, 75);
        totalMvmt = 0;
        importImg();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Plane-Yellow.png");
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
        img = img.getSubimage(400, 400, 200, 200);
    }

    public void update()
    {
        updateHitBox();
        posY += enemySpeed;
        totalMvmt += enemySpeed;

        if(totalMvmt >= GamePanel.GAME_HEIGHT + 100)
        {
            resetPosition();
        }
    }
    private void resetPosition()
    {
        health = healthMax;
        posY = initPosY;
        totalMvmt = 0;
        enemySpeed = MathAssist.getRandomNumber(2,4);

        posX = MathAssist.getRandomNumber(50, GamePanel.GAME_WIDTH - 50);
    }
    public void destroyObjectFromScreen()
    {
        health--;

        if(health < 0) {
            posY = GamePanel.GAME_HEIGHT + 1000;
            health=healthMax;
        }
    }
    public void render(Graphics g)
    {
        drawHitBox(g);
        g.drawImage(img, (int) posX, (int) posY, 150, 150, null);
    }
}
