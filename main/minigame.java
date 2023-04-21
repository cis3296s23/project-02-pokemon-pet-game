package main;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class minigame extends JFrame{
    private gamePanel gamePanel;

    minigame(gamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.add(new snakePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });
    }
}