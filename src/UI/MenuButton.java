package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import GameStates.GameState;
import Main.GameEngine;
import Utils.*;
import static Utils.Constants.UIData.Buttons.*;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;    
    private GameState state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);

    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = null;
        InputStream is = getClass().getResourceAsStream(Constants.Path.BUTTON_MAINMENU);
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
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT,
                    B_HEIGHT_DEFAULT);


    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = state;
        if(state == GameState.PLAYING)
        {
            GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
        }
        else if(state == GameState.MENU)
        {
            GameEngine.audioPlayer.playSong(AudioPlayer.BACKGROUND);
        }
        else if(state == GameState.QUIT)
        {
            System.exit(0);
        }
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

}
