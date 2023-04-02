package Background;

import Main.GamePanel;
import Utils.Constants;

import javax.imageio.ImageIO;

import GameStates.GameState;
import GameStates.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundManager {
    public static boolean isFirstMap = true;
    private final int initFirstImagePosY = -GamePanel.GAME_WIDTH * 2 + GamePanel.GAME_HEIGHT;
    private final int initSecondImagePosY = -GamePanel.GAME_WIDTH * 2;
    public static final int backgroundMovementSpeed = 1;
    private final int backgroundWidth = GamePanel.GAME_WIDTH;
    private final int backgroundHeight = GamePanel.GAME_WIDTH * 2;

    private BufferedImage backgroundImage;
    private BufferedImage map1, map2;
    private int firstImagePosY, secondImagePosY;


    public BackgroundManager() {
        secondImagePosY = initSecondImagePosY;
        firstImagePosY = initFirstImagePosY;

        importImg();
    }

    public void render(Graphics g) {
        if(Playing.paused == false)
            updateBackgroundPosition();
        g.drawImage(backgroundImage, 0, firstImagePosY, backgroundWidth, backgroundHeight, null);
        validateSubtractImage(g);
        
        if(Playing.paused == false)
            validateResetImage();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream(Constants.Path.BACKGROUND_GAME_2);
        try {
            backgroundImage = map2 = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        is = getClass().getResourceAsStream(Constants.Path.BACKGROUND_GAME_1);
        try {
            map1 = ImageIO.read(is);
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

    private void updateBackgroundPosition() {
        backgroundImage = (isFirstMap) ? map1 : map2;

        firstImagePosY += backgroundMovementSpeed;
    }

    private void validateSubtractImage(Graphics g) {
        if (firstImagePosY >= 0) {
            
            if(Playing.paused == false)
                secondImagePosY += backgroundMovementSpeed;
            g.drawImage(backgroundImage, 0, secondImagePosY, backgroundWidth, backgroundHeight, null);
        }
    }

    private void validateResetImage() {
        if (secondImagePosY >= initFirstImagePosY) {
            firstImagePosY = initFirstImagePosY;
            secondImagePosY = initSecondImagePosY;
        }
    }
}
