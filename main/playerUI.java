package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

public class playerUI extends JPanel implements shopActions{
    int health = 100;
    int hunger = 100;
    int happiness = 100;
    private int healthDecreaseCounter = 0;
    private gamePanel gamePanel;
    private JButton shopButton;
    private JButton miniGameButton;
    private Timer timer;

    public playerUI(gamePanel gamePanel) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.gamePanel = gamePanel;

        // Create a new panel for holding the buttons
        JPanel buttonPanel = new JPanel();
        int hGap = 20; // Horizontal gap between components
        int vGap = 10; // Vertical gap between components
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, hGap, vGap));
        buttonPanel.setOpaque(false);

        // Create a new shop button
        shopButton = new JButton();
        shopButton.setPreferredSize(new Dimension(50, 50));
        shopButton.setBorderPainted(false);
        shopButton.setContentAreaFilled(false);
        shopButton.setFocusPainted(false);
        shopButton.setOpaque(false);

        // Set the shop icon
        URL location = getClass().getResource("/res/shop.png");
        ImageIcon shopIcon = new ImageIcon(location);
        Image scaledImage = shopIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        shopIcon = new ImageIcon(scaledImage);
        shopButton.setIcon(shopIcon);

        buttonPanel.add(shopButton);

        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shop shopWindow = new shop(gamePanel, playerUI.this);
                shopWindow.setVisible(true);
            }
        });

        // Initialize mini-game icon button and open mini-game window on click
        miniGameButton = new JButton();
        miniGameButton.setPreferredSize(new Dimension(50, 50));
        miniGameButton.setBorderPainted(false);
        miniGameButton.setContentAreaFilled(false);
        miniGameButton.setFocusPainted(false);
        miniGameButton.setOpaque(false);

        // Set the snake game icon
        URL location1 = getClass().getResource("/res/snake.png");
        ImageIcon snakeGameIcon = new ImageIcon(location1);
        Image scaledImage1 = snakeGameIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        snakeGameIcon = new ImageIcon(scaledImage1);
        miniGameButton.setIcon(snakeGameIcon);

        buttonPanel.add(miniGameButton);

        miniGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minigame snakeGame = new minigame(gamePanel);
                snakeGame.setVisible(true);

            }
        });

        // Add the button panel to the main panel
        this.add(buttonPanel, BorderLayout.NORTH);

        // Initialize the timer
        int initialDelay = 1000; // 1000 milliseconds = 1 second
        int delayBetweenCalls = 1000; // Decrease the bars every second
        timer = new Timer(initialDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseBars();
            }
        });
        timer.setDelay(delayBetweenCalls);
        timer.start();
    }

    private void decreaseBars() {
        int healthDecrease = 1; // Adjust this value to change the rate of health decrease
        int hungerDecrease = 1; // Adjust this value to change the rate of hunger decrease
        int happinessDecrease = 1; // Adjust this value to change the rate of happiness decrease

        updateHappiness(Math.max(happiness - happinessDecrease, 0));
        updateHunger(Math.max(hunger - hungerDecrease, 0));

        // Update the happinessDecreaseCounter
        // happinessDecreaseCounter++;
        healthDecreaseCounter++;

        // Decrease health by 1% after hunger or happiness become 0
        if (this.hunger == 0 || this.happiness == 0) {
            updateHealth(Math.max(health - (2 * healthDecrease), 0));
        }
        else if (healthDecreaseCounter % 5 == 0){
            updateHealth(Math.max(health - healthDecrease, 0));
        }
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
    public void increaseHealth(int value) {
        updateHealth(health + value);
    }

    @Override
    public void increaseHappiness(int value) {
        updateHappiness(happiness + value);
    }

    @Override
    public void increaseHunger(int value) {
        updateHunger(hunger + value);
    }
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
        g2.fillRoundRect(healthBarX, healthBarY, (int) (health * (statusBarWidth / 100.0)), statusBarHeight,
                cornerRadius, cornerRadius);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(healthBarX, healthBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);

        // Draw hunger bar
        int hungerBarX = 20;
        int hungerBarY = 340;
        GradientPaint hungerGradient = new GradientPaint(0, hungerBarY, Color.ORANGE, 0, hungerBarY + statusBarHeight,
                Color.GRAY);

        g2.setPaint(hungerGradient);
        g2.fillRoundRect(hungerBarX, hungerBarY, (int) (hunger * (statusBarWidth / 100.0)), statusBarHeight,
                cornerRadius, cornerRadius);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(hungerBarX, hungerBarY, statusBarWidth, statusBarHeight, cornerRadius, cornerRadius);

        // Draw happiness bar
        int happinessBarX = 20;
        int happinessBarY = 370;
        GradientPaint happinessGradient = new GradientPaint(0, happinessBarY, Color.GREEN, 0,
                happinessBarY + statusBarHeight, Color.GRAY);

        g2.setPaint(happinessGradient);
        g2.fillRoundRect(happinessBarX, happinessBarY, (int) (happiness * (statusBarWidth / 100.0)), statusBarHeight,
                cornerRadius, cornerRadius);
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
