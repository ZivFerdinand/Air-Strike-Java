package Objects;

import java.awt.*;
public class Laser extends Object{
    private final int laserSpeed = 4;
    private final int initPosX, initPosY;
    private int totalMvmt = 0;

    public Laser(int posX, int posY) {
        super(posX, posY,70, 0, 10, 38, "/res/sprite/Laser-Sprite.png");
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

    public int getTotalMvmt() {
        return totalMvmt;
    }

    public void setTotalMvmt(int totalMvmt) {
        this.totalMvmt = totalMvmt;
    }

    public void render(Graphics g) {
//        drawHitBox(g);
        posY -= laserSpeed;
        totalMvmt += laserSpeed;
        g.drawImage(img, (int) posX + 65, (int) posY, 20, 38, null);
    }

    public int getPosY() {
        return (int)posY;
    }
    
    public int getPosX() {
        return (int)posX;
    }
}
