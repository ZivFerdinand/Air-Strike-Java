package Objects;

import Utils.ObjectSize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public abstract class Object {
    protected BufferedImage img;
    protected float posX, posY;
    protected float hitBoxX, hitBoxY;
    protected int hitBoxWidth, hitBoxHeight;
    protected Rectangle hitBox;

    protected int imageWidth, imageHeight;

    public Object(float posX, float posY, float hitBoxX, float hitBoxY, int hitBoxWidth, int hitBoxHeight, String path, ObjectSize objectSize) {
        this.posX = posX;
        this.posY = posY;
        this.hitBoxX = hitBoxX;
        this.hitBoxY = hitBoxY;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.imageWidth = objectSize.w;
        this.imageHeight = objectSize.h;

        initHitBox();
        importImg(path);
    }
    public int getPosX()
    {
        return (int)posX;
    }
    public int getPosY()
    {
        return (int) posY;
    }
    protected void drawHitBox(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) hitBoxX, (int) hitBoxY, hitBoxWidth, hitBoxHeight);
    }

    public void updateHitBox() {
        hitBox.x = (int) posX + (int) hitBoxX;
        hitBox.y = (int) posY + (int) hitBoxY;
    }

    private void importImg(String path) {
        InputStream is = getClass().getResourceAsStream(path);
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

    public Rectangle getHitBox() {
        return hitBox;
    }
}
