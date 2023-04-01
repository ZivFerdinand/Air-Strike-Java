package Objects;

import java.awt.*;

import Utils.Constants;
import Utils.Constants.Path;
public class LaserPlane extends Object implements ILaser, IGameStandard{
    private final int laserSpeed = 4;
    private final int initPosX, initPosY;
    private int totalMvmt = 0;

    public LaserPlane(int posX, int posY) {
        super(posX, posY,70, 0, 10, 38, Path.LASER, Constants.ObjectSizeData.PLAYER_LASER);
        this.initPosX = posX;
        this.initPosY = posY;
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
    public int getTotalMvmt() {
        return totalMvmt;
    }

    public void setTotalMvmt(int totalMvmt) {
        this.totalMvmt = totalMvmt;
    }

    public void render(Graphics g) {
        updatePosition();
        g.drawImage(img, (int) posX + 65, (int) posY, imageWidth, imageHeight, null);
    }
private void updatePosition()
{

    posY -= laserSpeed;
    totalMvmt += laserSpeed;
}
    public int getPosY() {
        return (int)posY;
    }
    
    public int getPosX() {
        return (int)posX;
    }
}
