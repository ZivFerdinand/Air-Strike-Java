package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import GameStates.Playing;
import Interfaces.*;
import Utils.ImageLoader;

import static Utils.Constants.Path.*;
import static Utils.Constants.ObjectSizeData.*;
public class LaserPlane extends Object implements ILaser, IGameStandard{
    private static BufferedImage privateImg;
    private final int initPosX, initPosY;
    private int totalMvmt = 0;

    public LaserPlane(int posX, int posY) {
        super(posX, posY,70, 0, 10, 38, "", PLAYER_LASER, 4);
        this.initPosX = posX;
        this.initPosY = posY;

        if(privateImg == null)
           privateImg = importPrivateImg(LASER);
    }



    public boolean checkHasMoved() {
        return posX != initPosX || posY != initPosY;
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
        if(!Playing.isPaused() && !Playing.isGameOver())
            updatePosition();
        g.drawImage(privateImg, (int) posX + 65, (int) posY, imageWidth, imageHeight, null);
    }
    private void updatePosition()
    {

        posY -= speed;
        totalMvmt += speed;
    }
    public int getPosY() {
        return (int)posY;
    }
    
    public int getPosX() {
        return (int)posX;
    }
}
