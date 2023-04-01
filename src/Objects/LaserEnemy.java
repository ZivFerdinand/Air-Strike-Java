package Objects;

import java.awt.*;

import Utils.Constants;
import Utils.Constants.Path;
public class LaserEnemy extends Object implements ILaser,IGameStandard {
    private final int laserSpeedX, laserSpeedY;
    private final int initPosX, initPosY;
    private int totalMvmt = 0;

    public LaserEnemy(int posX, int posY, int laserSpeedX, int laserSpeedY) {
        super(posX, posY,85, 85, 31, 32, Path.LASER_ENEMY, Constants.ObjectSizeData.ENEMY_LASER);
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

    public void laserDisplayNone()
    {
        this.posX = this.posY = -100;
    }

    public void render(Graphics g) {
        updatePosition();
        g.drawImage(img, (int) posX + 85, (int) posY+85, imageWidth, imageHeight, null);
    }

    private void updatePosition() {

        posY += laserSpeedY;
        posX += laserSpeedX;
        totalMvmt += 4;
    }

    public int getPosY() {
        return (int)posY;
    }

    public int getPosX() {
        return (int)posX;
    }

    public int getTotalMvmt() {
        return totalMvmt;
    }

    public void setTotalMvmt(int totalMvmt) {
        this.totalMvmt = totalMvmt;
    }
}
