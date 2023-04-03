package Interfaces;

import Objects.PlayerPlane;

public interface IEnemy {
    void setHitting(boolean isHitting);
    void destroyObjectFromScreen();
    void destroyObjectFromScreen(PlayerPlane playerPlane);
    void healthReset();
}
