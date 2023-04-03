package GameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import Background.BackgroundManager;
import Interfaces.IStateMethod;
import Main.*;
import Utils.*;
import static Utils.Constants.Path.*;

public class Option extends State implements IStateMethod {
    private AudioPlayer audioPlayer;

    private BufferedImage map1, map2;
    private BufferedImage map1_Hvr, map2_Hvr;
    private BufferedImage map1_Clk, map2_Clk;
    private BufferedImage currMap1, currMap2;
    private BufferedImage chooseMapImg;

    private Rectangle map1_HB, map2_HB;

    public Option() {
        super();
        audioPlayer = new AudioPlayer();
        instantiateHitBox();
        loadBackground();
    }

    private void loadBackground() {
        map1 = ImageLoader.GetSpriteAtlas(MAP_1);
        map2 = ImageLoader.GetSpriteAtlas(MAP_2);

        map1_Hvr = ImageLoader.GetSpriteAtlas(MAP_1_HVR);
        map2_Hvr = ImageLoader.GetSpriteAtlas(MAP_2_HVR);

        map1_Clk = ImageLoader.GetSpriteAtlas(MAP_1_CLK);
        map2_Clk = ImageLoader.GetSpriteAtlas(MAP_2_CLK);

        chooseMapImg = ImageLoader.GetSpriteAtlas(CHOOSE_MAP);

        currMap1 = map1;
        currMap2 = map2;
    }

    private void instantiateHitBox() {
        map1_HB = new Rectangle(320, 100, 300, 600);
        map2_HB = new Rectangle(660, 100, 300, 600);
    }

    private void moveScene() {
        GameState.setState(GameState.PLAYING);
        audioPlayer.playSelectSound();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(SplashScreen.backgroundImg, 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT, null);
        g.drawImage(chooseMapImg, 499, 40, 282, 96, null);
        g.drawImage(currMap1, 320, 150, 300, 600, null);
        g.drawImage(currMap2, 660, 150, 300, 600, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, map1_HB)) {
            currMap1 = map1_Clk;
        } else if (isIn(e, map2_HB)) {
            currMap2 = map2_Clk;
        } else {
            currMap1 = map1;
            currMap2 = map2;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, map1_HB)) {
            BackgroundManager.isFirstMap = true;
            moveScene();
        }
        if (isIn(e, map2_HB)) {
            BackgroundManager.isFirstMap = false;
            moveScene();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isIn(e, map1_HB)) {
            currMap1 = map1_Hvr;
        } else if (isIn(e, map2_HB)) {
            currMap2 = map2_Hvr;
        } else {
            currMap1 = map1;
            currMap2 = map2;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}