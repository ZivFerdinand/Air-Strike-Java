package Objects;

import java.awt.*;

import Utils.Constants.Path;
public class LaserEnemy extends Object{
    private final int laserSpeedX, laserSpeedY;
    private final int initPosX, initPosY;
    private int totalMvmt = 0;

    public LaserEnemy(int posX, int posY, int laserSpeedX, int laserSpeedY) {
        super(posX, posY,85, 85, 31, 32, Path.LASER_ENEMY);
        this.initPosX = posX;
        this.initPosY = posY;
        this.laserSpeedX = laserSpeedX;
        this.laserSpeedY = laserSpeedY;
    }

    public boolean checkHasMoved() {
        if (posX != initPosX || posY != initPosY)
            return true;

        return false;
    }

    public void resetPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getTotalMvmt() {
        return totalMvmt;
    }

    public void setTotalMvmt(int totalMvmt) {
        this.totalMvmt = totalMvmt;
    }

    public void render(Graphics g) {
        drawHitBox(g);
        posY += laserSpeedY;
        posX +=laserSpeedX;
        totalMvmt += 4;
        g.drawImage(img, (int) posX + 85, (int) posY+85, 31, 32, null);
    }

    public int getPosY() {
        return (int)posY;
    }

    public int getPosX() {
        return (int)posX;
    }
}
