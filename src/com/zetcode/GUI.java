package com.zetcode;
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
class GUI {
    public static void main(String args[]) {

        JFrame frame = new JFrame("COVID-19 Area Data Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);

        JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Enter State");
        JTextField input = new JTextField(15); 
        JButton search = new JButton("Search");
        
        panel.add(label); 
        panel.add(input);
        panel.add(search);

        
        JTextArea displayarea = new JTextArea();

        
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, displayarea);
        frame.setVisible(true);
    }
}