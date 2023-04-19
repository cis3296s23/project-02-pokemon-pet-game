package main;

import javax.swing.*;
import java.awt.event.*;

public class Action extends JPanel{
    //create button
    JButton button = new JButton("Click");
    

    //create action listener
    ActionListener listener = new MyActionListener(){
        public void actionPerformed(ActionEvent e){
            //click event 
            System.out.println("Button clicked");
        }
    }

    //add action listener to button
    button.addActionListener(listener);

    //add button to frame
    getContentPane().add(button);
    

    //set frame size and make visible
    setSize(300,200);
    setVisible(true);
}