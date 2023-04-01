package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.*;
import UI.MenuButton;
import Utils.*;;

public class Menu extends State implements IStateMethod {
    private AudioPlayer audioPlayer;
    private MenuButton[] buttons = new MenuButton[2];
    private BufferedImage backgroundPanel, backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(GameEngine gameEngine) {
        super(gameEngine);
        loadButtons();
        loadBackground();
        audioPlayer = new AudioPlayer();
    }

    private void loadBackground() {
        InputStream is = getClass().getResourceAsStream(Constants.Path.MAINMENU_PANEL);
        try {

            backgroundPanel = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream(Constants.Path.MAIN_MENU_BG);
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
        menuWidth = (int) backgroundPanel.getWidth() * 2;
        menuHeight = (int) backgroundPanel.getHeight() * 2;
        menuX = GamePanel.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = GamePanel.GAME_HEIGHT / 2 - menuHeight / 2;

    }

    private void loadButtons() {
        buttons[0] = new MenuButton(GamePanel.GAME_WIDTH / 2, (int) 350, 0, GameState.OPTIONS);
        buttons[1] = new MenuButton(GamePanel.GAME_WIDTH / 2, (int) 480, 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT, null);
        g.drawImage(backgroundPanel, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    audioPlayer.playSelectSound();
                    mb.applyGameState();
                    break;
                }
            }
        }

        resetButtons();

    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}