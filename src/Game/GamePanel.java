package Game;

import InputListener.KeyboardListener;
import InputListener.MouseListener;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int xDelta, yDelta;
    MouseListener mouseListener;
    public GamePanel()
    {
        mouseListener = new MouseListener(this);
        setSize();

        addKeyListener(new KeyboardListener(this));
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    private void setSize()
    {
        Dimension d = new Dimension(1280, 800);
        setMinimumSize(d);
        setPreferredSize(d);
        setMaximumSize(d);
    }
    public void addPosition(int x, int y)
    {
        xDelta+=x;
        yDelta+=y;
    }
    public void changePosition(int x, int y)
    {
        xDelta = x;
        yDelta = y;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(0+xDelta,0+yDelta,100,100);
        repaint();
    }
}
