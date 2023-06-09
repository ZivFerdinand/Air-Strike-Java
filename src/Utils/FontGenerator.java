package Utils;

import java.awt.*;
import java.io.*;

import GameStates.Playing;

public class FontGenerator {
    private static Font mainFont;

    float tempFontSize = 40;
    float change = -.3f;
    int opacity = 255;
    int opacityChange = -1;

    public FontGenerator() {
        if(mainFont == null)
            importFont();
    }

    private void importFont() {
        try {
            InputStream is = getClass().getResourceAsStream("../res/font/mainFont.ttf");
            assert is != null;
            mainFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setFontSizeColor(Graphics g, float fontSize, Color color) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
        g.setColor(color);
    }

    private void setFontSizeColor(Graphics g, int a) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, (float) 18.5));
        g.setColor(new Color(255, 255, 255, a));
    }

    private void blinkingText() {
        opacity += opacityChange;
        if (opacity == 125 || opacity == 255) {
            opacityChange *= -1;
        }
    }

    private void healthStatusUpdate(Graphics g, int health, float fontSize) {
        if (health <= 30) {
            g.setColor(Color.YELLOW);

            if (tempFontSize <= 35) {
                change *= -1;
            } else if (tempFontSize >= 45) {
                change *= -1;
            }
            tempFontSize += change;
            fontSize = tempFontSize;
        }

        if (health <= 15) {
            g.setColor(Color.RED);
        }
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));
    }

    public void drawExclamationMark(Graphics g, int x) {
        g.setFont(mainFont);

        setFontSizeColor(g, 60, Color.RED);
        g.drawString("!!", x + 30, 50);
    }

    public void drawHealthAndScore(Graphics g, float fontSize, int health, int score) {
        g.setFont(mainFont);

        setFontSizeColor(g, fontSize, Color.WHITE);
        g.drawString(String.format("%08d", score), 510, 100);

        setFontSizeColor(g, fontSize, Color.WHITE);
        healthStatusUpdate(g, health, fontSize);
        g.drawString("" + health + "%", 140, 100);

        setFontSizeColor(g, opacity);
        blinkingText();
        g.drawString("'ESC' -> PAUSE", 55, 45);
    }
    public void drawInstruction(Graphics g) {
        g.setFont(mainFont);

        setFontSizeColor(g, opacity);
        blinkingText();
        g.drawString("PRESS ENTER OR CLICK ANYWHERE TO START", 360, 700);
    }

    public void drawScoreGained(Graphics g, int score, int fontSize, int x, int y) {
        g.setFont(mainFont);

        setFontSizeColor(g, fontSize, Color.YELLOW);
        g.drawString("+" + score + "PTS", x, y);
    }

    public void drawPlayerDamaged(Graphics g, int score, int fontSize, int x, int y) {
        g.setFont(mainFont);

        setFontSizeColor(g, fontSize, Color.RED);
        g.drawString("-" + score, x, y);
    }

    public void drawPointsTaken(Graphics g, int fontSize, int x, int y) {
        g.setFont(mainFont);

        setFontSizeColor(g, fontSize, Color.ORANGE);
        g.drawString("+" + 5 + "c", x, y);
    }

    public void drawScoreOnDeath(Graphics g, int fontSize, int x, int y) {
        g.setFont(mainFont);

        setFontSizeColor(g, fontSize, Color.YELLOW);
        g.drawString("YOUR SCORE", x, y);
        g.drawString("> " + Playing.score.getScore(), x, y + 30);
    }
}
