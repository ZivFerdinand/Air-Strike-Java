package Collision;

import Objects.EnemyHelicopter;
import Objects.EnemyUFO;
import Objects.Laser;
import Objects.PlayerPlane;

import java.util.ArrayList;
import java.awt.*;

public class CollisionManager {
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
                laserShoot.get(i).resetPos(-100, -100);
            }
            if(enemyUFO.getHitBox().intersects(laserShoot.get(i).getHitBox()))
            {
                enemyUFO.destroyObjectFromScreen();
                laserShoot.get(i).resetPos(-100, -100);
            }
        }
        if (playerPlane.getHitBox().intersects(enemyHelicopter.getHitBox())) {
            enemyHelicopter.destroyObjectFromScreen();
        }
        if (playerPlane.getHitBox().intersects(enemyUFO.getHitBox())) {
            enemyUFO.destroyObjectFromScreen();
        }
    }
}
