package com.mm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TopPanel extends JPanel {
    JPanel topPanel;
    int width = 1920;
    int height = 90;
    TopButtonHandler TBH;

    JPanel holderCenter;
        JPanel leftPanel;
            JRoundedButton lprev;
            JRoundedButton l1selector;
            JRoundedButton l2selector;
            JRoundedButton l3selector;
            JRoundedButton l4selector;
            JRoundedButton l5selector;
            JRoundedButton l6selector;
            JRoundedButton lnext;
        JPanel centerPanel;
            JRoundedButton load;
        JPanel rightPanel;
            JRoundedButton rprev;
            JRoundedButton r1selector;
            JRoundedButton r2selector;
            JRoundedButton r3selector;
            JRoundedButton rnext;
    JPanel holderRight;
        JLabel currentDate;
    JPanel holderLeft;
        JComboBox adviceSelector;

    TopPanel() {
        String[] advices = { "I:50%, M:30%, W:20%", "I:40%, M:40%, W:20%", "I:30%, M:45%, W:25%", "I:20%, M:50%, W:30%", "I:10%, M:70%, W:20%","I:0%, M:0%, W:0%"};
        topPanel = this;
        holderCenter = new JPanel();
            leftPanel = new JPanel();
                lprev = new JRoundedButton();
                l1selector = new JRoundedButton();
                l2selector = new JRoundedButton();
                l3selector = new JRoundedButton();
                l4selector = new JRoundedButton();
                l5selector = new JRoundedButton();
                l6selector = new JRoundedButton();
                lnext = new JRoundedButton();
            centerPanel = new JPanel();
                load = new JRoundedButton();
            rightPanel = new JPanel();
                rprev = new JRoundedButton();
                r1selector = new JRoundedButton();
                r2selector = new JRoundedButton();
                r3selector = new JRoundedButton();
                rnext = new JRoundedButton();
        holderRight = new JPanel();
            currentDate = new JLabel();
        holderLeft = new JPanel();
            adviceSelector = new JComboBox(advices);

        TBH = new TopButtonHandler(this);

        this.setPreferredSize(new Dimension(1920,0));
        this.setBackground(new Color(22,22,22));
        this.setLayout(new BorderLayout());
        this.add(holderLeft, BorderLayout.WEST);
            holderLeft.setPreferredSize(new Dimension(100,90));
            holderLeft.setBackground(new Color(22,22,22));
        this.add(holderCenter, BorderLayout.CENTER);
            holderCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            holderCenter.setBackground(new Color(22,22,22));
            holderCenter.add(leftPanel, BorderLayout.WEST);
                leftPanel.setPreferredSize(new Dimension(430,90));
                leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
                leftPanel.setBackground(new Color(22,22,22));
                leftPanel.add(lprev);
                    lprev.setPreferredSize(new Dimension(50,50));
                    lprev.setBackground(new Color(22,22,22));
                    lprev.setForeground(new Color(223,228,234));
                    lprev.setText("<");
                    lprev.addActionListener(e -> TBH.leftSwitches(0));
                leftPanel.add(l1selector);
                    l1selector.setPreferredSize(new Dimension(55,50));
                    l1selector.setBackground(new Color(22,22,22));
                    l1selector.setForeground(new Color(223,228,234));
                    l1selector.setText("1");
                    l1selector.addActionListener(e -> TBH.buttonPressL(l1selector));
                leftPanel.add(l2selector);
                    l2selector.setPreferredSize(new Dimension(55,50));
                    l2selector.setBackground(new Color(22,22,22));
                    l2selector.setForeground(new Color(223,228,234));
                    l2selector.setText("2");
                    l2selector.addActionListener(e -> TBH.buttonPressL(l2selector));
                leftPanel.add(l3selector);
                    l3selector.setPreferredSize(new Dimension(55,50));
                    l3selector.setBackground(new Color(22,22,22));
                    l3selector.setForeground(new Color(223,228,234));
                    l3selector.setText("3");
                    l3selector.addActionListener(e -> TBH.buttonPressL(l3selector));
                leftPanel.add(l4selector);
                    l4selector.setPreferredSize(new Dimension(55,50));
                    l4selector.setBackground(new Color(22,22,22));
                    l4selector.setForeground(new Color(223,228,234));
                    l4selector.setText("4");
                    l4selector.addActionListener(e -> TBH.buttonPressL(l4selector));
                leftPanel.add(l5selector);
                    l5selector.setPreferredSize(new Dimension(55,50));
                    l5selector.setBackground(new Color(22,22,22));
                    l5selector.setForeground(new Color(223,228,234));
                    l5selector.setText("5");
                    l5selector.addActionListener(e -> TBH.buttonPressL(l5selector));
                leftPanel.add(l6selector);
                    l6selector.setPreferredSize(new Dimension(55,50));
                    l6selector.setBackground(new Color(22,22,22));
                    l6selector.setForeground(new Color(223,228,234));
                    l6selector.setText("6");
                    l6selector.addActionListener(e -> TBH.buttonPressL(l6selector));
                leftPanel.add(lnext);
                    lnext.setPreferredSize(new Dimension(50,50));
                    lnext.setBackground(new Color(22,22,22));
                    lnext.setForeground(new Color(223,228,234));
                    lnext.setText(">");
                    lnext.addActionListener(e -> TBH.leftSwitches(1));
/*          holderCenter.add(centerPanel, BorderLayout.CENTER);
                centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setPreferredSize(new Dimension(100,90));
                centerPanel.setBackground(new Color(22,22,22));
                centerPanel.add(Box.createRigidArea(new Dimension(100,10)));
                centerPanel.add(load);
                    load.setPreferredSize(new Dimension(100,50));
                    load.setMinimumSize(getPreferredSize());
                    load.setBackground(new Color(30,30,30));
                    load.setForeground(new Color(223,228,234));
                    load.setText("Load");*/
            holderCenter.add(rightPanel, BorderLayout.EAST);
                rightPanel.setPreferredSize(new Dimension(430,90));
                rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
                rightPanel.setBackground(new Color(22,22,22));
                rightPanel.add(rprev);
                    rprev.setPreferredSize(new Dimension(50,50));
                    rprev.setBackground(new Color(22,22,22));
                    rprev.setForeground(new Color(223,228,234));
                    rprev.setText("<");
                    rprev.addActionListener(e -> TBH.rightSwitches(0));
                rightPanel.add(r1selector);
                    r1selector.setPreferredSize(new Dimension(100,50));
                    r1selector.setBackground(new Color(22,22,22));
                    r1selector.setForeground(new Color(223,228,234));
                   // r1selector.setText("2025");
                    r1selector.addActionListener(e -> TBH.buttonPressR(r1selector));
                rightPanel.add(r2selector);
                    r2selector.setPreferredSize(new Dimension(100,50));
                    r2selector.setBackground(new Color(22,22,22));
                    r2selector.setForeground(new Color(223,228,234));
                    //r2selector.setText("2024");
                    r2selector.addActionListener(e -> TBH.buttonPressR(r2selector));
                rightPanel.add(r3selector);
                    r3selector.setPreferredSize(new Dimension(100,50));
                    r3selector.setBackground(new Color(22,22,22));
                    r3selector.setForeground(new Color(223,228,234));
                    //r3selector.setText("2023");
                    r3selector.addActionListener(e -> TBH.buttonPressR(r3selector));
                rightPanel.add(rnext);
                    rnext.setPreferredSize(new Dimension(50,50));
                    rnext.setBackground(new Color(22,22,22));
                    rnext.setForeground(new Color(223,228,234));
                    rnext.setText(">");
                    rnext.addActionListener(e -> TBH.rightSwitches(1));
        this.add(holderRight, BorderLayout.EAST);
            holderRight.setBackground(new Color(22,22,22));
            holderRight.setPreferredSize(new Dimension(200,90));
            holderRight.setLayout(new GridLayout());
            holderRight.setBorder(null);
            /*holderRight.add(currentDate);
                currentDate.setText(String.valueOf(TBH.selectedMonth)+"/"+String.valueOf(TBH.selectedYear));
                currentDate.setHorizontalAlignment(SwingConstants.CENTER);
                currentDate.setVerticalAlignment(SwingConstants.CENTER);
                currentDate.setForeground(new Color(223,228,234));*/
            holderRight.add(adviceSelector);
                adviceSelector.setBackground(new Color(22,22,22));
                adviceSelector.setForeground(new Color(223,228,234));
                adviceSelector.setBorder(null);
                adviceSelector.setBorder(new EmptyBorder(30,0,30,0));
        
        TBH.buttonPressR(r1selector);
        TBH.selectCurrentMonth();

        new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.setPreferredSize(new Dimension(topPanel.getWidth(), topPanel.getHeight()+2));
                topPanel.revalidate();
                topPanel.repaint();
                        
                        if(topPanel.getHeight() >= height) {
                            topPanel.revalidate();
                            topPanel.repaint();
                            ((Timer)e.getSource()).stop();
                        }
            }
            
        }).start();
    }
}
