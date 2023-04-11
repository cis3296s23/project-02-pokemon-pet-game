package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class shop extends JFrame {

    private gamePanel gamePanel;
    private JLabel currencyLabel;

    public shop(gamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setTitle("Shop");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // set the focus back to the gamePanel
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });

        // Shop will be composed of three Jpanels inside main panel
        // Main panel uses box layout to organize items in cateogries
        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(new Color(74, 208, 27));
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));

        // Currency label
        currencyLabel = new JLabel("Money: " + currency.balance);
        shopPanel.add(currencyLabel);

        // Food items
        JPanel foodItems = new JPanel();
        foodItems.setBackground(new Color(74, 208, 27));
        foodItems.setLayout(new FlowLayout());

        JLabel foodHeader = new JLabel("Food");
        foodItems.add(foodHeader);

        // Add empty JLabel to sapce food header
        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(50, 1));
        foodItems.add(emptyLabel);

        JButton buyCookie = new JButton("Buy Cookie: $5");
        buyCookie.addActionListener(e -> purchaseItem(5));
        JButton buyApple = new JButton("Buy Apple: $10");
        buyApple.addActionListener(e -> purchaseItem(10));
        foodItems.add(buyCookie);
        foodItems.add(buyApple);
        shopPanel.add(foodItems);

        // Toy items
        JPanel toyItems = new JPanel();
        toyItems.setBackground(new Color(74, 208, 27));
        JButton buyBall = new JButton("Buy Ball: $10");
        buyBall.addActionListener(e -> purchaseItem(10));
        JButton buyTrampoline = new JButton("Buy Trampoline: $25");
        buyTrampoline.addActionListener(e -> purchaseItem(25));
        toyItems.add(buyBall);
        toyItems.add(buyTrampoline);
        shopPanel.add(toyItems);

        // Pokemon
        JPanel pokemon = new JPanel();
        pokemon.setBackground(new Color(74, 208, 27));
        JButton buyPikachu = new JButton("Buy Pikachu");
        JButton buySquirtle = new JButton("Buy Squirtle");
        pokemon.add(buySquirtle);
        pokemon.add(buyPikachu);
        shopPanel.add(pokemon);

        this.add(shopPanel);
        this.setVisible(true);
    }

    // Deduct balance when Item is purchased
    public void purchaseItem(int cost) {
        currency.balance -= cost;
        currencyLabel.setText("Money: " + currency.balance);
    }

}
