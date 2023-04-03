package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Interfaces.IStateMethod;
import Main.GamePanel;
import Utils.AudioPlayer;
import Utils.FontGenerator;
import Utils.ImageLoader;

import static Utils.Constants.Path.*;

public class SplashScreen extends State implements IStateMethod {
    private BufferedImage logoTitle;
    private FontGenerator fontGenerator;
    private final AudioPlayer audioPlayer;
    public static BufferedImage backgroundImg = ImageLoader.GetSpriteAtlas(MAIN_MENU_BG);

    public SplashScreen()
    {
        super();
        audioPlayer = new AudioPlayer();
        loadBackground();
    }
    
    private void loadBackground() {
        fontGenerator = new FontGenerator();
        logoTitle = ImageLoader.GetSpriteAtlas(MAINMENU_LOGO);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT, null);
        g.drawImage(logoTitle, GamePanel.GAME_WIDTH / 2 - 500, GamePanel.GAME_HEIGHT / 2 - 250, 1000, 500, null);
        fontGenerator.drawInstruction(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameState.setState(GameState.MENU);
        audioPlayer.playSelectSound();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.setState(GameState.MENU);
            audioPlayer.playSelectSound();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
