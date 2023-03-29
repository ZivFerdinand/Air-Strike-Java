package Collision;

import Objects.Laser;
import Objects.PlayerPlane;

import java.util.ArrayList;
import java.awt.*;

public class CollisionManager {
    private PlayerPlane playerPlane;
    private ArrayList<Laser> laserShoot;
    public CollisionManager(PlayerPlane playerPlane, ArrayList<Laser> laserShoot)
    {
        this.playerPlane = playerPlane;
        this.laserShoot = laserShoot;
    }
    int posX = 300;
    Rectangle r1 = new Rectangle(posX, 300, 150, 150);
    Color x = new Color(255, 0, 0);
    public void updateCollisionDetection()
    {
        r1 = new Rectangle(posX, 300, 150, 150);
        if (r1.intersects(playerPlane.getHitBox())) {
            posX = 100;
        }
        for (int i = 0; i < laserShoot.size(); i++) {
            if (r1.intersects(laserShoot.get(i).getHitBox())) {
                laserShoot.get(i).resetPos(posX, -100);
                x = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
            }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(x);
        g.fillRect(posX, 300, 150, 150);
    }
}
