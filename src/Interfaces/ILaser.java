package Interfaces;

public interface ILaser {
    boolean checkHasMoved();

    void resetPos(int posX, int posY);

    void laserDisplayNone();
}
