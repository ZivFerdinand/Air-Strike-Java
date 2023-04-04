package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static Utils.Constants.UIData.PauseButton.*;

public class PauseButton extends Button {

    public PauseButton(int x, int y, int width, int height, String path) {
        super(x, y, width, height, path);
        loadImg();
    }
    private void loadImg() {
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * Utils.Constants.UIData.PauseButton.PAUSE_SIZE_DEFAULT, 0, Utils.Constants.UIData.PauseButton.PAUSE_SIZE_DEFAULT,
                    Utils.Constants.UIData.PauseButton.PAUSE_SIZE_DEFAULT);
    }
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, PAUSE_SIZE, PAUSE_SIZE, null);
    }    
}
