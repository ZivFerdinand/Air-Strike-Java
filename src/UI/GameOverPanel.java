package UI;

import GameStates.GameState;
import GameStates.Playing;
import Interfaces.IStateMethod;
import Main.GamePanel;
import Utils.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Utils.Constants.Path.*;
import static Utils.Constants.UIData.URMButtons.*;

public class GameOverPanel implements IStateMethod {
    private Playing playing;
    private BufferedImage img;
    private FontGenerator fontGenerator;
    private int imgX, imgY, imgW, imgH;
    private URMButton menu, play;

    public GameOverPanel(Playing playing) {
		this.playing = playing;
        this.fontGenerator = new FontGenerator();
		createImg();
        createButtons();
	}

    private void createButtons() {
        int menuX = 470;
        int playX = 690;
        int y = 400;
        play = new URMButton(playX, y, URM_SIZE, URM_SIZE, 0, URM_BUTTONS);
        menu = new URMButton(menuX, y, URM_SIZE, URM_SIZE, 2, URM_BUTTONS);
    }

    private void createImg() {
        img = ImageLoader.GetSpriteAtlas(DEATH_PANEL);
        imgW = Constants.ObjectSizeData.GAMEOVER_PANEL.w;
        imgH = Constants.ObjectSizeData.GAMEOVER_PANEL.h;
        imgX = GamePanel.GAME_WIDTH / 2 - imgW / 2;
        imgY = GamePanel.GAME_HEIGHT / 2 - imgH / 2;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);

        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        menu.draw(g);
        play.draw(g);

        fontGenerator.showScore(g, 30, 515, 350);
    }

    public void update() {
        menu.update();
        play.update();
    }

    public void keyPressed(KeyEvent e) {

    }

    private boolean isIn(URMButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        play.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(play, e))
            play.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                GameState.setState(GameState.MENU);
                playing.resetClasses();
            }
        } else if (isIn(play, e)){
            if (play.isMousePressed()) {
                GameState.setState(GameState.PLAYING);
                playing.resetClasses();
            }
        }

        menu.resetBools();
        play.resetBools();
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(play, e))
            play.setMousePressed(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
