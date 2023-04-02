package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Main.*;
import Objects.*;
import Background.BackgroundManager;
import Collision.CollisionManager;
import Utils.*;
import UI.*;
import static Utils.Constants.UIData.PauseButton.*;

public class Playing extends State implements IStateMethod {
    private PlayerPlane playerPlane;
    private BackgroundManager background;
    private CollisionManager collisionManager;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;
    private Explosion explosionHelicopter;
    private Explosion explosionUFO;
    private Explosion explosionPlayer;
    private ArrayList<Coin> star;
    private FontGenerator fontGenerator;
    private PauseButton pauseButton;
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;
    public static Score score;
    private boolean hasDied;
    public static boolean paused;
    public static boolean gameOver;

    public Playing(GameEngine gameEngine) {
        super(gameEngine);
        initClasses();
    }

    public void initClasses() {
        score = new Score();
        this.playerPlane = new PlayerPlane();
        this.background = new BackgroundManager(this);
        this.enemyHelicopter = new EnemyHelicopter(this);
        this.enemyUFO = new EnemyUFO(this);
        this.star = new ArrayList<>();
        instantiateStars();
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
        this.pauseButton = new PauseButton(1174, 55, PAUSE_SIZE, PAUSE_SIZE);
        this.hasDied = false;
        paused = false;
        gameOver = false;
    }

    private void instantiateStars() {
        for(int i = 0;i<5;i++){
            star.add(new Coin(Constants.ObjectSizeData.STAR));
        }
    }

    @Override
    public void update() {
        if (playerPlane.getHealth() <= 0 && !hasDied) {
            hasDied = true;
            explosionPlayer.startAnimation(playerPlane.getPosX(), playerPlane.getPosY());
        }
        if (gameOver) {
            gameOverPanel.update();

        }
        else if (!paused && !gameOver) {
            playerPlane.update();
            collisionManager.updateCollisionDetection();
            enemyHelicopter.update();
            enemyUFO.update();
            explosionHelicopter.update();
            explosionUFO.update();
            explosionPlayer.update();
            pauseButton.update();
            for (Coin s : star) {
                s.update();
            }
        }
        else {
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
        for (Coin s : star) {
            s.render(g);
        }
        playerPlane.render(g);
        collisionManager.render(g);
        fontGenerator.render(g, 40F, playerPlane.getHealth(), score.getScore());

        if (paused) {
            pausePanel.draw(g);
        }
        if (gameOver) {
            gameOverPanel.draw(g);
        }
    }

    private boolean isInPauseButton(MouseEvent e) {
        return pauseButton.getBounds().contains(e.getX(), e.getY());
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
    
    public void unpauseGame() {
        paused = false;
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

    public ArrayList<Coin> getStar() {
        return star;
    }

}
