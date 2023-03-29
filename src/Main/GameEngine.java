package Main;

import Background.BackgroundManager;
import Collision.CollisionManager;
import Objects.EnemyPlane;
import Objects.PlayerPlane;

public class GameEngine implements Runnable {

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;

    private PlayerPlane playerPlane;
    private BackgroundManager background;
    private CollisionManager collisionManager;
    private EnemyPlane enemyPlane;

    public GameEngine() {
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
        this.enemyPlane = new EnemyPlane(50, -50);
        this.collisionManager = new CollisionManager(playerPlane, playerPlane.getLaserShoot(), enemyPlane);
    }

    public PlayerPlane getPlayerPlane() {
        return this.playerPlane;
    }

    public EnemyPlane getEnemyPlane()
    {
        return this.enemyPlane;
    }
    public BackgroundManager getBackground() {
        return this.background;
    }
    public CollisionManager getCollisionManager()
    {
        return this.collisionManager;
    }

    public void update() {

        playerPlane.updateGame();
        collisionManager.updateCollisionDetection();
        enemyPlane.update();
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