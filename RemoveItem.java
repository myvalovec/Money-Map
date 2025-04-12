package com.mm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemoveItem extends JRoundedButton {
    String text;
    JLabel napis;
    RemoveItem(String text) {
        this.text = text;
        napis = new JLabel();
        napis.setText(text);
        this.setBackground(new Color(30,30,30));
        this.setForeground(new Color(223,228,234));
        napis.setForeground(new Color(223,228,234));
        this.setPreferredSize(new Dimension(240,50));
        this.setMaximumSize(new Dimension(240,50));
        this.add(napis);
    }
    
}
