package Collision;

import Main.GameEngine;
import Objects.*;
import Utils.AudioPlayer;
import Utils.Constants;
import Utils.FontGenerator;

import java.util.ArrayList;

import GameStates.Playing;
import Interfaces.IEnemy;

import java.awt.*;

public class CollisionManager {
    private final AudioPlayer audioPlayer;
    private final PlayerPlane playerPlane;
    private final ArrayList<LaserPlane> laserPlaneShoot;
    private final ArrayList<LaserEnemy> laserEnemies;
    private final EnemyHelicopter enemyHelicopter;
    private final EnemyUFO enemyUFO;
    private final FontGenerator fontGenerator;


    private int counterPassed = 0;
    private float minusSignFontSize = 50F;
    private boolean minusSignDisplay = false;

    public CollisionManager(Playing playing)
    {
        this.playerPlane = playing.getPlayerPlane();
        this.laserPlaneShoot = playing.getPlayerPlane().getLaserShoot();
        this.enemyHelicopter = playing.getEnemyHelicopter();
        this.enemyUFO = playing.getEnemyUFO();
        this.laserEnemies = playing.getEnemyUFO().getLaserShoot();

        this.audioPlayer = new AudioPlayer();
        this.fontGenerator = new FontGenerator();
    }

    public void updateCollisionDetection()
    {
        for (int i = 0; i < laserPlaneShoot.size(); i++) {
            if(enemyHelicopter.getHitBox().intersects(laserPlaneShoot.get(i).getHitBox()))
            {
                playerLaserOnCollision(enemyHelicopter, i);
            }
            if(enemyUFO.getHitBox().intersects(laserPlaneShoot.get(i).getHitBox()))
            {
                playerLaserOnCollision(enemyUFO, i);
            }
        }

        for (int i = 0; i < laserEnemies.size(); i++) {
            if(playerPlane.getHitBox().intersects(laserEnemies.get(i).getHitBox()))
            {
                playerPlaneOnLaserCollision(laserEnemies, i);
            }
        }

        if (playerPlane.getHitBox().intersects(enemyHelicopter.getHitBox())) {
            playerPlaneOnCollision(enemyHelicopter);
        }

        if (playerPlane.getHitBox().intersects(enemyUFO.getHitBox())) {
            playerPlaneOnCollision(enemyUFO);
        }
    }

    public void render(Graphics g){
        playerDamaged(g);
    }

    private void playerLaserOnCollision(IEnemy IEnemyObject, int i)
    {
        audioPlayer.playHitSound();
        laserPlaneShoot.get(i).laserDisplayNone();
        IEnemyObject.destroyObjectFromScreen();
        IEnemyObject.setHitting(true);
    }

    private void playerPlaneOnCollision(IEnemy IEnemyObject)
    {
        audioPlayer.playHitSound();
        IEnemyObject.destroyObjectFromScreen(playerPlane);
    }

    private void playerPlaneOnLaserCollision(ArrayList<LaserEnemy> laserEnemies, int i)
    {
        audioPlayer.playHitSound();
        playerPlane.reduceHealth(Constants.DamageDealer.PLAYER_REDUCE_LASER);
        laserEnemies.get(i).laserDisplayNone();
        minusSignDisplay = true;
    }

    private void playerDamaged(Graphics g)
    {
        if(minusSignDisplay)
        {
            fontGenerator.renderMinus(g, Constants.DamageDealer.PLAYER_REDUCE_LASER, (int) minusSignFontSize, playerPlane.getPosX(), playerPlane.getPosY());
            validateFontDecrease();
        }
    }

    private void validateFontDecrease()
    {
        counterPassed++;
        if(counterPassed % 5 == 0)
        {
            counterPassed=0;
            minusSignFontSize -=1;
        }
        if(minusSignFontSize <= 20)
        {
            minusSignFontSize = 50f;
            minusSignDisplay = false;
        }
    }
}
