package Main;

import javax.swing.JFrame;

public class GameFrame {
    private JFrame jFrame;

    public GameFrame(GamePanel gamePanel) {

        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);

        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

}
