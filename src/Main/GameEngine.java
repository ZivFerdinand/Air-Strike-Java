package Main;

import java.awt.Graphics;

import GameStates.*;
import GameStates.Playing;
import Utils.*;

public class GameEngine implements Runnable {
    public static AudioPlayer audioPlayer;
    private final GamePanel gamePanel;
    private final GameFrame gameFrame;

    private Playing playing;
    private Menu menu;
    private Option option;

    public static Score score;

    public GameEngine() {
        audioPlayer = new AudioPlayer();
        initClasses();
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel, this);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        audioPlayer.playSong(AudioPlayer.BACKGROUND);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        score = new Score();
        this.menu = new Menu(this);
        this.playing = new Playing(this);
        this.option = new Option(this);
    }

    public void update() {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case OPTIONS:
                option.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case OPTIONS:
                option.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / 120;
        double timePerUpdate = 1000000000.0 / 200;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
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
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
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
}