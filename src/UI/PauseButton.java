package UI;

import java.awt.Graphics;
import static Utils.Constants.UIData.PauseButton.*;

public class PauseButton extends Button {

    public PauseButton(int x, int y, int width, int height, String path) {
        super(x, y, width, height, path);
        loadImgs();
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, PAUSE_SIZE, PAUSE_SIZE, null);
    }    
}
