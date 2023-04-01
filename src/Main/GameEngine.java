package Main;

import Background.BackgroundManager;
import Collision.CollisionManager;
import Objects.*;
import Utils.*;

public class GameEngine implements Runnable {
    public AudioPlayer audioPlayer;
    private final GamePanel gamePanel;
    private final GameFrame gameFrame;

    private PlayerPlane playerPlane;
    private BackgroundManager background;
    private CollisionManager collisionManager;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;
    private Explosion explosionHelicopter;
    private Explosion explosionUFO;
    public static Score score;

    public GameEngine() {
        audioPlayer = new AudioPlayer();
        initClasses();
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void startGameLoop() {
        audioPlayer.playSong(AudioPlayer.LEVEL_1);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        this.background = new BackgroundManager();
        this.playerPlane = new PlayerPlane();
        this.score = new Score();

        this.enemyHelicopter = new EnemyHelicopter(this);
        this.enemyUFO = new EnemyUFO( this);
        this.collisionManager = new CollisionManager(this);

        this.explosionHelicopter = new Explosion(Constants.ObjectSizeData.EXP_HELICOPTER_PLANE, Constants.ObjectSizeData.EXP_HELICOPTER_PLANE);
        this.explosionUFO = new Explosion(Constants.ObjectSizeData.EXP_UFO_PLANE, Constants.ObjectSizeData.EXP_UFO_PLANE);
    }

    public PlayerPlane getPlayerPlane() {
        return this.playerPlane;
    }

    public EnemyHelicopter getEnemyHelicopter() {
        return this.enemyHelicopter;
    }

    public BackgroundManager getBackground() {
        return this.background;
    }

    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    public Explosion getExplosionHelicopter() {
        return explosionHelicopter;
    }

    public Explosion getExplosionUFO() {
        return explosionUFO;
    }

    public EnemyUFO getEnemyUFO() {
        return enemyUFO;
    }

    public void update() {
        playerPlane.update();

        collisionManager.updateCollisionDetection();

        enemyHelicopter.update();
        enemyUFO.update();
        explosionHelicopter.update();
        explosionUFO.update();
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

}