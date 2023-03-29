package Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class EnemyPlane extends Object{

    private final int enemySpeed = 4;
    private BufferedImage img;
    public EnemyPlane(float posX, float posY) {
        super(posX, posY, 0, 0, 50, 50);
        importImg();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Plane-Yellow.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img = img.getSubimage(400, 400, 200, 200);
    }
}
