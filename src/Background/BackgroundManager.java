package Background;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundManager {
    private BufferedImage img;
    private int posY, secondImagePosY;

    private final int initYPos = -GamePanel.GAME_WIDTH * 2 + GamePanel.GAME_HEIGHT;
    private final int initSecondImagePosY = -GamePanel.GAME_WIDTH * 2;
    private final int backgroundMovementSpeed = 1;

    public BackgroundManager() {
        secondImagePosY = initSecondImagePosY;
        posY = initYPos;

        importImg();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Main-Background-2-Cloud.png");
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

    public void render(Graphics g) {
        posY += backgroundMovementSpeed;
        g.drawImage(img, 0, posY, GamePanel.GAME_WIDTH, GamePanel.GAME_WIDTH * 2, null);

        if (posY >= 0) {
            secondImagePosY += backgroundMovementSpeed;
            g.drawImage(img, 0, secondImagePosY, GamePanel.GAME_WIDTH, GamePanel.GAME_WIDTH * 2, null);
        }
        if (secondImagePosY >= initYPos) {
            posY = initYPos;
            secondImagePosY = initSecondImagePosY;
        }
    }
}
