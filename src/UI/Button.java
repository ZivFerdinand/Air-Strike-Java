package UI;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Utils.ImageLoader;

public abstract class Button {
    protected int x, y, width, height;
    protected Rectangle bounds;

    protected boolean mouseOver, mousePressed;
    protected BufferedImage[] imgs;
    protected BufferedImage temp;
    protected int index;
    protected String path;

    public Button(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.index = 0;
        this.path = path;
        this.mouseOver = false;
        this.mousePressed = false;
        this.temp = ImageLoader.GetSpriteAtlas(path);
        createBounds();
    }

    protected void loadImgs(int size) {
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * size, 0, size,
                    size);
    }

    protected void loadImgs(int size, int rowIndex) {
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * size, rowIndex * size, size,
                    size);
    }

    protected void loadImgs(int widthDef, int heightDef, int rowIndex) {
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * widthDef, rowIndex * heightDef, widthDef,
                    heightDef);
    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    protected int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    protected void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    protected boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

}
