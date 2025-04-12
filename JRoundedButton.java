package com.mm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class JRoundedButton extends JButton {
    public JRoundedButton() { //String text
        //super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }
}
