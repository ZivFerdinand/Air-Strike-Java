package GameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Background.BackgroundManager;
import Main.*;
import Utils.*;;

public class Option extends State implements IStateMethod {
    private AudioPlayer audioPlayer;
    private BufferedImage backgroundImg;
    private BufferedImage map1, map2;
    private BufferedImage map1_Hvr, map2_Hvr;
    private BufferedImage map1_Clk, map2_Clk;
    private BufferedImage currMap1, currMap2;

    private FontGenerator fontGenerator;
    private Rectangle map1_HB, map2_HB;

    public Option(GameEngine gameEngine) {
        super(gameEngine);
        fontGenerator = new FontGenerator();
        audioPlayer = new AudioPlayer();
        instantiateHitBox();
        loadBackground();

        currMap1 = map1;
        currMap2 = map2;
    }

    private void loadBackground() {
        InputStream is = getClass().getResourceAsStream(Constants.Path.MAIN_MENU_BG);
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


        is = getClass().getResourceAsStream(Constants.Path.MAP_1);
        try {

            map1 = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream(Constants.Path.MAP_2);
        try {

            map2 = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        is = getClass().getResourceAsStream(Constants.Path.MAP_1_HVR);
        try {

            map1_Hvr = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream(Constants.Path.MAP_2_HVR);
        try {

            map2_Hvr = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream(Constants.Path.MAP_1_CLK);
        try {

            map1_Clk = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = getClass().getResourceAsStream(Constants.Path.MAP_2_CLK);
        try {

            map2_Clk = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void update() {
    }

    private void instantiateHitBox() {
        map1_HB = new Rectangle(320, 100, 300, 600);
        map2_HB = new Rectangle(660, 100, 300, 600);
    }

    int x = 320;

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT, null);
        fontGenerator.chooseMap(g, 40);
        g.drawImage(currMap1, x, 100, 300, 600, null);
        g.drawImage(currMap2, 660, 100, 300, 600, null);
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

    private void moveScene() {
        GameState.state = GameState.PLAYING;
        GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
        audioPlayer.playSelectSound();
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