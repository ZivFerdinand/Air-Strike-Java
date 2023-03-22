package Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    public GameFrame()
    {
        gamePanel = new GamePanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Air Strike");

        add(gamePanel);
        gamePanel.requestFocus();

        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setVisible(true);

    }
}