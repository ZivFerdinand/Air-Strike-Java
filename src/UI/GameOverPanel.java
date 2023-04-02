package UI;

import GameStates.GameState;
import GameStates.Playing;
import Main.GameEngine;
import Main.GamePanel;
import Utils.AudioPlayer;
import Utils.Constants;

import static Utils.Constants.UIData.URMButtons.URM_SIZE;

import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import static Utils.Constants.Path.DEATH_PANEL;

public class GameOverPanel {
    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;
    private URMButton menu, play;

    public GameOverPanel(Playing playing) {
		this.playing = playing;
		createImg();
		createButtons();
	}

    private void createButtons() {
        int menuX = 470;
        int playX = 690;
        int y = 385;
        play = new URMButton(playX, y, URM_SIZE, URM_SIZE, 0);
        menu = new URMButton(menuX, y, URM_SIZE, URM_SIZE, 2);

    }

    private void createImg() {
        InputStream is = getClass().getResourceAsStream(DEATH_PANEL);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                GameEngine.audioPlayer.playSong(AudioPlayer.BACKGROUND);
                playing.initClasses();
                GameState.state = GameState.MENU;
            }
        } else if (isIn(play, e)){
            if (play.isMousePressed()) {
                GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
                playing.initClasses();
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
}
