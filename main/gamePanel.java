//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class gamePanel extends JPanel implements Runnable {
    public Thread gameThread;

    public int petX = 512;
    public int petY = 230;
    public int petSpeed = 10;

    public double jumpSpeed = -10;
    public boolean isJumping = false;

    private Image cloud1;
    private Image cloud2;
    private Image cloud3;
    public keyHandler kh = new keyHandler();
    public playerUI UI = new playerUI(this);

    private JLabel currencyLabel;

    public void drawImageCloud(Graphics2D g2, Image cloud, int x, int y, int width, int height) {
        g2.drawImage(cloud, x, y, width, height, null);
    }

    public gamePanel() {
        this.setPreferredSize(new DimensionUIResource(1024, 576));

        Color sky = new Color(28, 235, 235);
        this.setBackground(sky);
        this.setDoubleBuffered(true); // May improve performance
        this.addKeyListener(kh);
        this.add(UI);
        this.setFocusable(true);

        // Load Cloud images
        ImageIcon cloudIcon1 = new ImageIcon("images/cloud_01.png");
        ImageIcon cloudIcon2 = new ImageIcon("images/cloud_02.png");
        ImageIcon cloudIcon3 = new ImageIcon("images/cloud_03.png");
        cloud1 = cloudIcon1.getImage();
        cloud2 = cloudIcon2.getImage();
        cloud3 = cloudIcon3.getImage();
    }

    public void startGameThread() {
        this.requestFocus();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int FPS = 60;

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
                try {
                    update(TIME_STEP);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                accumulatedTime -= TIME_STEP;
            }
            repaint();
        }
    }

    public void update(double timeStep) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int gravity = 15;
        int jumpHeight = 10;

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
            File file = new File("main/jump.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
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
            File file = new File("main/eevee.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
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

        // Draw cloud images
        drawImageCloud(g2, cloud1, 120, 70, 200, 100);
        drawImageCloud(g2, cloud2, 450, 120, 200, 100);
        drawImageCloud(g2, cloud3, 750, 70, 190, 110);
        // Draw eevee
        ImageIcon ii = new ImageIcon("main/windowIcon.png");
        Image eevee = ii.getImage();
        g2.drawImage(eevee, petX, petY, 64 * 4, 64 * 4, null);

        // Draw Currency label
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 26));
        g2.drawString("Money: " + currency.balance, 456, 560);

        // Draw UI
        UI.paint(g2);

        g2.dispose();
    }

}