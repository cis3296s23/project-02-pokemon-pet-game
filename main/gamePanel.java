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
    playerUI UI = new playerUI();

    public gamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(sky);
        this.setDoubleBuffered(true); // May improve performance
        this.addKeyListener(kh);
        this.add(UI);
        this.setFocusable(true);
    }

    // Game mechanics
    Thread gameThread;

    int FPS = 60;

    int petX = 512;
    int petY = 230;
    int petSpeed = 10;

    int jumpHeight = 10;
    double jumpSpeed = -10;
    int gravity = 15;
    boolean isJumping = false;

    public void startGameThread() {

        this.requestFocus();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Set up game loop to slow game down
        // Set our time step to run in 60 FPS, game should update every .016 seconds
        // instead of every nanosecond
        final double TIME_STEP = 1.0 / FPS;
        double accumulatedTime = 0;
        long currentTime = System.nanoTime();
        long lastTime = System.nanoTime();

        // Game loop
        // Every time we get to our time step update the game and reset accumulated time
        // vairable, ensure game runs smoothly regardless of system
        while (gameThread != null) {
            // Calculate elapsed time since last update
            currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastTime) / 1000000000.0;
            lastTime = currentTime;
            accumulatedTime += elapsedTime;

            // Update game logic and reset accumulatedTime
            while (accumulatedTime >= TIME_STEP) {
                update(TIME_STEP);
                accumulatedTime -= TIME_STEP;
            }

            repaint();
        }
    }

    public void update(double timeStep) {

        // Move to the left
        if (kh.leftPressed == true) {
            petX = petX - petSpeed;
        }
        // Move to the right
        if (kh.rightPressed == true) {
            petX = petX + petSpeed;
        }
        // Jump
        if (kh.jumpPressed == true && !isJumping) {
            // Set isJumping to true so we cant jump multiple times in air
            isJumping = true;
            petY -= jumpHeight;
            jumpSpeed = -10;
        }
        // If jumping fall back down
        if (isJumping) {
            petY += (int) jumpSpeed;
            jumpSpeed += gravity * timeStep;
            // Don't fall past default height and set to false so we can jump again
            if (petY >= 230) {
                petY = 230;
                isJumping = false;
            }
        }
        // Make Noise
        if (kh.speakPressed == true) {
            // make noise
        }

        if (petX > 1024) {
            petX = -200;
        }
        if (petX < -200) {
            petX = 1024;
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
        g2.drawImage(eevee, petX, petY, tileSize * 4, tileSize * 4, null);

        // Draw UI
        UI.paint(g2);

        g2.dispose();
    }

}
