package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Main.*;
import Objects.*;
import Background.BackgroundManager;
import Collision.CollisionManager;
import Interfaces.IStateMethod;
import Utils.*;
import UI.*;
import static Utils.Constants.UIData.PauseButton.*;
import static Utils.Constants.Path.*;

public class Playing extends State implements IStateMethod {
    private PlayerPlane playerPlane;
    private BackgroundManager background;
    private CollisionManager collisionManager;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;
    private Explosion explosionHelicopter;
    private Explosion explosionUFO;
    private Explosion explosionPlayer;
    private ArrayList<Coin> coins;
    private FontGenerator fontGenerator;
    private PauseButton pauseButton;
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;

    private boolean hasDied;

    private static boolean paused;
    private static boolean gameOver;

    public static Score score;

    public Playing() {
        super();
        initClasses();
    }

    private void initClasses() {
        score = new Score(this);
        this.coins = instantiateCoins();
        this.playerPlane = new PlayerPlane();
        this.background = new BackgroundManager(this);
        this.enemyHelicopter = new EnemyHelicopter(this);
        this.enemyUFO = new EnemyUFO(this);
        this.collisionManager = new CollisionManager(this);
        this.explosionHelicopter = new Explosion(Constants.ObjectSizeData.EXP_HELICOPTER_PLANE,
                Constants.ObjectSizeData.EXP_HELICOPTER_PLANE);
        this.explosionUFO = new Explosion(Constants.ObjectSizeData.EXP_UFO_PLANE,
                Constants.ObjectSizeData.EXP_UFO_PLANE);
        this.explosionPlayer = new Explosion(Constants.ObjectSizeData.EXP_PLAYER_PLANE,
                Constants.ObjectSizeData.EXP_PLAYER_PLANE);
        this.fontGenerator = new FontGenerator();
        this.pausePanel = new PausePanel(this);
        this.gameOverPanel = new GameOverPanel(this);
        this.pauseButton = new PauseButton(1174, 55, PAUSE_SIZE, PAUSE_SIZE, PAUSE_BUTTON);

        this.hasDied = false;
        paused = false;
        gameOver = false;
    }

    public void resetClasses() {
        background.reset();
        playerPlane.reset();
        enemyHelicopter.reset();
        enemyUFO.reset();
        explosionHelicopter.reset();
        explosionUFO.reset();
        explosionPlayer.reset();
        score = new Score(this);
        this.hasDied = false;
        paused = false;
        gameOver = false;
    }

    @Override
    public void update() {
        if (playerPlane.getHealth() <= 0 && !hasDied) {
            hasDied = true;
            explosionPlayer.startAnimation(playerPlane.getPosX(), playerPlane.getPosY());
        }

        if (gameOver) {
            gameOverPanel.update();

        } else if (!paused && !gameOver) {
            playerPlane.update();
            collisionManager.updateCollisionDetection();
            enemyHelicopter.update();
            enemyUFO.update();
            explosionHelicopter.update();
            explosionUFO.update();
            explosionPlayer.update();
            pauseButton.update();
            updateCoins(coins);

        } else {
            pausePanel.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        background.render(g);
        enemyHelicopter.render(g);
        enemyUFO.render(g);
        explosionHelicopter.render(g);
        explosionUFO.render(g);
        explosionPlayer.render(g);
        pauseButton.draw(g);
        renderCoins(coins, g);
        playerPlane.render(g);
        collisionManager.render(g);
        fontGenerator.drawHealthAndScore(g, 40F, playerPlane.getHealth(), score.getScore());

        if (paused) {
            pausePanel.draw(g);
        }
        if (gameOver) {
            gameOverPanel.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pauseButton.setMousePressed(false);
        if (paused)
            pausePanel.mousePressed(e);
        if (gameOver)
            gameOverPanel.mousePressed(e);
        if (isInPauseButton(e))
            pauseButton.setMousePressed(true);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused)
            pausePanel.mouseReleased(e);
        if (gameOver)
            gameOverPanel.mouseReleased(e);
        if (isInPauseButton(e))
            paused = true;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pauseButton.setMouseOver(false);
        if (paused)
            pausePanel.mouseMoved(e);
        if (gameOver)
            gameOverPanel.mouseMoved(e);
        if (isInPauseButton(e))
            pauseButton.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                playerPlane.setUp(true);
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                playerPlane.setLeft(true);
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                playerPlane.setDown(true);
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                playerPlane.setRight(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                playerPlane.setUp(false);
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                playerPlane.setLeft(false);
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                playerPlane.setDown(false);
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                playerPlane.setRight(false);
                break;
        }
    }

    public static boolean isPaused() {
        return paused;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean status) {
        gameOver = status;
    }

    public void setPaused(boolean status) {
        paused = status;
    }

    public PlayerPlane getPlayerPlane() {
        return playerPlane;
    }

    public EnemyHelicopter getEnemyHelicopter() {
        return enemyHelicopter;
    }

    public EnemyUFO getEnemyUFO() {
        return enemyUFO;
    }

    public Explosion getExplosionHelicopter() {
        return explosionHelicopter;
    }

    public Explosion getExplosionUFO() {
        return explosionUFO;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    private ArrayList<Coin> instantiateCoins() {
        ArrayList<Coin> newC = new ArrayList<Coin>();
        for (int i = 0; i < 5; i++) {
            newC.add(new Coin(Constants.ObjectSizeData.STAR));
        }

        return newC;
    }

    private void updateCoins(ArrayList<Coin> coin) {
        for (Coin s : coin) {
            s.update();
        }
    }

    private void renderCoins(ArrayList<Coin> coin, Graphics g) {
        for (Coin s : coin) {
            s.render(g);
        }
    }

    private boolean isInPauseButton(MouseEvent e) {
        return pauseButton.getBounds().contains(e.getX(), e.getY());
    }
}
