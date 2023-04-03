package Main;

import javax.swing.*;

import GameStates.GameState;
import static Utils.Constants.Path.*;
import java.awt.event.*;

public class GameFrame {

    public GameFrame(GamePanel gamePanel, GameEngine gameEngine) {

        JFrame jFrame = new JFrame();
        ImageIcon image = new ImageIcon(ICON);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setTitle("Air Strike [JAVIER-TRISTAN-ZIVEN]");
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setIconImage(image.getImage());
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                if (GameState.state == GameState.PLAYING)
                    gameEngine.getPlaying().getPlayerPlane().resetMovement();
            }
        });
    }

}
