package main;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        ImageIcon windowIcon = new ImageIcon("res/windowIcon.png");
        window.setIconImage(windowIcon.getImage());
        window.setTitle("Pokemon Pet Game");
        window.setLocationRelativeTo((Component)null);
        window.setVisible(true);
        gamePanel gamePanel = new gamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();

    }
}