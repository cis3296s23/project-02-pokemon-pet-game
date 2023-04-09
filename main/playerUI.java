package main;

import java.awt.*;

import javax.swing.JPanel;

public class playerUI extends JPanel {
    private int health = 100;
    private int hunger = 100;
    private int happiness = 100;

    public playerUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setOpaque(false);
    }

    public void updateHealth(int health) {
        this.health = health;
        repaint();
    }

    public void updateHunger(int hunger) {
        this.hunger = hunger;
        repaint();
    }

    public void updateHappiness(int happiness) {
        this.happiness = happiness;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        int statuBarWidth = 200;
        int statuBarHeight = 20;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw health bar
        int healthBarX = 50;
        int healthBarY = 310;

        g2.setColor(Color.RED);
        g2.fillRect(healthBarX, healthBarY, statuBarWidth, statuBarHeight);
        g2.setColor(Color.BLACK);
        String healthLabel = "Health: " + health;
        FontMetrics fm = g2.getFontMetrics();
        int labelWidth = fm.stringWidth(healthLabel);
        int labelHeight = fm.getHeight();
        int labelX = healthBarX + (statuBarWidth - labelWidth) / 2;
        int labelY = healthBarY + (statuBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(healthLabel, labelX, labelY);

        // Draw Hunger Bar
        int hungerBarX = 50;
        int hungerBarY = 340;

        g2.setColor(Color.GRAY);
        g2.fillRect(hungerBarX, hungerBarY, statuBarWidth, statuBarHeight);
        g2.setColor(Color.BLACK);
        String hungerLabel = "Hunger: " + hunger;
        labelWidth = fm.stringWidth(hungerLabel);
        labelHeight = fm.getHeight();
        labelX = hungerBarX + (statuBarWidth - labelWidth) / 2;
        labelY = hungerBarY + (statuBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(hungerLabel, labelX, labelY);

        // Draw Happiness Bar
        int happinessBarX = 50;
        int happinessBarY = 370;

        g2.setColor(Color.PINK);
        g2.fillRect(happinessBarX, happinessBarY, statuBarWidth, statuBarHeight);
        g2.setColor(Color.BLACK);
        String happinessLabel = "Happiness: " + happiness;
        labelWidth = fm.stringWidth(happinessLabel);
        labelHeight = fm.getHeight();
        labelX = happinessBarX + (statuBarWidth - labelWidth) / 2;
        labelY = happinessBarY + (statuBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(happinessLabel, labelX, labelY);

    }
    public void drawStatusBar(Graphics2D g2, int x, int y, int width, int height, double percentage, Color barColor) {
        int statusBarWidth = (int) (width * percentage);

        // Draw background
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, width, height);

        // Draw status bar
        g2.setColor(barColor);
        g2.fillRect(x, y, statusBarWidth, height);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);
    }
}
