package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Input.*;

import static Enum.Constants.Directions.*;


public class GamePanel extends JPanel {

    private MouseInput mouseInput;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    private BufferedImage upAnim;
    private BufferedImage downAnim;
    private BufferedImage leftAnim;
    private BufferedImage rightAnim;
    private BufferedImage leftupAnim;
    private BufferedImage rightupAnim;
    private BufferedImage leftdownAnim;
    private BufferedImage rightdownAnim;
    private BufferedImage idleAnim;
    private BufferedImage currAnimation;
    public GamePanel() {
        mouseInput = new MouseInput(this);
        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void loadAnimations() {
        BufferedImage[][] animations = new BufferedImage[3][3];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 200, j * 200, 200, 200);

        upAnim = animations[0][1];
        downAnim = animations[2][1];
        leftAnim = animations[1][0];
        rightAnim = animations[1][2];
        leftupAnim = animations[0][0];
        rightupAnim = animations[0][2];
        leftdownAnim = animations[2][0];
        rightdownAnim = animations[2][2];
        idleAnim = animations[1][1];
    currAnimation=idleAnim;
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/3x3Sprite.png");
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

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        if(moving==false){
            currAnimation=idleAnim;
        }
        this.moving = moving;
    }



    private void setAnimation() {
        if (moving)
            if(playerDir==UP){
                currAnimation=upAnim;
            }
            else if(playerDir==DOWN){
                currAnimation=downAnim;
            }
            else if(playerDir==LEFT){
                currAnimation=leftAnim;
            }
            else if(playerDir==RIGHT){
                currAnimation=rightAnim;
            }
        else
            currAnimation=idleAnim;
    }

    private void updatePos() {
        if (moving) {
            switch (playerDir) {
                case LEFT:
                    xDelta -= 2;
                    break;
                case UP:
                    yDelta -= 2;
                    break;
                case RIGHT:
                    xDelta += 2;
                    break;
                case DOWN:
                    yDelta += 2;
                    break;
            }
        }
    }

    public void updateGame() {


        setAnimation();
        updatePos();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(currAnimation, (int) xDelta, (int) yDelta, 150, 150, null);
    }

}