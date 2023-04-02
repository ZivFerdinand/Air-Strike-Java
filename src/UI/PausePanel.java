package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import GameStates.GameState;
import GameStates.Playing;
import Main.GamePanel;
import static Utils.Constants.UIData.URMButtons.URM_SIZE;
import static Utils.Constants.Path.PAUSE_PANEL;

public class PausePanel {
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
        int menuX = 313;
        int replayX = 387;
        int unpauseX = 462;
        int bY = 325;

        menuB = new URMButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new URMButton(replayX, bY,URM_SIZE,URM_SIZE, 1);
        unpauseB = new URMButton(unpauseX, bY,URM_SIZE,URM_SIZE, 0);

    }

    private void loadBackground() {
        InputStream is = getClass().getResourceAsStream(PAUSE_PANEL);
        try {

            backgroundImg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bgW = backgroundImg.getWidth();
        bgH = backgroundImg.getHeight();
        bgX = GamePanel.GAME_WIDTH / 2 - bgW / 2;
        bgY = 25;

    }

    public void update() {
        menuB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g) {
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
                GameState.state = GameState.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed())
                System.out.println("replay lvl!");
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                playing.unpauseGame();
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

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
