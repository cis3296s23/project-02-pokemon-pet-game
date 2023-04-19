
package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class playerUI extends JPanel {
    private int health = 100;
    private int hunger = 100;
    private int happiness = 100;

    private gamePanel gamePanel;
    private JButton shopButton;
    private JButton miniGame;

    private JLabel currencyLabel;

    public playerUI(gamePanel gamePanel) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.gamePanel = gamePanel;

        // // Initialize shop button and open shop window on click
        // shopButton = new JButton();
        // shopButton.setPreferredSize(new Dimension(50, 50));
        // shopButton.setBorderPainted(false);
        // shopButton.setContentAreaFilled(false);
        // shopButton.setFocusPainted(false);
        // shopButton.setOpaque(true);

        // /* 
        // Set the shop icon
        // URL location = getClass().getResource("/images/shop.png");
        // ImageIcon shopIcon = new ImageIcon(location);
        // Image scaledImage = shopIcon.getImage().getScaledInstance(50, 50,
        // Image.SCALE_SMOOTH);
        // shopIcon = new ImageIcon(scaledImage);
        // shopButton.setIcon(shopIcon); 
        // */ 

        // this.add(shopButton, BorderLayout.NORTH);

        // shopButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         shop shopWindow = new shop(gamePanel);
        //         shopWindow.setVisible(true);
        //     }
        // });

        // Initialize minigame button and open shop window on click
        miniGame = new JButton();
        miniGame.setPreferredSize(new Dimension(50, 50));
        miniGame.setBorderPainted(true);
        miniGame.setContentAreaFilled(true);
        miniGame.setFocusPainted(true);
        miniGame.setOpaque(true);

        this.add(miniGame, BorderLayout.NORTH);

        miniGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new minigame();
                System.out.println("Jeff");
            }
        });
    }

    public void updateCurrencyLabel() {
        currencyLabel.setText("Money: " + currency.balance);
        repaint();
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
        int statusBarWidth = 200;
        int statusBarHeight = 20;
        int cornerRadius = 5;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw health bar
        int healthBarX = 20;
        int healthBarY = 310;
        GradientPaint healthGradient = new GradientPaint(0, healthBarY, Color.RED, 0, healthBarY + statusBarHeight,
                Color.RED);

        g2.setPaint(healthGradient);
        g2.fillRoundRect(healthBarX, healthBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(healthBarX, healthBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);

        // Draw hunger bar
        int hungerBarX = 20;
        int hungerBarY = 340;
        GradientPaint hungerGradient = new GradientPaint(0, hungerBarY, Color.ORANGE, 0, hungerBarY + statusBarHeight,
                Color.GRAY);

        g2.setPaint(hungerGradient);
        g2.fillRoundRect(hungerBarX, hungerBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(hungerBarX, hungerBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);

        // Draw happiness bar
        int happinessBarX = 20;
        int happinessBarY = 370;
        GradientPaint happinessGradient = new GradientPaint(0, happinessBarY, Color.CYAN, 0,
                happinessBarY + statusBarHeight, Color.GRAY);

        g2.setPaint(happinessGradient);
        g2.fillRoundRect(happinessBarX, happinessBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(happinessBarX, happinessBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);

        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int labelHeight = fm.getHeight();

        // Draw health label
        String healthLabel = "Health: " + health;
        int labelWidth = fm.stringWidth(healthLabel);
        int labelX = healthBarX + (statusBarWidth - labelWidth) / 2;
        int labelY = healthBarY + (statusBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(healthLabel, labelX, labelY);

        // Draw hunger label
        String hungerLabel = "Hunger: " + hunger;
        labelWidth = fm.stringWidth(hungerLabel);
        labelX = hungerBarX + (statusBarWidth - labelWidth) / 2;
        labelY = hungerBarY + (statusBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(hungerLabel, labelX, labelY);

        // Draw happiness label
        String happinessLabel = "Happiness: " + happiness;
        labelWidth = fm.stringWidth(happinessLabel);
        labelX = happinessBarX + (statusBarWidth - labelWidth) / 2;
        labelY = happinessBarY + (statusBarHeight - labelHeight) / 2 + fm.getAscent();
        g2.drawString(happinessLabel, labelX, labelY);
    }

}
