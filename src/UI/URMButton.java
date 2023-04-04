package UI;

import Utils.ImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static Utils.Constants.UIData.URMButtons.*;

public class URMButton extends Button {
    private BufferedImage[] imgs;
    private static BufferedImage privateImg;
    public URMButton(int x, int y, int width, int height, int rowIndex, String path) {
		super(x, y, width, height, "");
        if(privateImg == null)
        {
            privateImg = ImageLoader.GetSpriteAtlas(path);
        }
		loadImg(rowIndex);
	}
    private void loadImg(int rowIndex) {


        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = privateImg.getSubimage(i * Utils.Constants.UIData.URMButtons.URM_SIZE_DEFAULT, rowIndex * Utils.Constants.UIData.URMButtons.URM_SIZE_DEFAULT, Utils.Constants.UIData.URMButtons.URM_SIZE_DEFAULT,
                    Utils.Constants.UIData.URMButtons.URM_SIZE_DEFAULT);
    }
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }
}
