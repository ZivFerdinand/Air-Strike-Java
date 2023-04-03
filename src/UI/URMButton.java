package UI;

import java.awt.Graphics;
import static Utils.Constants.UIData.URMButtons.*;

public class URMButton extends Button {
    
    public URMButton(int x, int y, int width, int height, int rowIndex, String path) {
		super(x, y, width, height, path);
		loadImgs(URM_SIZE_DEFAULT, rowIndex);
	}

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }
}
