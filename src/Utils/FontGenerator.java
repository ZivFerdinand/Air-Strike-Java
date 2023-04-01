package Utils;

import java.awt.*;
import java.io.*;

public class FontGenerator {
    Font mainFont;
    public FontGenerator()
    {
        importFont();
    }
    private void importFont()
    {
        try {

            InputStream is = getClass().getResourceAsStream("/res/font/mainFont.ttf");
            mainFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    float tempFontSize = 40;
    float change = -.3f;
    public void drawExclamationMark(Graphics g, int x)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 60));
        g.setColor(Color.RED);

        g.drawString("!", x + 50, 50);
    }
    public void chooseMap(Graphics g, int fontSize)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(Color.BLACK);

        g.drawString("CHOOSE MAP", 480, 68);
    }
    public void render(Graphics g, float fontSize, int health, int score)
    {
        g.setFont(mainFont);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.drawString(String.format("%08d", score), 510, 100);
        if(health <= 30)
        {

            g.setColor(Color.YELLOW);

            if(tempFontSize <= 35)
            {
                change*=-1;
            }
            else if(tempFontSize >= 45)
            {
                change*=-1;
            }
            tempFontSize += change;
            fontSize = tempFontSize;

        }
        if(health <= 15)
        {
            g.setColor(Color.RED);
        }
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.drawString("" + health + "%", 140, 100);

    }
    public void render(Graphics g, int score, int fontSize, int x, int y)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(Color.YELLOW);

        g.drawString("+" + score + "PTS", x, y);
    }
    public void renderMinus(Graphics g, int score, int fontSize, int x, int y)
    {
        g.setFont(mainFont);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(Color.RED);

        g.drawString("-" + score, x, y);
    }
}
