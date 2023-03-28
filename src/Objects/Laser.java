package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Laser {
    private int posX, posY;
    private int initPosX, initPosY;
    private int totalMvmt = 0;
    private BufferedImage img;
    private boolean hasMoved = false;

    public Laser(int posX, int posY) {
        this.initPosX = posX;
        this.initPosY = posY;
        this.posX = posX;
        this.posY = posY;
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
        posY -= 2;
        totalMvmt += 2;
        g.drawImage(img, posX + 65, posY, 20, 38, null);
    }
}
