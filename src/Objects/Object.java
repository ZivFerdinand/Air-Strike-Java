package Objects;

import java.awt.*;

public abstract class Object {
    protected float posX, posY;
    protected float hitBoxX, hitBoxY;
    protected int width, height;
    protected Rectangle hitBox;

    public Object(float posX, float posY, float hitBoxX, float hitBoxY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.hitBoxX = hitBoxX;
        this.hitBoxY = hitBoxY;
        this.width = width;
        this.height = height;
        initHitbox();
    }

    protected void drawHitBox(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private void initHitbox() {
        hitBox = new Rectangle((int) hitBoxX, (int) hitBoxY, width, height);
    }

    public void updateHitbox() {
        hitBox.x = (int) posX + (int) hitBoxX;
        hitBox.y = (int) posY + (int) hitBoxY;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
