package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Main.*;
import Objects.*;
import Background.BackgroundManager;
import Collision.CollisionManager;
import Utils.*;

public class Playing extends State implements IStateMethod {
    private PlayerPlane playerPlane;
    private BackgroundManager background;
    private CollisionManager collisionManager;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;
    private Explosion explosionHelicopter;
    private Explosion explosionUFO;
    private Score score;
    private FontGenerator fontGenerator;

    private void initClasses() {
        this.background = new BackgroundManager();
        this.playerPlane = new PlayerPlane();
        this.score = gameEngine.score;

        this.enemyHelicopter = new EnemyHelicopter(this);
        this.enemyUFO = new EnemyUFO(this);
        this.collisionManager = new CollisionManager(this);

        this.explosionHelicopter = new Explosion(Constants.ObjectSizeData.EXP_HELICOPTER_PLANE,
                Constants.ObjectSizeData.EXP_HELICOPTER_PLANE);
        this.explosionUFO = new Explosion(Constants.ObjectSizeData.EXP_UFO_PLANE,
                Constants.ObjectSizeData.EXP_UFO_PLANE);
        this.fontGenerator = new FontGenerator();
    }



    public Playing(GameEngine gameEngine) {
        super(gameEngine);
        initClasses();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        playerPlane.update();

        collisionManager.updateCollisionDetection();

        enemyHelicopter.update();
        enemyUFO.update();
        explosionHelicopter.update();
        explosionUFO.update();
    }

    @Override
    public void draw(Graphics g) {
        background.render(g);
        enemyHelicopter.render(g);
        enemyUFO.render(g);
        explosionHelicopter.render(g);
        explosionUFO.render(g);
        playerPlane.render(g);
        collisionManager.render(g);
        fontGenerator.render(g, 40F, playerPlane.getHealth(), GameEngine.score.getScore());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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



    public PlayerPlane getPlayerPlane() {
        return playerPlane;
    }



    public BackgroundManager getBackground() {
        return background;
    }



    public CollisionManager getCollisionManager() {
        return collisionManager;
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



    public Score getScore() {
        return score;
    }


    public FontGenerator getFontGenerator() {
        return fontGenerator;
    }

}
