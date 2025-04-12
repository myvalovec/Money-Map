package com.mm;

import java.awt.Color;

public class SidePanelHandler {
    TopPanel TP;
    SidePanel SP;
    SQLiteHandler db;

    String selectedCategory = "Income";
    String selectedSubcategory = "Income";

    SidePanelHandler(SidePanel SP, TopPanel TP) {
        this.TP = TP;
        this.SP = SP;
        db = new SQLiteHandler();
        
    }

    public void saveData() {
        db.add(String.valueOf(TP.TBH.selectedYear), String.valueOf(TP.TBH.selectedMonth), SP.fField.getText(), Integer.valueOf(SP.sField.getText()), selectedCategory, selectedSubcategory);
        //System.out.println(db.db.getRecordsForMonth(String.valueOf(TP.TBH.selectedYear), String.valueOf(TP.TBH.selectedMonth)));
    }

    public void addHandler() {
        SP.remove(SP.SR);
        SP.add(SP.centerPanel);
        SP.revalidate();
        SP.repaint();
        SP.addButton.setBackground(new Color(30,30,30));
        SP.addButton.setForeground(new Color(223,228,234));
        SP.removeButton.setBackground(new Color(40,40,40));
        SP.removeButton.setForeground(new Color(160,170,180));
    }

    public void removeHandler() {
        SP.remove(SP.centerPanel);
        SP.add(SP.SR);
        SP.revalidate();
        SP.repaint();
        SP.addButton.setBackground(new Color(40,40,40));
        SP.addButton.setForeground(new Color(160,170,180));
        SP.removeButton.setBackground(new Color(30,30,30));
        SP.removeButton.setForeground(new Color(223,228,234));
    }

    public void checkboxHandler(int code) {
        if(code == 0) { //INCOME
            SP.sCheckBox.setSelected(false);
            SP.sfCheckBox.setSelected(false);
            SP.ssCheckBox.setSelected(false);
            SP.stCheckBox.setSelected(false);
        }
        else if (code == 1) { //EXPENSE
            SP.fCheckBox.setSelected(false);
            if(SP.sCheckBox.isSelected() == false) {
                SP.sfCheckBox.setSelected(false);
                SP.ssCheckBox.setSelected(false);
                SP.stCheckBox.setSelected(false);
            }
            else {
                SP.sfCheckBox.setSelected(true);
            }
        }
        else if (code == 2) { //Investments
            SP.fCheckBox.setSelected(false);
            SP.sCheckBox.setSelected(true);
            SP.ssCheckBox.setSelected(false);
            SP.stCheckBox.setSelected(false);
        }
        else if (code == 3) { //Musts
            SP.fCheckBox.setSelected(false);
            SP.sCheckBox.setSelected(true);
            SP.sfCheckBox.setSelected(false);
            SP.stCheckBox.setSelected(false);
        }
        else if (code == 4) { //Wants
            SP.fCheckBox.setSelected(false);
            SP.sCheckBox.setSelected(true);
            SP.sfCheckBox.setSelected(false);
            SP.ssCheckBox.setSelected(false);
        }
        if(SP.sCheckBox.isSelected() == true && SP.fCheckBox.isSelected() == false && SP.sfCheckBox.isSelected() == false && SP.ssCheckBox.isSelected() == false && SP.stCheckBox.isSelected() == false) {
            SP.sfCheckBox.setSelected(true);
        }
        if(SP.fCheckBox.isSelected() == false && SP.sCheckBox.isSelected() == false) {
            SP.fCheckBox.setSelected(true);
        }
        if(SP.fCheckBox.isSelected() == true) {
            selectedCategory = "Income";
            selectedSubcategory = "Income";
        }
        else if(SP.sCheckBox.isSelected() == true) {
            if(SP.sfCheckBox.isSelected() == true) {
                selectedCategory = "Expense";
                selectedSubcategory = "Investments";
            }
            if(SP.ssCheckBox.isSelected() == true) {
                selectedCategory = "Expense";
                selectedSubcategory = "Musts";
            }
            if(SP.stCheckBox.isSelected() == true) {
                selectedCategory = "Expense";
                selectedSubcategory = "Wants";
            }
        }
    }

}
