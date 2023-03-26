package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
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

    keyHandler kh = new keyHandler();
    Color sky = new Color(28, 235, 235);

    public gamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(sky);
        this.setDoubleBuffered(true); // May improve performance
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    // Game mechanics
    Thread gameThread;

    int FPS = 60;

    int petX = 512;
    int petY = 400;
    int petSpeed = 10;

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Need to slow down the game
        // Divide 1 second by our FPS (60) to get .016 seconds
        // Draw every .016 seconds instead of every nanosecond
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            // Update
            update();

            // Draw
            repaint();

            // Need to slow down the game so we draw and update every .016 seconds instead
            // of every nanosecond
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                // If update and draw take more time no need to put thread to sleep
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void update() {

        // Add a 20 ms delay to slow the game down
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Move to the left
        if (kh.leftPressed == true) {
            petX = petX - petSpeed;
        }
        // Move to the right
        if (kh.rightPressed == true) {
            petX = petX + petSpeed;
        }
        // Jump
        if (kh.jumpPressed == true) {
            // Add jump functionality
        }
        // Make Noise
        if (kh.speakPressed == true) {
            // make noise
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Grass
        g2.setColor(new Color(16, 199, 59));
        g2.fillRect(0, 400, 1024, 300);

        // Draw eevee
        ImageIcon ii = new ImageIcon("main/windowIcon.png");
        Image eevee = ii.getImage();
        g2.drawImage(eevee, petX, petY, tileSize, tileSize, null);

        g2.dispose();
    }

}
