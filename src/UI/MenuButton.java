package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import GameStates.GameState;
import Main.GameEngine;
import Utils.*;
import static Utils.Constants.UIData.Buttons.*;

public class MenuButton extends Button {
    private int xOffsetCenter = B_WIDTH / 2;
    private GameState state;

    public MenuButton(int x, int y, int rowIndex, GameState state, String path) {
        super(x, y, B_WIDTH, B_HEIGHT, path);
        this.x = x;
        this.y = y;
        this.state = state;
        loadImgs(B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT, rowIndex);
        initBounds();
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
