package com.mm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
    SQLite database;

    TopPanel topPanel = new TopPanel();
    SidePanel sidePanel = new SidePanel(topPanel);
    CenterPanel centerPanel = new CenterPanel(topPanel, sidePanel);

    MainPanel() {
        this.setBackground(new Color(18, 18, 18));
        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(sidePanel, BorderLayout.WEST);
    }
    
}