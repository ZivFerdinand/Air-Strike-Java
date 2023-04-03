package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameStates.Playing;
import Main.GamePanel;
import Utils.*;

public abstract class Panels {
    protected Playing playing;
    protected BufferedImage backgroundImg;
    protected int bgX, bgY, bgW, bgH;

    public Panels(Playing playing) {
        this.playing = playing;
    }

    protected void loadBackground(ObjectSize objectSize, String path) {
        backgroundImg = ImageLoader.GetSpriteAtlas(path);
        bgW = objectSize.w;
        bgH = objectSize.h;
        bgX = GamePanel.GAME_WIDTH / 2 - bgW / 2;
        bgY = GamePanel.GAME_HEIGHT / 2 - bgH / 2;
    }
    protected boolean isIn(MouseEvent e, Button b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}