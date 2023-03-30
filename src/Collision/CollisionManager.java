package Collision;

import Main.GameEngine;
import Objects.EnemyHelicopter;
import Objects.EnemyUFO;
import Objects.Laser;
import Objects.PlayerPlane;
import Utils.AudioPlayer;

import java.util.ArrayList;
import java.awt.*;

public class CollisionManager {
    private AudioPlayer audioPlayer = new AudioPlayer();
    private PlayerPlane playerPlane;
    private ArrayList<Laser> laserShoot;
    private EnemyHelicopter enemyHelicopter;
    private EnemyUFO enemyUFO;


    // private boolean isHitting = false;
    // private int posHittingX = 0, posHittingY = 0;

    public CollisionManager(PlayerPlane playerPlane, ArrayList<Laser> laserShoot, EnemyHelicopter enemyHelicopter, EnemyUFO enemyUFO)
    {
        this.playerPlane = playerPlane;
        this.laserShoot = laserShoot;
        this.enemyHelicopter = enemyHelicopter;
        this.enemyUFO = enemyUFO;
    }

    public void updateCollisionDetection()
    {
        for (int i = 0; i < laserShoot.size(); i++) {
            if(enemyHelicopter.getHitBox().intersects(laserShoot.get(i).getHitBox()))
            {
                enemyHelicopter.destroyObjectFromScreen();

                // posHittingX = (int) laserShoot.get(i).getPosX();
                // posHittingY = (int) laserShoot.get(i).getPosY();
                // isHitting = true;
                audioPlayer.playHitSound(0);
                laserShoot.get(i).resetPos(-100, -100);
                enemyHelicopter.setHitting(true);
            }
            if(enemyUFO.getHitBox().intersects(laserShoot.get(i).getHitBox()))
            {
                audioPlayer.playHitSound(0);
                enemyUFO.destroyObjectFromScreen();
                laserShoot.get(i).resetPos(-100, -100);
                enemyUFO.setHitting(true);
            }
        }
        if (playerPlane.getHitBox().intersects(enemyHelicopter.getHitBox())) {

            audioPlayer.playHitSound(0);
            enemyHelicopter.destroyObjectFromScreen(playerPlane);

        }
        if (playerPlane.getHitBox().intersects(enemyUFO.getHitBox())) {

            audioPlayer.playHitSound(0);
            enemyUFO.destroyObjectFromScreen(playerPlane);
        }
    }
}
