package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import GameStates.GameState;
import GameStates.Playing;
import Interfaces.IStateMethod;
import Main.GamePanel;
import Utils.ImageLoader;
import Utils.Constants.ObjectSizeData;

import static Utils.Constants.UIData.URMButtons.URM_SIZE;
import static Utils.Constants.Path.*;

public class PausePanel implements IStateMethod{
    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private URMButton menuB, replayB, unpauseB;

    public PausePanel(Playing playing) {
		this.playing = playing;
		loadBackground();
		createURMButtons();
	}
    
    private void createURMButtons() {
        int menuX = 434;
        int replayX = 584;
        int unpauseX = 734;
        int bY = 400;

        menuB = new URMButton(menuX, bY, URM_SIZE, URM_SIZE, 2, URM_BUTTONS);
        replayB = new URMButton(replayX, bY,URM_SIZE,URM_SIZE, 1, URM_BUTTONS);
        unpauseB = new URMButton(unpauseX, bY,URM_SIZE,URM_SIZE, 0, URM_BUTTONS);

    }

    private void loadBackground() {
        backgroundImg = ImageLoader.GetSpriteAtlas(PAUSE_PANEL);
        bgW = ObjectSizeData.PAUSE_PANEL.w;
        bgH = ObjectSizeData.PAUSE_PANEL.h;
        bgX = GamePanel.GAME_WIDTH / 2 - bgW / 2;
        bgY = GamePanel.GAME_HEIGHT / 2 - bgH / 2;
    }

    public void update() {
        menuB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5F));
        g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
        // Background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        // UrmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.setState(GameState.MENU);
                playing.resetClasses();
                playing.setPaused(false);
                
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed())
            {
                GameState.setState(GameState.PLAYING);
                playing.resetClasses();
                playing.setPaused(false);
            }
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                playing.setPaused(false);
        }

        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);

    }

    private boolean isIn(MouseEvent e, Button b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
