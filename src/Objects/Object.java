package Objects;

import Utils.ObjectSize;

import java.awt.*;
import java.awt.image.BufferedImage;
import Utils.ImageLoader;

public abstract class Object {
    protected float posX, posY;
    protected float hitBoxX, hitBoxY;
    protected int hitBoxWidth, hitBoxHeight;
    protected int imageWidth, imageHeight;
    protected Rectangle hitBox;
    protected BufferedImage img;

    protected int counterPassed, speed;

    public Object(float posX, float posY, float hitBoxX, float hitBoxY, int hitBoxWidth, int hitBoxHeight, String path,
            ObjectSize objectSize, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.hitBoxX = hitBoxX;
        this.hitBoxY = hitBoxY;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.imageWidth = objectSize.w;
        this.imageHeight = objectSize.h;
        this.counterPassed = 0;
        this.speed = speed;
        initHitBox();
        importImg(path);
    }

    public int getPosX() {
        return (int) posX;
    }

    public int getPosY() {
        return (int) posY;
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) hitBoxX, (int) hitBoxY, hitBoxWidth, hitBoxHeight);
    }

    private void importImg(String path) {
        if(path != "")
            img = ImageLoader.GetSpriteAtlas(path);
    }
    protected BufferedImage importPrivateImg(String path) {
        return ImageLoader.GetSpriteAtlas(path);
    }

    public void updateHitBox() {
        hitBox.x = (int) posX + (int) hitBoxX;
        hitBox.y = (int) posY + (int) hitBoxY;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    protected void drawDebuggingHitBox(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
}
