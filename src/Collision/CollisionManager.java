package Collision;

import Main.GameEngine;
import Objects.*;
import Utils.AudioPlayer;
import Utils.Constants;
import Utils.FontGenerator;

import java.util.ArrayList;
import java.awt.*;

public class CollisionManager {
    private AudioPlayer audioPlayer = new AudioPlayer();
    private PlayerPlane playerPlane;
    private ArrayList<Laser> laserShoot;
    private ArrayList<LaserEnemy> laserEnemies;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;
    private FontGenerator fontGenerator = new FontGenerator();


    private boolean renderMinusSignPlayer = false;

    public CollisionManager(PlayerPlane playerPlane, ArrayList<Laser> laserShoot, EnemyHelicopter enemyHelicopter, EnemyUFO enemyUFO, ArrayList<LaserEnemy> laserEnemies)
    {
        this.playerPlane = playerPlane;
        this.laserShoot = laserShoot;
        this.enemyHelicopter = enemyHelicopter;
        this.enemyUFO = enemyUFO;
        this.laserEnemies = laserEnemies;
    }

    public void updateCollisionDetection()
    {
        for (int i = 0; i < laserShoot.size(); i++) {
            if(enemyHelicopter.getHitBox().intersects(laserShoot.get(i).getHitBox()))
            {
                enemyHelicopter.destroyObjectFromScreen();
                audioPlayer.playHitSound();
                laserShoot.get(i).resetPos(-100, -100);
                enemyHelicopter.setHitting(true);
            }
            if(enemyUFO.getHitBox().intersects(laserShoot.get(i).getHitBox()))
            {
                audioPlayer.playHitSound();
                enemyUFO.destroyObjectFromScreen();
                laserShoot.get(i).resetPos(-100, -100);
                enemyUFO.setHitting(true);
            }
        }
        for (int i = 0; i < laserEnemies.size(); i++) {
            if(playerPlane.getHitBox().intersects(laserEnemies.get(i).getHitBox()))
            {
                audioPlayer.playHitSound();
                playerPlane.reduceHealth(Constants.DamageDealer.PLAYER_REDUCE_LASER);
                laserEnemies.get(i).resetPos(-100, -100);
                renderMinusSignPlayer = true;
            }

        }
        if (playerPlane.getHitBox().intersects(enemyHelicopter.getHitBox())) {

            audioPlayer.playHitSound();
            enemyHelicopter.destroyObjectFromScreen(playerPlane);

        }
        if (playerPlane.getHitBox().intersects(enemyUFO.getHitBox())) {

            audioPlayer.playHitSound();
            enemyUFO.destroyObjectFromScreen(playerPlane);
        }
    }
    int counterPassed = 0;
    float fontSize = 50F;
    public void render(Graphics g){

        if(renderMinusSignPlayer)
        {
            counterPassed++;
            fontGenerator.renderMinus(g, Constants.DamageDealer.PLAYER_REDUCE_LASER, (int)fontSize, playerPlane.getPosX(), playerPlane.getPosY());
            if(counterPassed % 5 == 0)
            {
                counterPassed=0;
                fontSize-=1;
            }
            if(fontSize <= 20)
            {
                fontSize = 50f;
                renderMinusSignPlayer = false;
            }
        }
    }
}
