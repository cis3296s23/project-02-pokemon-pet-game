package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;

public class main {

    public static void main(String[] args) {
        // Set up JFrame object
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        ImageIcon windowIcon = new ImageIcon("main/windowIcon.png");
        window.setIconImage(windowIcon.getImage());

        window.setTitle("Pokemon Pet Game");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Create our gamePanel (basically the main GUI) and add it to our JFrame
        gamePanel gamePanel = new gamePanel();
        window.add(gamePanel);
        window.pack(); // Sizes the window so all contents are at the prferred size

        gamePanel.startGameThread();
    }
}