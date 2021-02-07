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

        

        String data[][]={ {"101","Amit","670000"}};    
        String column[]={"ID","NAME","SALARY"};         
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        frame.add(sp);   
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
    }
}