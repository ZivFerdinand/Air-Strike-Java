package Objects;

import Background.BackgroundManager;
import Interfaces.IGameStandard;
import Utils.Constants;
import Utils.FontGenerator;
import Utils.Constants.Path;
import Utils.AudioPlayer;
import Utils.ObjectSize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Explosion extends Object implements IGameStandard {
    private AudioPlayer audioPlayer;
    private FontGenerator fontGenerator;
    private BufferedImage currAnimation;
    private BufferedImage[] animations = new BufferedImage[36];

    private int counterPassed = 0;
    private int animIndex = 0;
    private int score;
    private int healthReduce;
    private int expWidth, expHeight;
    private int fontSize = 50;
    private boolean healthShow;
    private boolean isAnimating = false;

    public Explosion(ObjectSize objectSize, ObjectSize imageSize) {
        super(0, 0, 0, 0, 0, 0, Path.EXPLOSION, imageSize);
        this.score = 0;
        this.audioPlayer = new AudioPlayer();
        this.fontGenerator = new FontGenerator();
        this.expWidth = objectSize.w;
        this.expHeight = objectSize.h;
        loadAnimations();
    }

    private void loadAnimations() {
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
                }

                currAnimation = animations[animIndex];
                counterPassed = 0;
            }
            counterPassed++;
        }
    }

    public void update() {
        posY += BackgroundManager.backgroundMovementSpeed;
        updateAnimation();
    }
    public void render(Graphics g) {
        if (isAnimating) {

            g.drawImage(currAnimation, (int) posX, (int) posY, expWidth, expHeight, null);
            fontGenerator.render(g, score, fontSize, (int) posX, (int) posY);

            if (healthShow)
                fontGenerator.renderMinus(g, healthReduce, fontSize, (int) posX + 100, (int) posY + 96);
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

}
