//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class gamePanel extends JPanel implements Runnable {
    final int orignalTileSize = 16;
    final int scale = 4;
    final int tileSize = 64;
    final int maxScreenCol = 16;
    final int maxScreenRow = 9;
    final int screenWidth = 1024;
    final int screenHeight = 576;
    keyHandler kh = new keyHandler();
    Color sky = new Color(28, 235, 235);
    Thread gameThread;
    int FPS = 60;
    int petX = 512;
    int petY = 360;
    int petSpeed = 10;
    int barWidth = 200;
    int barHeight = 30;
    int barSpacing = 10;
    int barX = 20;

    public gamePanel() {
        this.setPreferredSize(new DimensionUIResource(1024, 576));
        this.setBackground(this.sky);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.kh);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {
        double TIME_STEP = 1.0 / (double)this.FPS;
        double accumulatedTime = 0.0;
        long currentTime = System.nanoTime();
        long lastTime = System.nanoTime();

        while(this.gameThread != null) {
            currentTime = System.nanoTime();
            double elapsedTime = (double)(currentTime - lastTime) / 1.0E9;
            lastTime = currentTime;

            for(accumulatedTime += elapsedTime; accumulatedTime >= TIME_STEP; accumulatedTime -= TIME_STEP) {
                this.update(TIME_STEP);
            }

            this.repaint();
        }

    }

    public void update(double timeStep) {
        if (this.kh.leftPressed) {
            this.petX -= this.petSpeed;
        }

        if (this.kh.rightPressed) {
            this.petX += this.petSpeed;
        }

        if (this.kh.jumpPressed) {
        }

        if (this.kh.speakPressed) {
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.sky);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(new Color(16, 199, 59));
        g2.fillRect(0, 400, 1024, 300);
        int barWidth = 200;
        int barHeight = 25;
        int barX = 10;
        int borderRadius = 15;
        int happinessBarY = 350;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(barX, happinessBarY, barWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.CYAN);
        int happinessBarWidth = (int)((double)barWidth * 0.6);
        g2.fillRoundRect(barX, happinessBarY, happinessBarWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.BLACK);
        g2.drawString("Happiness", barX + 10, happinessBarY + 20);
        int hungerBarY = 380;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(barX, hungerBarY, barWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.ORANGE);
        int hungerBarWidth = (int)((double)barWidth * 0.4);
        g2.fillRoundRect(barX, hungerBarY, hungerBarWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.BLACK);
        g2.drawString("Hunger", barX + 10, hungerBarY + 20);
        int healthBar = 410;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(barX, healthBar, barWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.RED);
        int healthBarWidth = (int)((double)barWidth * 0.8);
        g2.fillRoundRect(barX, healthBar, healthBarWidth, barHeight, borderRadius, borderRadius);
        g2.setColor(Color.BLACK);
        g2.drawString("Health", barX + 10, healthBar + 20);
        ImageIcon ii = new ImageIcon("main/windowIcon.png");
        Image eevee = ii.getImage();
        g2.drawImage(eevee, this.petX, this.petY, 64, 64, (ImageObserver)null);
        g2.dispose();
    }
}
