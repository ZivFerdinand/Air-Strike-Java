package Objects;

import Background.BackgroundManager;
import Main.GameEngine;
import Main.GamePanel;
import Utils.AudioPlayer;
import Utils.Constants;
import Utils.FontGenerator;
import Utils.ObjectSize;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Star extends Object{
    private FontGenerator fontGenerator;
    private BufferedImage currAnimation;
    private BufferedImage[] animations = new BufferedImage[13];
    private int expWidth, expHeight;
    private int plusRenderX, plusRenderY;
    private int counterPassed = 0;
    private int animIndex= 0;
    private int fontSize = 50;
    private boolean isAnimating = false;

    public Star(ObjectSize imageSize) {
        super(0, 0, 0, 0, imageSize.w, imageSize.h, Constants.Path.STAR, imageSize);
        this.expWidth = imageSize.w;
        this.expHeight = imageSize.h;
        this.fontGenerator = new FontGenerator();
        loadAnimations();
    }

    private void loadAnimations() {
        for (int i = 0; i < 13; i++) {
            animations[i] = img.getSubimage(i * 95, 0, 95, 102);
        }
    }

    private void updateAnimation() {
        if(posY > 50 + GamePanel.GAME_HEIGHT)
        {
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
        posY += BackgroundManager.backgroundMovementSpeed;
        updateAnimation();
    }
    public void render(Graphics g) {

        if (isAnimating) {
            g.drawImage(currAnimation, (int) posX, (int) posY, expWidth, expHeight, null);
            if(fontSize > 10)
                fontGenerator.renderPlus(g, fontSize, plusRenderX, plusRenderY);
        }
    }
    public void resetPosition()
    {
        GameEngine.score.setScore(5);
        plusRenderX = (int)posX;
        plusRenderY = (int)posY;
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
