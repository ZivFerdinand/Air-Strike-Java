package Main;

import Background.BackgroundManager;
import Collision.CollisionManager;
import Objects.*;
import Utils.AudioPlayer;

public class GameEngine implements Runnable {
    public static AudioPlayer audioPlayer;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;

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
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        this.background = new BackgroundManager();
        this.playerPlane = new PlayerPlane((GamePanel.GAME_WIDTH - 150) / 2, 600);
        this.enemyHelicopter = new EnemyHelicopter(50, -50, this);
        this.enemyUFO = new EnemyUFO(50, 50, this);
        this.collisionManager = new CollisionManager(playerPlane, playerPlane.getLaserShoot(), enemyHelicopter,
                enemyUFO);
        this.explosionHelicopter = new Explosion(10, 10, 100, 96, 20);
        this.explosionUFO = new Explosion(10, 10, 200, 192, 50);
        this.score = new Score();
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

        playerPlane.updateGame();
        collisionManager.updateCollisionDetection();
        enemyHelicopter.update();
        enemyUFO.update();
        explosionHelicopter.updateGame();
        explosionUFO.updateGame();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

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
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

}