package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Laser extends Object{
    private final int laserSpeed = 4;
    // private int posX, posY;
    private int initPosX, initPosY;
    private int totalMvmt = 0;
    private BufferedImage img;
    private boolean hasMoved = false;

    public Laser(int posX, int posY) {
        super(posX, posY,70, 0, 10, 38);
        this.initPosX = posX;
        this.initPosY = posY;
        importImg();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Laser-Sprite.png");
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

    public boolean checkHasMoved() {
        if (posX != initPosX || posY != initPosY) {
            hasMoved = true;
            return true;
        }

        return hasMoved;
    }

    public void resetPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getTotalMvmt() {
        return totalMvmt;
    }

    public void setTotalMvmt(int totalMvmt) {
        this.totalMvmt = totalMvmt;
    }

    public void render(Graphics g) {
        drawHitBox(g);
        posY -= laserSpeed;
        totalMvmt += laserSpeed;
        g.drawImage(img, (int) posX + 65, (int) posY, 20, 38, null);
    }
    
}
