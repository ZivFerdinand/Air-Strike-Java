package Utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Assist {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static BufferedImage rotate(BufferedImage img, int degree) {

        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(degree), width / 2,
                height / 2);
        g2.drawImage(img, null, 0, 0);

        return newImage;
    }
}
