package com.mm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class SideRemove extends JPanel {
    SQLiteHandler dbHandler;
    TopButtonHandler TBH;

    JScrollPane scroll;
    JPanel holder;
    ArrayList<RemoveItem> removeItems = new ArrayList<RemoveItem>();

    SideRemove(TopButtonHandler TBH) {
        dbHandler = new SQLiteHandler();
        this.TBH = TBH;

        scroll = new JScrollPane();
        holder = new JPanel();
        

        this.setBackground(Color.PINK);
        this.setLayout(new GridLayout());;
        this.add(scroll);
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
        
        scroll.getViewport().add(holder);
            scroll.setBackground(new Color(22,22,22));
            scroll.setBorder(null);
            holder.setBackground(new Color(22,22,22));
            holder.setBorder(new LineBorder(new Color(22,22,22), 10));

            
    }

    public void clickedItem(String text) {
        //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
        String year = String.valueOf(TBH.selectedYear);
        String month = String.valueOf(TBH.selectedMonth);
        dbHandler.db.deleteRecordByMonthAndName(year, month, text);
        loadItems();
        //System.out.println(dbHandler.loadNames(year, month) + "adasdasdasdasdsa");
    }

    public void loadItems() {
        ArrayList<String> temp = new ArrayList<String>();
        String year = String.valueOf(TBH.selectedYear);
        String month = String.valueOf(TBH.selectedMonth);
        temp = dbHandler.loadNames(year, month);
        holder.removeAll();
        removeItems.clear();
        for(int i = 0; i < temp.size(); i++) {
            if(hasItem(temp.get(i)) == false) {
                String tempName = temp.get(i);
                RemoveItem tempItem = new RemoveItem(tempName);
                tempItem.addActionListener(e -> clickedItem(tempName));
                holder.add(tempItem);
                removeItems.add(tempItem);
                
            }
        }
        this.revalidate();
        this.repaint();

    }

    public boolean hasItem(String text) {
        for(int i = 0; i < removeItems.size(); i++) {
            if(removeItems.get(i).text.equals(text)) {
                return true;
            }
        }
        return false;
    }

    public void removeItems(String text) {
        
    }

    
}

