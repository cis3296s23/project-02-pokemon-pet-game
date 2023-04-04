package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class shop extends JFrame {

    public shop() {
        this.setTitle("Shop");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel shopPanel = new JPanel();
        JButton buy = new JButton("Buy");

        shopPanel.add(buy);
        this.add(shopPanel);
        this.setVisible(true);
    }

}
