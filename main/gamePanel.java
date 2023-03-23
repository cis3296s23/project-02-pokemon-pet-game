package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class gamePanel extends JPanel implements Runnable {

    // Gamepanel/window configuration
    final int orignalTileSize = 16;
    final int scale = 4;

    final int tileSize = orignalTileSize * scale; // 64 x 64 per tile
    // 16:9 Width to height
    final int maxScreenCol = 16;
    final int maxScreenRow = 9;
    // 1024 x 576
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public gamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true); // May improve performance
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    // Game mechanics
    Thread gameThread;
    keyHandler kh = new keyHandler();

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {

            // Update
            update();

            // Draw
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }

}
