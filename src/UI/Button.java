package UI;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Utils.*;

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

        if(path != "")
            this.temp = ImageLoader.GetSpriteAtlas(path);
        createBounds();
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

    public Rectangle getBounds() {
        return bounds;
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
