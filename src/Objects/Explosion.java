package Objects;

import Utils.FontGenerator;
import Utils.Constants.Path;
import Utils.AudioPlayer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Explosion extends Object {
    private AudioPlayer audioPlayer = new AudioPlayer();
    private FontGenerator fontGenerator = new FontGenerator();
    private boolean isAnimating = false;
    private BufferedImage currAnimation;
    private BufferedImage[] animations = new BufferedImage[36];

    private int counterPassed = 0;
    private int animIndex = 0;

    private int score, healthReduce;
    private boolean healthShow;

    private int expWidth, expHeight;
    private int fontSize = 50;

    public Explosion(float posX, float posY, int expWidth, int expHeight) {
        super(posX, posY, 0, 0, 100, 96, Path.EXPLOSION);
        this.score = 0;
        this.expWidth = expWidth;
        this.expHeight = expHeight;
        loadAnimations();
    }

    private void loadAnimations() {

        int count = 0;
        for (int j = 0; j < 6; j++)
            for (int i = 0; i < 6; i++) {
                animations[count++] = img.getSubimage(i * 100, j * 96, 100, 96);
            }
    }

    public void render(Graphics g) {
        if (isAnimating) {

            g.drawImage(currAnimation, (int) posX, (int) posY, expWidth, expHeight, null);
            fontGenerator.render(g, score, fontSize, (int)posX, (int)posY);

            if(healthShow)
            fontGenerator.renderMinus(g, healthReduce, fontSize, (int)posX+100, (int)posY+96);
        }
    }

    public void startAnimation(float posX, float posY, int score, int healthReduce, boolean healthShow) {
        audioPlayer.playDestroySound(0);
        isAnimating = true;
        this.healthShow = healthShow;
        fontSize=50;
        this.score = score;
        this.healthReduce = healthReduce;
        animIndex = 0;
        this.posX = posX;
        this.posY = posY;
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

    public void updateGame() {
        posY += 1;
        updateAnimation();
    }

}
