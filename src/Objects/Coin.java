package Objects;

import Background.BackgroundManager;
import GameStates.Playing;
import Main.GamePanel;
import Utils.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static Utils.Constants.Path.*;

public class Coin extends Object {
    private final FontGenerator fontGenerator;
    private BufferedImage currAnimation;
    private BufferedImage[] animations;
    private final int expWidth, expHeight;
    private int plusRenderX, plusRenderY;
    private int animIndex = 0;
    private int fontSize = 50;
    private boolean isAnimating = false;

    public Coin(ObjectSize imageSize) {
        super(0, 0, 0, 0, imageSize.w, imageSize.h, COIN, imageSize, BackgroundManager.backgroundMovementSpeed);
        this.expWidth = imageSize.w;
        this.expHeight = imageSize.h;
        this.fontGenerator = new FontGenerator();
        loadAnimations();
    }

    private void loadAnimations() {
        animations = new BufferedImage[5];
        for (int i = 0; i < animations.length; i++) {
            animations[i] = img.getSubimage(i * 84, 0, 84, 84);
        }
    }

    private void updateAnimation() {
        if (posY > 50 + GamePanel.GAME_HEIGHT) {
            isAnimating = false;
        }
        if (isAnimating) {
            if (counterPassed % 15 == 0) {
                animIndex++;
                fontSize--;
                if (animIndex >= animations.length) {
                    animIndex = 0;
                }

                currAnimation = animations[animIndex];
                counterPassed = 0;
            }
            counterPassed++;
        }
    }

    public void update() {
        updateHitBox();
        posY += speed;
        updateAnimation();
    }

    public void render(Graphics g) {

        if (isAnimating) {
            g.drawImage(currAnimation, (int) posX, (int) posY, expWidth, expHeight, null);
            if (fontSize > 10)
                fontGenerator.drawPointsTaken(g, fontSize, plusRenderX, plusRenderY);
        }
    }

    public void resetPosition() {
        Playing.score.setScore(5);
        plusRenderX = (int) posX;
        plusRenderY = (int) posY;
        fontSize = 30;
        this.posX = this.posY = -100;
    }

    public void startAnimation(float posX, float posY) {
        isAnimating = true;
        fontSize = 0;
        animIndex = 0;
        this.posX = posX;
        this.posY = posY;
    }
}
