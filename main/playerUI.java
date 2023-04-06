package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.shop;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class playerUI extends JPanel {
    private int health = 100;
    private int hunger = 100;
    private int happiness = 100;

    private JButton shopButton;

    public playerUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        // Initialize shop button and open shop window on click
        shopButton = new JButton("Shop");
        shopButton.setPreferredSize(new Dimension(10, 50));
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("My name Jeff");
                shop shopWindow = new shop();
                shopWindow.setVisible(true);
            }
        });

        this.add(shopButton, BorderLayout.NORTH);
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

        // Draw Happiness Bar
        int happinessBarX = 50;
        int happinessBarY = 370;

        g2.setColor(Color.PINK);
        g2.fillRect(happinessBarX, happinessBarY, statuBarWidth, statuBarHeight);
    }
}