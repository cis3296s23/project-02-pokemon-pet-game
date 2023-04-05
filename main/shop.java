package main;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class shop extends JFrame {

    public shop() {
        this.setTitle("Shop");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Shop will be composed of three Jpanels inside main panel
        // Main panel uses box layout to organize items in cateogries
        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(new Color(74, 208, 27));
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));

        // Food items
        JPanel foodItems = new JPanel();
        foodItems.setBackground(new Color(74, 208, 27));

        JButton buyCookie = new JButton("Buy Cookie");
        JButton buyApple = new JButton("Buy Apple");
        foodItems.add(buyCookie);
        foodItems.add(buyApple);
        shopPanel.add(foodItems);

        // Toy items
        JPanel toyItems = new JPanel();
        toyItems.setBackground(new Color(74, 208, 27));
        JButton buyBall = new JButton("Buy Ball");
        JButton buyTrampoline = new JButton("Buy Trampoline");
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

}
