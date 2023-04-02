package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import static Utils.Constants.UIData.PauseButton.*;
import static Utils.Constants.Path.*;

public class PauseButton extends Button {
    private BufferedImage[] imgs;
    private int index;
    private boolean mouseOver, mousePressed;

    public PauseButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImgs();
        //TODO Auto-generated constructor stub
    }

    private void loadImgs() {
        BufferedImage temp = null;
        InputStream is = getClass().getResourceAsStream(PAUSE_BUTTON);
        try {
            temp = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * PAUSE_SIZE_DEFAULT, 0, PAUSE_SIZE_DEFAULT,
                    PAUSE_SIZE_DEFAULT);
    }
    
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, PAUSE_SIZE, PAUSE_SIZE, null);
    }
    

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
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
