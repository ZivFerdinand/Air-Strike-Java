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

import static Utils.Constants.ObjectSizeData.*;
import static Utils.Constants.Path.*;
import static Utils.Constants.UIData.URMButtons.*;

public class GameOverPanel extends Panels implements IStateMethod {
    private FontGenerator fontGenerator;
    private URMButton menu, play;

    public GameOverPanel(Playing playing) {
        super(playing);
        this.fontGenerator = new FontGenerator();
        loadBackground(GAMEOVER_PANEL, GAMEOVER_PANEL_SPRITE);
        createButtons();
    }

    private void createButtons() {
        int menuX = 470;
        int playX = 690;
        int y = 400;
        play = new URMButton(playX, y, URM_SIZE, URM_SIZE, 0, URM_BUTTONS);
        menu = new URMButton(menuX, y, URM_SIZE, URM_SIZE, 2, URM_BUTTONS);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);

        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        menu.draw(g);
        play.draw(g);

        fontGenerator.drawScoreOnDeath(g, 30, 515, 350);
    }

    public void update() {
        menu.update();
        play.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        play.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(e, menu))
            menu.setMouseOver(true);
        else if (isIn(e, play))
            play.setMouseOver(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menu)) {
            if (menu.isMousePressed()) {
                GameState.setState(GameState.MENU);
                playing.resetClasses();
            }
        } else if (isIn(e, play)) {
            if (play.isMousePressed()) {
                GameState.setState(GameState.PLAYING);
                playing.resetClasses();
            }
        }

        menu.resetBools();
        play.resetBools();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menu))
            menu.setMousePressed(true);
        else if (isIn(e, play))
            play.setMousePressed(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
