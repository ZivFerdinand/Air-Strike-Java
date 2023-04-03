package Background;

import Main.GamePanel;
import Utils.ImageLoader;
import GameStates.Playing;
import java.awt.*;
import java.awt.image.BufferedImage;
import static Utils.Constants.Path.*;

public class BackgroundManager {
    public static boolean isFirstMap = true;
    public static final int backgroundMovementSpeed = 1;

    private final int initFirstImagePosY = -GamePanel.GAME_WIDTH * 2 + GamePanel.GAME_HEIGHT;
    private final int initSecondImagePosY = -GamePanel.GAME_WIDTH * 2;

    private final int backgroundWidth = GamePanel.GAME_WIDTH;
    private final int backgroundHeight = backgroundWidth * 2;

    private BufferedImage backgroundImage;
    private BufferedImage map1Img, map2Img;

    private int firstImagePosY, secondImagePosY;

    private Playing playing;

    public BackgroundManager(Playing playing) {
        secondImagePosY = initSecondImagePosY;
        firstImagePosY = initFirstImagePosY;
        this.playing = playing;
        importImg();
    }

    private void importImg() {
        backgroundImage = ImageLoader.GetSpriteAtlas(BACKGROUND_GAME_2);
        map1Img = ImageLoader.GetSpriteAtlas(BACKGROUND_GAME_1);
    }

    public void reset() {
        secondImagePosY = initSecondImagePosY;
        firstImagePosY = initFirstImagePosY;
    }

    public void render(Graphics g) {
        if (checkStatusToUpdate()) {
            updateBackgroundPosition();
            validateResetImage();
        }
        g.drawImage(backgroundImage, 0, firstImagePosY, backgroundWidth, backgroundHeight, null);
        validateSubtractImage(g);
    }

    private boolean checkStatusToUpdate() {
        return (!Playing.isPaused() && !Playing.isGameOver() && playing.getPlayerPlane().getHealth() != 0);
    }

    private void updateBackgroundPosition() {
        backgroundImage = (isFirstMap) ? map1Img : map2Img;
        firstImagePosY += backgroundMovementSpeed;
    }

    private void validateSubtractImage(Graphics g) {
        if (firstImagePosY >= 0) {
            if (checkStatusToUpdate())
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
