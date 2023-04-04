package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class playerUI extends JPanel {

    private int health = 100;
    private int hunger = 100;
    private int happiness = 100;

    private final int statuBarWidth = 200;
    private final int statuBarHeight = 20;

    private int healthBarX = 50;
    private int healthBarY = 310;
    private int hungerBarX = 50;
    private int hungerBarY = 340;
    private int happinessBarX = 50;
    private int happinessBarY = 370;

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

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw health bar
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
        g2.setColor(Color.GRAY);
        g2.fillRect(hungerBarX, hungerBarY, statuBarWidth, statuBarHeight);
        g2.setColor(Color.BLACK);
        String hungerLabel = "Hunger: " + hunger;
        FontMetrics hungerfm = g2.getFontMetrics();
        int hungerlabelWidth = hungerfm.stringWidth(hungerLabel);
        int hungerlabelHeight = hungerfm.getHeight();
        int hungerlabelX = hungerBarX + (statuBarWidth - hungerlabelWidth) / 2;
        int hungerlabelY = hungerBarY + (statuBarHeight - hungerlabelHeight) / 2 + hungerfm.getAscent();
        g2.drawString(hungerLabel, hungerlabelX, hungerlabelY);

        // Draw Happiness Bar
        g2.setColor(Color.PINK);
        g2.fillRect(happinessBarX, happinessBarY, statuBarWidth, statuBarHeight);
        g2.setColor(Color.BLACK);
        String happinessLabel = "Happiness: " + happiness;
        FontMetrics happinessfm = g2.getFontMetrics();
        int happinesslabelWidth = happinessfm.stringWidth(happinessLabel);
        int happinesslabelHeight = happinessfm.getHeight();
        int happinesslabelX = happinessBarX + (statuBarWidth - happinesslabelWidth) / 2;
        int happinesslabelY = happinessBarY + (statuBarHeight - happinesslabelHeight) / 2 + hungerfm.getAscent();
        g2.drawString(happinessLabel, happinesslabelX, happinesslabelY);
    }
}