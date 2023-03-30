package Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontGenerator {
    Font mainFont;
    public FontGenerator()
    {
        importFont();
    }
    private void importFont()
    {
        try {

            InputStream is = getClass().getResourceAsStream("/res/mainFont.ttf");
            mainFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void render(Graphics g, float fontSize, int health, int score)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(Color.WHITE);

        g.drawString("" + health, 140, 100);
        g.drawString(String.format("%08d", score), 510, 100);
    }
    public void render(Graphics g, int score, int fontSize, int x, int y)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(Color.YELLOW);

        g.drawString("+" + score, x, y);
    }

}
