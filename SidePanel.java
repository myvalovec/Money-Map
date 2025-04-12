package com.mm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SidePanel extends JPanel {
    int width = 260;
    int height = 990;
    JPanel sidePanel;
    JPanel centerPanel;
        JPanel fInput;
            JLabel fLabel;
            JTextField fField;
        JPanel sInput;
            JLabel sLabel;
            JTextField sField;
        JPanel checkBoxes;
            JCheckBox fCheckBox;
            JCheckBox sCheckBox;
            JCheckBox sfCheckBox;
            JCheckBox ssCheckBox;
            JCheckBox stCheckBox;
    JPanel topPanel;
        JRoundedButton removeButton;
        JRoundedButton addButton;
    JPanel bottomPanel;
        JRoundedButton saveButton;

    SideRemove SR;

    SidePanelHandler SPH;
    
    SidePanel(TopPanel TP) {
        sidePanel = this;
        
        centerPanel = new JPanel();
            fInput = new JPanel();
                fLabel = new JLabel();
                fField = new JTextField();
            sInput = new JPanel();
                sLabel = new JLabel();
                sField = new JTextField();
            checkBoxes = new JPanel();
                fCheckBox = new JCheckBox();
                sCheckBox = new JCheckBox();
                sfCheckBox = new JCheckBox();
                ssCheckBox = new JCheckBox();
                stCheckBox = new JCheckBox();
        topPanel = new JPanel();
            removeButton = new JRoundedButton();
            addButton = new JRoundedButton();
        bottomPanel = new JPanel();
            saveButton = new JRoundedButton();

        SR = new SideRemove(TP.TBH);

        SPH = new SidePanelHandler(this, TP);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(50,height));
        this.setBackground(new Color(22,22,22));
        this.add(centerPanel, BorderLayout.CENTER);
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setBackground(new Color(22,22,22));
            centerPanel.add(fInput);
                fInput.setPreferredSize(new Dimension(220, 85));
                fInput.setBackground(new Color(22,22,22));
                fInput.setLayout(new BoxLayout(fInput, BoxLayout.Y_AXIS));
                fInput.setAlignmentX(Component.CENTER_ALIGNMENT);
                fInput.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
                fInput.setMaximumSize(fInput.getPreferredSize());
                fInput.add(fLabel);
                    fLabel.setPreferredSize(new Dimension(220,22));
                    fLabel.setMinimumSize(new Dimension(50, 20));
                    fLabel.setMaximumSize(getPreferredSize());
                    fLabel.setForeground(new Color(223,228,234));
                    fLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    fLabel.setText("Name");
                fInput.add(fField);
                    fField.setPreferredSize(new Dimension(220,43));
                    fField.setBackground(new Color(30,30,30));
                    fField.setForeground(new Color(223,228,234));
                    fField.setBorder(null);
            centerPanel.add(sInput);
                sInput.setPreferredSize(new Dimension(220, 85));
                sInput.setBackground(new Color(22,22,22));
                sInput.setLayout(new BoxLayout(sInput, BoxLayout.Y_AXIS));
                sInput.setAlignmentX(Component.CENTER_ALIGNMENT);
                sInput.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
                sInput.setMaximumSize(sInput.getPreferredSize());
                sInput.add(sLabel);
                    sLabel.setPreferredSize(new Dimension(220,22));
                    sLabel.setMaximumSize(getPreferredSize());
                    sLabel.setForeground(new Color(223,228,234));
                    sLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    sLabel.setText("Amount");
                sInput.add(sField);
                    sField.setPreferredSize(new Dimension(220,43));
                    sField.setBackground(new Color(30,30,30));
                    sField.setForeground(new Color(223,228,234));
                    sField.setBorder(null);
            centerPanel.add(checkBoxes);
                checkBoxes.setPreferredSize(new Dimension(220, 200));
                checkBoxes.setBackground(new Color(22,22,22));
                checkBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);
                checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
                checkBoxes.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
                checkBoxes.setMaximumSize(checkBoxes.getPreferredSize());
                checkBoxes.add(fCheckBox);
                    fCheckBox.setText("Income");
                    fCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    fCheckBox.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
                    fCheckBox.setFocusable(false);
                    fCheckBox.setBackground(new Color(22,22,22));
                    fCheckBox.setForeground(new Color(223,228,234));
                    fCheckBox.setSelected(true);
                    fCheckBox.addActionListener(e -> SPH.checkboxHandler(0));
                checkBoxes.add(sCheckBox);
                    sCheckBox.setText("Expense");
                    sCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    sCheckBox.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
                    sCheckBox.setFocusable(false);
                    sCheckBox.setForeground(new Color(223,228,234));
                    sCheckBox.setBackground(new Color(22,22,22));
                    sCheckBox.addActionListener(e -> SPH.checkboxHandler(1));
                checkBoxes.add(sfCheckBox);
                    sfCheckBox.setText("Investments");
                    sfCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    sfCheckBox.setBorder(BorderFactory.createEmptyBorder(5,20,0,0));
                    sfCheckBox.setFocusable(false);
                    sfCheckBox.setBackground(new Color(22,22,22));
                    sfCheckBox.setForeground(new Color(223,228,234));
                    sfCheckBox.addActionListener(e -> SPH.checkboxHandler(2));
                checkBoxes.add(ssCheckBox);
                    ssCheckBox.setText("Musts");
                    ssCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    ssCheckBox.setBorder(BorderFactory.createEmptyBorder(5,20,0,0));
                    ssCheckBox.setFocusable(false);
                    ssCheckBox.setForeground(new Color(223,228,234));
                    ssCheckBox.setBackground(new Color(22,22,22));
                    ssCheckBox.addActionListener(e -> SPH.checkboxHandler(3));
                checkBoxes.add(stCheckBox);
                    stCheckBox.setText("Wants");
                    stCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    stCheckBox.setBorder(BorderFactory.createEmptyBorder(5,20,0,0));
                    stCheckBox.setFocusable(false);
                    stCheckBox.setForeground(new Color(223,228,234));
                    stCheckBox.setBackground(new Color(22,22,22));
                    stCheckBox.addActionListener(e -> SPH.checkboxHandler(4));
        this.add(topPanel, BorderLayout.NORTH);
            topPanel.setBackground(new Color(22,22,22));
            topPanel.setLayout(new FlowLayout());
            topPanel.add(removeButton);
                removeButton.setPreferredSize(new Dimension(100,50));
                removeButton.setBackground(new Color(40,40,40));
                removeButton.setForeground(new Color(160,170,180));
                removeButton.setText("Remove");
                removeButton.addActionListener(e -> SPH.removeHandler());
            topPanel.add(Box.createRigidArea(new Dimension(10,50)));
            topPanel.add(addButton);
                addButton.setPreferredSize(new Dimension(100,50));
                addButton.setBackground(new Color(30,30,30));
                addButton.setForeground(new Color(223,228,234));
                addButton.setText("Add");
                addButton.addActionListener(e -> SPH.addHandler());
        this.add(bottomPanel, BorderLayout.SOUTH);
            bottomPanel.setPreferredSize(new Dimension(260, 75 ));
            bottomPanel.setBackground(new Color(22,22,22));
            bottomPanel.add(saveButton);
                saveButton.setPreferredSize(new Dimension(220,50));
                saveButton.setBackground(new Color(13,71,161));
                saveButton.setForeground(new Color(245,245,245));
                saveButton.setText("Save");
                saveButton.addActionListener(e -> SPH.saveData());
                
                new Timer(10, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //sidePanel.setBounds(sidePanel.getX(), sidePanel.getY(), sidePanel.getWidth()+10, sidePanel.getHeight()+10);
                        sidePanel.setPreferredSize(new Dimension((sidePanel.getWidth())+5, sidePanel.getHeight()));
                        sidePanel.revalidate();
                        sidePanel.repaint();
                        
                        if(sidePanel.getWidth() >= width) {
                            sidePanel.revalidate();
                            sidePanel.repaint();
                            ((Timer)e.getSource()).stop();
                        }
                            
                    }
                }).start();
    
    }
}
