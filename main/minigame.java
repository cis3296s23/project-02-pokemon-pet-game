package main;

import javax.swing.JFrame;

public class minigame extends JFrame{

	minigame(){	
		this.add(new snakePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}