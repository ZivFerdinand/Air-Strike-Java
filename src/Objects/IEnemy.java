package Objects;

public interface IEnemy {
    public void setHitting(boolean isHitting);
    public void destroyObjectFromScreen();
    public void destroyObjectFromScreen(PlayerPlane playerPlane);
    public void healthReset();
}
