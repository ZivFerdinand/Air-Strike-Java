package GameStates;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import Interfaces.IStateMethod;
import Main.*;
import UI.MenuButton;
import Utils.*;
import static Utils.Constants.Path.*;


public class Menu extends State implements IStateMethod {
    private final AudioPlayer audioPlayer;
    private MenuButton[] buttons;
    private BufferedImage backgroundPanel;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu() {
        super();
        loadButtons();
        loadBackground();
        audioPlayer = new AudioPlayer();
    }

    private void loadBackground() {
        backgroundPanel = ImageLoader.GetSpriteAtlas(MAINMENU_PANEL);

        menuWidth = backgroundPanel.getWidth() * 2;
        menuHeight = backgroundPanel.getHeight() * 2;
        menuX = GamePanel.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = GamePanel.GAME_HEIGHT / 2 - menuHeight / 2;
    }

    private void loadButtons() {
        buttons = new MenuButton[2];
        buttons[0] = new MenuButton(GamePanel.GAME_WIDTH / 2, 350, 0, GameState.OPTIONS, BUTTON_MAINMENU);
        buttons[1] = new MenuButton(GamePanel.GAME_WIDTH / 2, 480, 2, GameState.QUIT, BUTTON_MAINMENU);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(SplashScreen.backgroundImg, 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT, null);
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