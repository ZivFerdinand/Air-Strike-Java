package Objects;

import Background.BackgroundManager;
import GameStates.Playing;
import Interfaces.IGameStandard;
import Utils.FontGenerator;
import Utils.AudioPlayer;
import Utils.ObjectSize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static Utils.Constants.Path.*;

public class Explosion extends Object implements IGameStandard {
    private final AudioPlayer audioPlayer;
    private final FontGenerator fontGenerator;
    private BufferedImage currAnimation;
    private BufferedImage[] animations;

    private int animIndex = 0;
    private int score;
    private int healthReduce;
    private final int expWidth, expHeight;
    private int fontSize = 50;
    private boolean healthShow;
    private boolean isAnimating = false;
    private boolean isPlayerDeath = false;

    public Explosion(ObjectSize objectSize, ObjectSize imageSize) {
        super(0, 0, 0, 0, 0, 0, EXPLOSION, imageSize, BackgroundManager.backgroundMovementSpeed);
        this.score = 0;
        this.audioPlayer = new AudioPlayer();
        this.fontGenerator = new FontGenerator();
        this.expWidth = objectSize.w;
        this.expHeight = objectSize.h;
        loadAnimations();
    }

    public void reset() {
        isAnimating = false;
    }

    private void loadAnimations() {
        animations = new BufferedImage[36];
        int count = 0;
        for (int j = 0; j < 6; j++)
            for (int i = 0; i < 6; i++) {
                animations[count++] = img.getSubimage(i * 100, j * 96, 100, 96);
            }
    }

    private void updateAnimation() {

        if (isAnimating) {
            if (counterPassed % 15 == 0) {
                animIndex++;
                fontSize--;
                if (animIndex >= animations.length) {

                    animIndex = 0;
                    isAnimating = false;
                    if (isPlayerDeath) {
                        isPlayerDeath = false;
                        Playing.setGameOver(true);
                    }
                }

                currAnimation = animations[animIndex];
                counterPassed = 0;
            }
            counterPassed++;
        }
    }

    public void update() {
        if (!isPlayerDeath)
            posY += speed;
        updateAnimation();
    }

    public void render(Graphics g) {
        if (isAnimating) {

            g.drawImage(currAnimation, (int) posX, (int) posY, expWidth, expHeight, null);

            if (!isPlayerDeath)
                fontGenerator.drawScoreGained(g, score, fontSize, (int) posX, (int) posY);

            if (healthShow)
                fontGenerator.drawPlayerDamaged(g, healthReduce, fontSize, (int) posX + 100, (int) posY + 96);
        }
    }

    public void startAnimation(float posX, float posY, int score, int healthReduce, boolean healthShow) {
        audioPlayer.playDestroySound();
        isAnimating = true;
        fontSize = 50;
        animIndex = 0;
        this.score = score;
        this.healthReduce = healthReduce;
        this.healthShow = healthShow;
        this.posX = posX;
        this.posY = posY;
    }

    public void startAnimation(float posX, float posY) {
        audioPlayer.playDestroySound();
        isAnimating = true;
        fontSize = 0;
        animIndex = 0;
        isPlayerDeath = true;
        this.posX = posX;
        this.posY = posY;
        this.healthShow = false;
    }

}
