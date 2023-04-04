package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import Main.GameEngine;
import Utils.*;
import static Utils.Constants.UIData.Buttons.*;

public class MenuButton extends Button {
    private final int xOffsetCenter = B_WIDTH / 2;
    private final GameState state;


    private BufferedImage[] imgs;
    private static BufferedImage privateImg;

    public MenuButton(int x, int y, int rowIndex, GameState state, String path) {
        super(x, y, B_WIDTH, B_HEIGHT, "");
        this.x = x;
        this.y = y;
        this.state = state;
        if(privateImg == null)
        {
            privateImg = ImageLoader.GetSpriteAtlas(path);
        }
        loadImg(rowIndex);
        initBounds();
    }
    private void loadImg(int rowIndex) {
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = privateImg.getSubimage(i * Utils.Constants.UIData.Buttons.B_WIDTH_DEFAULT, rowIndex * Constants.UIData.Buttons.B_HEIGHT_DEFAULT, Utils.Constants.UIData.Buttons.B_WIDTH_DEFAULT,
                    Constants.UIData.Buttons.B_HEIGHT_DEFAULT);
    }
    private void initBounds() {
        bounds = new Rectangle(x - xOffsetCenter, y, B_WIDTH, B_HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x - xOffsetCenter, y, B_WIDTH, B_HEIGHT, null);
    }

    public void applyGameState() {
        GameState.state = state;
        if (state == GameState.PLAYING) {
            GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
        } else if (state == GameState.MENU) {
            GameEngine.audioPlayer.playSong(AudioPlayer.BACKGROUND);
        } else if (state == GameState.QUIT) {
            System.exit(0);
        }
    }

}
