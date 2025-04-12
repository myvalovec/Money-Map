package com.mm;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
    Frame frm;
    
    boolean FrameOpened;
    Timer tfrmSize;
    Timer tfrmCol;
    Color bckgColor = new Color(0,0,0);
    double MPop = 0;
    MainPanel MP;
    Frame() {
        frm = this;
        MP = new MainPanel();
        tfrmSize = new Timer();
        tfrmCol = new Timer();
        //this.setSize(50,50);
        this.setLayout(new GridLayout());
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Money Map");
        this.getContentPane().setBackground(new Color(0,0,0));
        tfrmSize.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(frm.getWidth() <= Toolkit.getDefaultToolkit().getScreenSize().width) {
                    frm.setSize(frm.getWidth()+10+((Toolkit.getDefaultToolkit().getScreenSize().width-frm.getWidth())/9), frm.getHeight());
                }
                else {
                    //System.out.println("cancel");
                    frm.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
                    
                    frm.setExtendedState(frm.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                    
                    FrameOpened = true;
                    tfrmSize.cancel();
                }
                if (frm.getHeight() <= Toolkit.getDefaultToolkit().getScreenSize().height) {
                    frm.setSize(frm.getWidth(), frm.getHeight()+10+((Toolkit.getDefaultToolkit().getScreenSize().height-frm.getHeight())/9));
                }
                
            }
            
        },200, 1);
        tfrmCol.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                if(FrameOpened == true && frm.bckgColor.getRed() < 18) {
                    bckgColor = new Color(bckgColor.getRed()+1, bckgColor.getGreen()+1, bckgColor.getBlue()+1);
                    frm.getContentPane().setBackground(bckgColor);
                }
                else {
                    frm.add(MP);
                    frm.revalidate();
                    frm.repaint();
                    frm.setMinimumSize(new Dimension(1250,700));
                    tfrmCol.cancel();
                }
            }
        }, 1000, 10);
    }
}
