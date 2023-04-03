package Main;

import java.awt.Graphics;

import GameStates.*;
import Utils.*;

public class GameEngine implements Runnable {
    public static AudioPlayer audioPlayer;
    private final GamePanel gamePanel;
    private SplashScreen splashScreen;
    private Playing playing;
    private Menu menu;
    private Option option;

    public GameEngine() {
        audioPlayer = new AudioPlayer();
        initClasses();
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel, this);
        startGameLoop();
        gamePanel.requestFocus();
    }

    private void startGameLoop() {
        audioPlayer.playSong(AudioPlayer.BACKGROUND);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        this.splashScreen = new SplashScreen();
        this.menu = new Menu();
        this.playing = new Playing();
        this.option = new Option();
    }

    public void update() {
        switch (GameState.state) {
            case SPLASH_SCREEN -> splashScreen.update();
            case MENU -> menu.update();
            case OPTIONS -> option.update();
            case PLAYING -> playing.update();
            default -> {
            }
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case SPLASH_SCREEN -> splashScreen.draw(g);
            case MENU -> menu.draw(g);
            case OPTIONS -> option.draw(g);
            case PLAYING -> playing.draw(g);
            default -> {
            }
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / 120;
        double timePerUpdate = 1000000000.0 / 200;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }

    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public Option getOption() {
        return option;
    }
    public SplashScreen getSplashScreen() {
        return splashScreen;
    }
}