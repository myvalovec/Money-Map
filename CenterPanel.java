package com.mm;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class CenterPanel extends JPanel {
    CenterPanel CP;
    SQLiteHandler dbh;
    TopPanel TP;
    SidePanel SP;
    Timer t;
    Timer ta;
    int panelHeight = 0;
    int panelWidth = 0;

    String drawnMonth = "";
    String drawnYear = "";

    ArrayList<Integer> tempPresetsList = new ArrayList<Integer>();

    boolean chartOpening = false;
    boolean loading = false;
    boolean loading2 = false;
    
    ArrayList<Integer> namesPos = new ArrayList<Integer>();

    ArrayList<Integer> incomesHeights = new ArrayList<Integer>();
    ArrayList<Boolean> incomesFinished = new ArrayList<Boolean>();
    int incomeHeightMain = 0;
    boolean incomeMainOpened = false;

    ArrayList<Integer> expensesHeights = new ArrayList<Integer>();
    ArrayList<Boolean> expensesFinished = new ArrayList<Boolean>();
    int expenseHeightMain = 0;
    boolean expenseMainOpened = false;

    ArrayList<Integer> investmentsHeights = new ArrayList<Integer>();
    ArrayList<Boolean> investmentsFinished = new ArrayList<Boolean>();
    int investmentHeightMain = 0;
    boolean investmentMainOpened = false;

    ArrayList<Integer> mustsHeights = new ArrayList<Integer>();
    ArrayList<Boolean> mustsFinished = new ArrayList<Boolean>();
    int mustHeightMain = 0;
    boolean mustMainOpened = false;

    ArrayList<Integer> wantsHeights = new ArrayList<Integer>();
    ArrayList<Boolean> wantsFinished = new ArrayList<Boolean>();
    int wantHeightMain = 0;
    boolean wantMainOpened = false;

    int Income = 0;
    ArrayList<Integer> Incomes = new ArrayList<Integer>();
    ArrayList<String> IncomesNames = new ArrayList<String>();
    int Expense = 0;
    ArrayList<Integer> Expenses = new ArrayList<Integer>();
    ArrayList<String> ExpensesNames = new ArrayList<String>();

    ArrayList<Integer> EInvestments = new ArrayList<Integer>();
    ArrayList<String> InvestmentsNames = new ArrayList<String>();
    int Investments = 0;
    ArrayList<Integer> EMusts = new ArrayList<Integer>();
    ArrayList<String> MustsNames = new ArrayList<String>();
    int Musts = 0;
    ArrayList<Integer> EWants= new ArrayList<Integer>();
    ArrayList<String> WantsNames = new ArrayList<String>();
    int Wants = 0;
    int maxValue = 0;

    String year;
    String month;

    CenterPanel(TopPanel TP, SidePanel SP) {

        this.TP = TP;
        CP = this;
        dbh = new SQLiteHandler();
        t = new Timer();
        ta = new Timer();
        this.setBackground(new Color(18,18,18));
        drawnMonth = String.valueOf(TP.TBH.selectedMonth);
        drawnYear = String.valueOf(TP.TBH.selectedYear);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                loading = true;
                year = String.valueOf(TP.TBH.selectedYear);
                month = String.valueOf(TP.TBH.selectedMonth);
                panelHeight = CP.getHeight();
                panelWidth = CP.getWidth();
        
                //load Incomes
                Incomes = dbh.loadIncomes(year, month);
                Income = 0;
                for(int i = 0; i < Incomes.size(); i++) {
                    Income += Incomes.get(i);
                }
                //load Incomes names
                IncomesNames = dbh.loadIncomesNames(year, month);

                //load Expenses
                Expenses = dbh.loadExpenses(year, month);
                Expense = 0;
                for(int i = 0; i < Expenses.size(); i++) {
                    Expense += Expenses.get(i);
                }

                //load Expenses names
                ExpensesNames = dbh.loadExpensesNames(year, month);

                EInvestments = dbh.loadEInvestments(year, month);
                Investments = 0;
                for(int i = 0; i < EInvestments.size(); i++) {
                    Investments += EInvestments.get(i);
                }
                EMusts = dbh.loadEMusts(year, month);
                Musts = 0;
                for(int i = 0; i < EMusts.size(); i++) {
                    Musts += EMusts.get(i);
                }
                EWants = dbh.loadEWants(year, month);
                Wants = 0;
                for(int i = 0; i < EWants.size(); i++) {
                    Wants += EWants.get(i);
                }
                InvestmentsNames = dbh.loadInvestmentsNames(year, month);
                MustsNames = dbh.loadMustsNames(year, month);
                WantsNames = dbh.loadWantsNames(year, month);
                
                //determine max value
                if(Income > Expense) {
                    maxValue = Income;
                }
                else {
                    maxValue = Expense;
                }

                //sorting
                int temp = 0;
                String temp2 = "";
                boolean sortingFinished = false;
                razeni:
                while (sortingFinished == false) {
                    temp = 0;
                    temp2 = "";
                    sortingFinished = true;
                    for(int i = Incomes.size()-1; i > 0; i--) {
                        if((Incomes.get(i-1) < Incomes.get(i))) {
                            sortingFinished = false;
                            temp = Incomes.get(i);
                            Incomes.set(i, Incomes.get(i-1));
                            Incomes.set(i-1, temp);
                            temp2 = IncomesNames.get(i);
                            IncomesNames.set(i, IncomesNames.get(i-1));
                            IncomesNames.set(i-1, temp2);
                            continue razeni;
                        }
                    }
                    temp = 0;
                    temp2 = "";
                    sortingFinished = true;
                    for(int i = Expenses.size()-1; i > 0; i--) {
                        if((Expenses.size() > 0) && (Expenses.get(i-1) < Expenses.get(i))) {
                            sortingFinished = false;
                            temp = Expenses.get(i);
                            Expenses.set(i, Expenses.get(i-1));
                            Expenses.set(i-1, temp);
                            temp2 = ExpensesNames.get(i);
                            ExpensesNames.set(i, ExpensesNames.get(i-1));
                            ExpensesNames.set(i-1, temp2);
                            continue razeni;
                        }
                    }
                    temp = 0;
                    temp2 = "";
                    sortingFinished = true;
                    for(int i = EInvestments.size()-1; i > 0; i--) {
                        if((EInvestments.size() > 0) && (EInvestments.get(i-1) < EInvestments.get(i))) {
                            sortingFinished = false;
                            temp = EInvestments.get(i);
                            EInvestments.set(i, EInvestments.get(i-1));
                            EInvestments.set(i-1, temp);
                            temp2 = InvestmentsNames.get(i);
                            InvestmentsNames.set(i, InvestmentsNames.get(i-1));
                            InvestmentsNames.set(i-1, temp2);
                            continue razeni;
                        }
                    }
                    temp = 0;
                    temp2 = "";
                    sortingFinished = true;
                    for(int i = EMusts.size()-1; i > 0; i--) {
                        if((EMusts.size() > 0) && (EMusts.get(i-1) < EMusts.get(i))) {
                            sortingFinished = false;
                            temp = EMusts.get(i);
                            EMusts.set(i, EMusts.get(i-1));
                            EMusts.set(i-1, temp);
                            temp2 = MustsNames.get(i);
                            MustsNames.set(i, MustsNames.get(i-1));
                            MustsNames.set(i-1, temp2);
                            continue razeni;
                        }
                    }
                    temp = 0;
                    temp2 = "";
                    sortingFinished = true;
                    for(int i = EWants.size()-1; i > 0; i--) {
                        if((EWants.size() > 0) && (EWants.get(i-1) < EWants.get(i))) {
                            sortingFinished = false;
                            temp = EWants.get(i);
                            EWants.set(i, EWants.get(i-1));
                            EWants.set(i-1, temp);
                            temp2 = WantsNames.get(i);
                            WantsNames.set(i, WantsNames.get(i-1));
                            WantsNames.set(i-1, temp2);
                            continue razeni;
                        }
                    }
                    
                }

                //presets handler
                String tempPresets;
                
                tempPresets = (String) TP.adviceSelector.getSelectedItem();
                String[] tempPresetsArr = tempPresets.split(",");
                tempPresetsList.clear();
                for(int i = 0; i < tempPresetsArr.length; i++) {
                    tempPresets = "";
                    String[] tempPresetsArr2 = tempPresetsArr[i].split(":");
                    tempPresets = (String) tempPresetsArr2[1];
                    tempPresets = tempPresets.substring(0, tempPresets.length()-1);
                    tempPresetsList.add(Integer.valueOf(tempPresets));
                }

                CP.revalidate();
                CP.repaint();
                SP.SR.loadItems();
                loading = false;
                loading2 = false;
            }
            
        }, 1500, 800);

        

        ta.schedule(new TimerTask() {
            @Override
            public void run() {
                if(loading == false && loading2 == false) {
                chartOpening = true;
                CP.revalidate();
                CP.repaint();
                }
            }
            
        }, 3000, 1);
        
    }

    @Override
    public void paint(Graphics g) {
        if((!drawnMonth.equals(String.valueOf(TP.TBH.selectedMonth)) || (!drawnYear.equals(String.valueOf(TP.TBH.selectedYear)))) && loading2 == false) {
            loading2 = true;
            super.paint(g);

            namesPos.clear();
    
            incomesHeights.clear();
            incomesFinished.clear();
            incomeHeightMain = 0;
            incomeMainOpened = false;
    
            expensesHeights.clear();
            expensesFinished.clear();
            expenseHeightMain = 0;
            expenseMainOpened = false;
    
            investmentsHeights.clear();
            investmentsFinished.clear();
            investmentHeightMain = 0;
            investmentMainOpened = false;
    
            mustsHeights.clear();
            mustsFinished.clear();
            mustHeightMain = 0;
            mustMainOpened = false;
    
            wantsHeights.clear();
            wantsFinished.clear();
            wantHeightMain = 0;
            wantMainOpened = false;

    
            drawnMonth = String.valueOf(TP.TBH.selectedMonth);
            drawnYear = String.valueOf(TP.TBH.selectedYear);
        }
        else if((IncomesNames.size() != 0  && Incomes.size() != 0) || (ExpensesNames.size() != 0  && Expenses.size() != 0) && (drawnMonth.equals(String.valueOf(TP.TBH.selectedMonth)) && drawnYear.equals(String.valueOf(TP.TBH.selectedYear))) && loading == false && loading2 == false) {
        if((loading == false && loading2 == false) || chartOpening == false) {
            super.paintComponent(g);
        }
        
        Graphics2D g2d = (Graphics2D) g;
        
        
        if(maxValue != 0 && Income != 0 && chartOpening == true && loading == false && loading2 == false) {
            int temp3 = 0;
            double temp1 = (Income / (double) maxValue);
            double temp2 =  (panelHeight-100);
            int heightMain = (int) (temp1*temp2);
            for(int i = namesPos.size(); i < 5; i++) {
                namesPos.add(-10);
            }
            g2d.setPaint(new Color(245,245,245));
            if(namesPos.get(0) <= 30) {
                namesPos.set(0, namesPos.get(0) + 1);
                g2d.drawString("Income", (CP.getWidth()/5)*0+50, namesPos.get(0));
            }
            else {
                g2d.drawString("Income", (CP.getWidth()/5)*0+50, 30);
            }
            //g2d.setPaint(new Color(76, 175, 80));
            g2d.setPaint(new GradientPaint(0, 0, new Color(76, 175, 80), 0, CP.getHeight()*2, new Color(0,0,0)));
            
            if(incomesHeights.size() != Incomes.size()) {
                incomesHeights.clear();
                incomeHeightMain = 0;
                incomeMainOpened = false;
            }
            if(incomesFinished.size() != Incomes.size()) {
                incomesFinished.clear();
            }
            for(int i = incomesHeights.size(); i < Incomes.size(); i++) {
                incomesHeights.add(0);
            }
            for(int i = incomesFinished.size(); i < Incomes.size(); i++) {
                incomesFinished.add(false);
            }
            if(incomeHeightMain >= heightMain) {
                incomeMainOpened = true;
                g2d.fillRoundRect((CP.getWidth()/5)*0+50, 50, 20, heightMain, 10, 10);
            }
            else {
                incomeHeightMain += 14;
                g2d.fillRoundRect((CP.getWidth()/5)*0+50, 50, 20, incomeHeightMain, 10, 10);
            }
            
            int temp4 = (int) (temp1*temp2);
            if(incomeMainOpened == true) {
            for(int i = 0; i < Incomes.size(); i++) {
                temp1 = (Incomes.get(i) / (double) (Income));
                int x = (CP.getWidth()/5)*0+50+20;
                int y = 50+temp3;
                int height = (int) (temp1*temp4);
                if(incomesHeights.get(i) >= height) {
                    incomesFinished.set(i, true);
                    g2d.fillRoundRect(x, y , 20, height, 10, 10);
                    g2d.drawString(IncomesNames.get(i), (CP.getWidth()/5)*0+50+50, (int) (temp3 + 55 + ((double) ((temp1*temp4)) / 2)));
                }
                else {
                    if(i == 0) {
                        incomesHeights.set(i, incomesHeights.get(i)+12);
                        g2d.fillRoundRect(x, y , 20, incomesHeights.get(i), 10, 10);
                        g2d.drawString(IncomesNames.get(i), (CP.getWidth()/5)*0+50+50, (int) (temp3 + 55 + ((double) (incomesHeights.get(i)) / 2)));
                    }
                    else {
                        if(incomesFinished.get(i-1) == true) {
                            incomesHeights.set(i, incomesHeights.get(i)+12+i*2);
                            
                            g2d.fillRoundRect(x, y , 20, incomesHeights.get(i), 10, 10);
                            g2d.drawString(IncomesNames.get(i), (CP.getWidth()/5)*0+50+50, (int) (temp3 + 55 + ((double) (incomesHeights.get(i)) / 2)));
                        }
                    }
                }
                
                temp3 += (int) (temp1*temp4);
            }
        }
        }
        

        if(maxValue != 0 && Expense != 0 && chartOpening == true && loading == false && loading2 == false) {
            int temp3 = 0;
            double temp1 = (Expense / (double) maxValue);
            double temp2 =  (panelHeight-100);
            int heightMain = (int) (temp1*temp2);

            for(int i = namesPos.size(); i < 5; i++) {
                namesPos.add(-10);
            }
            g2d.setPaint(new Color(245,245,245));
            if(namesPos.get(1) <= 30) {
                namesPos.set(1, namesPos.get(1) + 1);
                g2d.drawString("Expense", (CP.getWidth()/5)*1+50, namesPos.get(1));
            }
            else {
                g2d.drawString("Expense", (CP.getWidth()/5)*1+50, 30);
            }
            //g2d.setPaint(new Color(229, 57, 53));
            g2d.setPaint(new GradientPaint(0, 0, new Color(229, 57, 53), 0, CP.getHeight()*2, new Color(0,0,0)));

            if(expensesHeights.size() != Expenses.size()) {
                expensesHeights.clear();
                expenseHeightMain = 0;
                expenseMainOpened = false;
            }
            if(expensesFinished.size() != Expenses.size()) {
                expensesFinished.clear();
            }
            for(int i = expensesHeights.size(); i < Expenses.size(); i++) {
                expensesHeights.add(0);
            }
            
            for(int i = expensesFinished.size(); i < Expenses.size(); i++) {
                expensesFinished.add(false);
            }
            if(expenseHeightMain >= heightMain) {
                expenseMainOpened = true;
                g2d.fillRoundRect((CP.getWidth()/5)*1+50, 50, 20, heightMain, 10, 10);
            }
            else {
                expenseHeightMain += 14;
                g2d.fillRoundRect((CP.getWidth()/5)*1+50, 50, 20, expenseHeightMain, 10, 10);
            }
            int temp4 = (int) (temp1*temp2);
            if(expenseMainOpened == true) {
            for(int i = 0; i < Expenses.size(); i++) {
                temp1 = (Expenses.get(i) / (double) (Expense));
                int x = (CP.getWidth()/5)*1+50+20;
                int y = 50+temp3;
                int height = (int) (temp1*temp4);
                if(expensesHeights.get(i) >= height) {
                    expensesFinished.set(i, true);
                    g2d.fillRoundRect(x, y , 20, height, 10, 10);
                    g2d.drawString(ExpensesNames.get(i), (CP.getWidth()/5)*1+50+50, (int) (temp3 + 55 + ((double) ((temp1*temp4)) / 2)));
                }
                else {
                    if(i == 0) {
                        expensesHeights.set(i, expensesHeights.get(i)+12);
                        g2d.fillRoundRect(x, y , 20, expensesHeights.get(i), 10, 10);
                        g2d.drawString(ExpensesNames.get(i), (CP.getWidth()/5)*1+50+50, (int) (temp3 + 55 + ((double) (expensesHeights.get(i)) / 2)));
                    }
                    else {
                        if(expensesFinished.get(i-1) == true) {
                            expensesHeights.set(i, expensesHeights.get(i)+12+i*2);
                            
                            g2d.fillRoundRect(x, y , 20, expensesHeights.get(i), 10, 10);
                            g2d.drawString(ExpensesNames.get(i), (CP.getWidth()/5)*1+50+50, (int) (temp3 + 55 + ((double) (expensesHeights.get(i)) / 2)));
                        }
                    }
                }
                
                temp3 += (int) (temp1*temp4);
            }
            }
        }

        if(Expense != 0 && Investments != 0 && chartOpening == true && loading == false && loading2 == false) {
            int temp3 = 0;
            double temp1 = (Investments / (double) maxValue);
            double temp2 =  (panelHeight-100);
            int heightMain = (int) (temp1*temp2);

            g2d.setPaint(new Color(245,245,245));
            if(namesPos.get(2) <= 30) {
                namesPos.set(2, namesPos.get(2) + 1);
                g2d.drawString("Investments", (CP.getWidth()/5)*2+50, namesPos.get(2));
            }
            else {
                g2d.drawString("Investments", (CP.getWidth()/5)*2+50, 30);
            }
            //g2d.setPaint(new Color(225, 179, 0));
            g2d.setPaint(new GradientPaint(0, 0, new Color(225, 179, 0), 0, CP.getHeight()*2, new Color(0,0,0)));

            if(investmentsHeights.size() != EInvestments.size()) {
                investmentsHeights.clear();
                investmentHeightMain = 0;
                investmentMainOpened = false;
            }
            if(investmentsFinished.size() != EInvestments.size()) {
                investmentsFinished.clear();
            }
            for(int i = investmentsHeights.size(); i < EInvestments.size(); i++) {
                investmentsHeights.add(0);
            }
            
            for(int i = investmentsFinished.size(); i < EInvestments.size(); i++) {
                investmentsFinished.add(false);
            }
            if(investmentHeightMain >= heightMain) {
                investmentMainOpened = true;
                g2d.fillRoundRect((CP.getWidth()/5)*2+50, 50, 20, heightMain, 10, 10);
                if((int) ((double) 100/Income*Investments) < tempPresetsList.get(0)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Investments) <= tempPresetsList.get(0)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(0) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Investments)) + "%", (CP.getWidth()/5)*2+50+8, heightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(225, 179, 0), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            else {
                investmentHeightMain += 14;
                g2d.fillRoundRect((CP.getWidth()/5)*2+50, 50, 20, investmentHeightMain, 10, 10);
                if((int) ((double) 100/Income*Investments) < tempPresetsList.get(0)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Investments) <= tempPresetsList.get(0)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(0) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Investments)) + "%", (CP.getWidth()/5)*2+50+8, investmentHeightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(225, 179, 0), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            int temp4 = (int) (temp1*temp2);
            if(investmentMainOpened == true) {
            for(int i = 0; i < EInvestments.size(); i++) {
                temp1 = (EInvestments.get(i) / (double) (Investments));
                int x = (CP.getWidth()/5)*2+50+20;
                int y = 50+temp3;
                int height = (int) (temp1*temp4);
                if(investmentsHeights.get(i) >= height) {
                    investmentsFinished.set(i, true);
                    g2d.fillRoundRect(x, y , 20, height, 10, 10);
                    g2d.drawString(InvestmentsNames.get(i), (CP.getWidth()/5)*2+50+50, (int) (temp3 + 55 + ((double) ((temp1*temp4)) / 2)));
                }
                else {
                    if(i == 0) {
                        investmentsHeights.set(i,   investmentsHeights.get(i)+12);
                        g2d.fillRoundRect(x, y , 20, investmentsHeights.get(i), 10, 10);
                        g2d.drawString(InvestmentsNames.get(i), (CP.getWidth()/5)*2+50+50, (int) (temp3 + 55 + ((double) (investmentsHeights.get(i)) / 2)));
                    }
                    else {
                        if(investmentsFinished.get(i-1) == true) {
                            investmentsHeights.set(i, investmentsHeights.get(i)+12+i*2);
                            
                            g2d.fillRoundRect(x, y , 20, investmentsHeights.get(i), 10, 10);
                            g2d.drawString(InvestmentsNames.get(i), (CP.getWidth()/5)*2+50+50, (int) (temp3 + 55 + ((double) (investmentsHeights.get(i)) / 2)));
                        }
                    }
                }
                
                temp3 += (int) (temp1*temp4);
            }
            }
        
        }


        if(Expense != 0 && Musts != 0 && chartOpening == true && loading == false && loading2 == false) {
            int temp3 = 0;
            double temp1 = (Musts / (double) maxValue);
            double temp2 =  (panelHeight-100);
            int heightMain = (int) (temp1*temp2);

            g2d.setPaint(new Color(245,245,245));
            if(namesPos.get(3) <= 30) {
                namesPos.set(3, namesPos.get(3) + 1);
                g2d.drawString("Musts", (CP.getWidth()/5)*3+50, namesPos.get(3));
            }
            else {
                g2d.drawString("Musts", (CP.getWidth()/5)*3+50, 30);
            }
            g2d.setPaint(new GradientPaint(0, 0, new Color(30, 136, 229), 0, CP.getHeight()*2, new Color(0,0,0)));

            if(mustsHeights.size() != EMusts.size()) {
                mustsHeights.clear();
                mustHeightMain = 0;
                mustMainOpened = false;
            }
            if(mustsFinished.size() != EMusts.size()) {
                mustsFinished.clear();
            }
            for(int i = mustsHeights.size(); i < EMusts.size(); i++) {
                mustsHeights.add(0);
            }
            
            for(int i = mustsFinished.size(); i < EMusts.size(); i++) {
                mustsFinished.add(false);
            }
            if(mustHeightMain >= heightMain) {
                mustMainOpened = true;
                g2d.fillRoundRect((CP.getWidth()/5)*3+50, 50, 20, heightMain, 10, 10);
                if((int) ((double) 100/Income*Musts) < tempPresetsList.get(1)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Musts) <= tempPresetsList.get(1)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(1) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Musts)) + "%", (CP.getWidth()/5)*3+50+8, heightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(30, 136, 229), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            else {
                mustHeightMain += 14;
                g2d.fillRoundRect((CP.getWidth()/5)*3+50, 50, 20, mustHeightMain, 10, 10);
                if((int) ((double) 100/Income*Investments) < tempPresetsList.get(0)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Investments) <= tempPresetsList.get(0)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(1) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Musts)) + "%", (CP.getWidth()/5)*3+50+8, mustHeightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(30, 136, 229), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            int temp4 = (int) (temp1*temp2);
            if(mustMainOpened == true) {
            for(int i = 0; i < EMusts.size(); i++) {
                temp1 = (EMusts.get(i) / (double) (Musts));
                int x = (CP.getWidth()/5)*3+50+20;
                int y = 50+temp3;
                int height = (int) (temp1*temp4);
                if(mustsHeights.get(i) >= height) {
                    mustsFinished.set(i, true);
                    g2d.fillRoundRect(x, y , 20, height, 10, 10);
                    g2d.drawString(MustsNames.get(i), (CP.getWidth()/5)*3+50+50, (int) (temp3 + 55 + ((double) ((temp1*temp4)) / 2)));
                }
                else {
                    if(i == 0) {
                        mustsHeights.set(i, mustsHeights.get(i)+12);
                        g2d.fillRoundRect(x, y , 20, mustsHeights.get(i), 10, 10);
                        g2d.drawString(MustsNames.get(i), (CP.getWidth()/5)*3+50+50, (int) (temp3 + 55 + ((double) (mustsHeights.get(i)) / 2)));
                    }
                    else {
                        if(mustsFinished.get(i-1) == true) {
                            mustsHeights.set(i, mustsHeights.get(i)+12+i*2);
                            
                            g2d.fillRoundRect(x, y , 20, mustsHeights.get(i), 10, 10);
                            g2d.drawString(MustsNames.get(i), (CP.getWidth()/5)*3+50+50, (int) (temp3 + 55 + ((double) (mustsHeights.get(i)) / 2)));
                        }
                    }
                }
                
                temp3 += (int) (temp1*temp4);
            }
            }
        
        }

        if(Expense != 0 && Wants != 0 && chartOpening == true && loading == false && loading2 == false) {
            int temp3 = 0;
            double temp1 = (Wants / (double) maxValue);
            double temp2 =  (panelHeight-100);
            int heightMain = (int) (temp1*temp2);

            g2d.setPaint(new Color(245,245,245));
            if(namesPos.get(4) <= 30) {
                namesPos.set(4, namesPos.get(4) + 1);
                g2d.drawString("Wants", (CP.getWidth()/5)*4+50, namesPos.get(4));
            }
            else {
                g2d.drawString("Wants", (CP.getWidth()/5)*4+50, 30);
            }
            //g2d.setPaint(new Color(225, 112, 67));
            g2d.setPaint(new GradientPaint(0, 0, new Color(225, 112, 67), 0, CP.getHeight()*2, new Color(0,0,0)));

            if(wantsHeights.size() != EWants.size()) {
                wantsHeights.clear();
                wantHeightMain = 0;
                wantMainOpened = false;
            }
            if(wantsFinished.size() != EWants.size()) {
                wantsFinished.clear();
            }
            for(int i = wantsHeights.size(); i < EWants.size(); i++) {
                wantsHeights.add(0);
            }
            
            for(int i = wantsFinished.size(); i < EWants.size(); i++) {
                wantsFinished.add(false);
            }
            if(wantHeightMain >= heightMain) {
                wantMainOpened = true;
                g2d.fillRoundRect((CP.getWidth()/5)*4+50, 50, 20, heightMain, 10, 10);
                if((int) ((double) 100/Income*Wants) < tempPresetsList.get(2)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Wants) <= tempPresetsList.get(2)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(2) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Wants)) + "%", (CP.getWidth()/5)*4+50+8, heightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(225, 112, 67), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            else {
                wantHeightMain += 14;
                g2d.fillRoundRect((CP.getWidth()/5)*4+50, 50, 20, wantHeightMain, 10, 10);
                g2d.fillRoundRect((CP.getWidth()/5)*4+50, 50, 20, heightMain, 10, 10);
                if((int) ((double) 100/Income*Wants) < tempPresetsList.get(2)-5) {
                    g2d.setPaint(Color.YELLOW);
                }
                else if((int) ((double) 100/Income*Wants) <= tempPresetsList.get(2)+5) {
                    g2d.setPaint(Color.GREEN);
                }
                else {
                    g2d.setPaint(Color.RED);
                }
                if(tempPresetsList.get(2) != 0) {
                    g2d.drawString(String.valueOf((int) ((double) 100/Income*Wants)) + "%", (CP.getWidth()/5)*4+50+8, wantHeightMain + 50 + 20);
                }
                g2d.setPaint(new GradientPaint(0, 0, new Color(225, 112, 67), 0, CP.getHeight()*2, new Color(0,0,0)));
            }
            int temp4 = (int) (temp1*temp2);
            if(wantMainOpened == true) {
            for(int i = 0; i < EWants.size(); i++) {
                temp1 = (EWants.get(i) / (double) (Wants));
                int x = (CP.getWidth()/5)*4+50+20;
                int y = 50+temp3;
                int height = (int) (temp1*temp4);
                if(wantsHeights.get(i) >= height) {
                    wantsFinished.set(i, true);
                    g2d.fillRoundRect(x, y , 20, height, 10, 10);
                    g2d.drawString(WantsNames.get(i), (CP.getWidth()/5)*4+50+50, (int) (temp3 + 55 + ((double) ((temp1*temp4)) / 2)));
                }
                else {
                    if(i == 0) {
                        wantsHeights.set(i, wantsHeights.get(i)+12);
                        g2d.fillRoundRect(x, y , 20, wantsHeights.get(i), 10, 10);
                        g2d.drawString(WantsNames.get(i), (CP.getWidth()/5)*4+50+50, (int) (temp3 + 55 + ((double) (wantsHeights.get(i)) / 2)));
                    }
                    else {
                        if(wantsFinished.get(i-1) == true) {
                            wantsHeights.set(i, wantsHeights.get(i)+12+i*2);
                            
                            g2d.fillRoundRect(x, y , 20, wantsHeights.get(i), 10, 10);
                            g2d.drawString(WantsNames.get(i), (CP.getWidth()/5)*4+50+50, (int) (temp3 + 55 + ((double) (wantsHeights.get(i)) / 2)));
                        }
                    }
                }
                
                temp3 += (int) (temp1*temp4);
            }
            }
        
        }
    }
    else if(loading == true && loading2 == true) {

    }
    else if(dbh.loadNames(String.valueOf(TP.TBH.selectedYear), String.valueOf(TP.TBH.selectedMonth)).size() == 0) {
        System.out.println("NEEEINNNN");
        super.paintComponent(g);
        CP.revalidate();
        CP.repaint();
    }
    
    }
    

}