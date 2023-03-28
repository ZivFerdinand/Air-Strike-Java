package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Laser {
    private int posX, posY;
    private BufferedImage img;

    public Laser(int posX, int posY) {
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
    public int getPosY()
    {
        return posY;
    }
    public void resetPos(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }
    public void render(Graphics g) {
        posY -= 2;
        g.drawImage(img, posX + 55, posY, 40, 75, null);
    }
}
