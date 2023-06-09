package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.*;

public class shop extends JFrame {

    private gamePanel gamePanel;
    private JLabel currencyLabel;

    public shop(gamePanel gamePanel, shopActions shopActions) {
        this.gamePanel = gamePanel;
        this.setTitle("Shop");
        this.setSize(600, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // set the focus back to the gamePanel
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });

        // Main panel uses GridLayout to organize items in categories
        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(new Color(200, 230, 255));
        shopPanel.setLayout(new GridLayout(4, 1, 10, 10));
        shopPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Currency label
        currencyLabel = new JLabel("Money: $" + currency.balance);
        currencyLabel.setFont(new Font("Serif", Font.BOLD, 24));
        currencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shopPanel.add(currencyLabel);

        // Food items
        JPanel foodItems = createCategoryPanel("Food");
        foodItems.add(createShopItem
                ("Buy Cookie: $2",
                        "/res/cookie.png",
                        "Delicious cookie - Price: $2",
                        2,
                        () -> {
                                shopActions.increaseHunger(4);
                }));
        foodItems.add(createShopItem
                ("Buy Apple: $5",
                        "/res/apple.png",
                        "Fresh apple - Price: $5",
                        5,
                        () -> {
                                shopActions.increaseHunger(8);

                }));
        shopPanel.add(foodItems);

        // Toy items
        JPanel toyItems = createCategoryPanel("Toys");
        toyItems.add(createShopItem
                ("Buy Ball: $10",
                        "/res/ball.png",
                        "Bouncy ball - Price: $10",
                        10,
                        () -> {
                                shopActions.increaseHunger(6);
                                shopActions.increaseHappiness(4);
                        }));
        toyItems.add(createShopItem
                ("Buy Trampoline: $30",
                        "/res/trampoline.png",
                        "Fun trampoline - Price: $30",
                        30,
                        () -> {
                                shopActions.increaseHunger(10);
                                shopActions.increaseHappiness(15);
                }));
        shopPanel.add(toyItems);

        // Pokemon
        JPanel pokemonItems = createCategoryPanel("Pokemon");
        pokemonItems.add(createShopItem
                ("Buy Pikachu: $100",
                        "/res/pikachu.png",
                        "Cute Pikachu - Price: $100",
                        100,
                        () -> {
                                shopActions.increaseHunger(40);
                                shopActions.increaseHappiness(40);
                                shopActions.increaseHealth(50);
                        }));
        pokemonItems.add(createShopItem
                ("Buy Squirtle: $80",
                        "/res/squirtle.png",
                        "Cool Squirtle - Price: $80",
                        80,
                        () -> {
                            shopActions.increaseHunger(30);
                            shopActions.increaseHappiness(30);
                            shopActions.increaseHealth(35);
        }));
        shopPanel.add(pokemonItems);

        this.add(shopPanel);
        this.setVisible(true);
    }

    private JPanel createCategoryPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 230, 255));
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private JButton createShopItem(String buttonText, String iconPath, String tooltip, int cost, Runnable action) {
        JButton button = new JButton(buttonText);
        button.setToolTipText(tooltip);
        URL location = getClass().getResource(iconPath);
        ImageIcon icon = new ImageIcon(location);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        button.setIcon(icon);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);

        // Set custom button styling
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        button.addActionListener(e -> {
            if (currency.balance >= cost) {
                currency.balance -= cost;
                currencyLabel.setText("Money: $" + currency.balance);
                action.run(); // Execute the action
                JOptionPane.showMessageDialog(this, "Item purchased successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds!");
            }
        });

        return button;
    }

    // Deduct balance when Item is purchased
    public void purchaseItem(int cost) {
        if (currency.balance >= cost) {
            currency.balance -= cost;
            currencyLabel.setText("Money: " + currency.balance);
            JOptionPane.showMessageDialog(this, "Item purchased successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds!");
        }
    }

}

