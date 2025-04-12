package com.mm;

import java.awt.Color;
import java.util.Calendar;

public class TopButtonHandler {
    TopPanel TP;
    int selectedMonth;
    int selectedYear;
    int currentMonthOffset = 0;
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
    int currentYearOffset = 0;
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    TopButtonHandler(TopPanel TP) {
        this.TP = TP;
        rightButtonsText();
        leftButtonsText();
        
    }

    public void buttonPressL(JRoundedButton button) {
        disableLeftButtons();
        button.setBackground(new Color(13,71,161));
        selectedMonth = Integer.valueOf(button.getText());
        TP.currentDate.setText(String.valueOf(selectedMonth)+"/"+String.valueOf(selectedYear));
    }

    public void buttonPressR(JRoundedButton button) {
        disableRightButtons();
        button.setBackground(new Color(13,71,161));
        selectedYear = Integer.valueOf(button.getText());
        TP.currentDate.setText(String.valueOf(selectedMonth)+"/"+String.valueOf(selectedYear));
    }

    public void disableLeftButtons() {
        TP.l1selector.setBackground(new Color(22,22,22));
        TP.l2selector.setBackground(new Color(22,22,22));
        TP.l3selector.setBackground(new Color(22,22,22));
        TP.l4selector.setBackground(new Color(22,22,22));
        TP.l5selector.setBackground(new Color(22,22,22));
        TP.l6selector.setBackground(new Color(22,22,22));
    }

    public void disableRightButtons() {
        TP.r1selector.setBackground(new Color(22,22,22));
        TP.r2selector.setBackground(new Color(22,22,22));
        TP.r3selector.setBackground(new Color(22,22,22));
    }

    public void selectCurrentMonth() {
        System.out.println(currentMonth);
        for(int i = 0; i <= 10; i++) {
            if(TP.l1selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l1selector);
                break;
            }
            else if(TP.l2selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l2selector);
                break;
            }
            else if(TP.l3selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l3selector);
                break;
            }
            else if(TP.l4selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l4selector);
                break;
            }
            else if(TP.l5selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l5selector);
                break;
            }
            else if(TP.l6selector.getText().contains(String.valueOf(currentMonth))) {
                buttonPressL(TP.l6selector);
                break;
            }
            else {
                leftSwitches(1);
            }
        }
        TP.currentDate.setText(String.valueOf(selectedMonth)+"/"+String.valueOf(selectedYear));
    }

    public void leftSwitches(int code) {
        if(code == 0) {
            if(currentMonthOffset > 0) {
                currentMonthOffset -= 1;
            }
        }
        else if(code == 1) {
            if(currentMonthOffset < 6) {
                currentMonthOffset += 1;
            }
        }
        leftButtonsText();
        leftButtonsCheck();
    }

    public void rightSwitches(int code) {
        if(code == 0) {
            if(currentYearOffset < 0) {
                currentYearOffset += 1;
            }
        }
        if(code == 1) {
            if(currentYearOffset > -30) {
                currentYearOffset -= 1;
            }
        }
        rightButtonsText();
        rightButtonsCheck();
    }

    public void leftButtonsCheck() {
        if(Integer.valueOf(TP.l1selector.getText()) == selectedMonth) {
            buttonPressL(TP.l1selector);
        }
        else if(Integer.valueOf(TP.l2selector.getText()) == selectedMonth) {
            buttonPressL(TP.l2selector);
        }
        else if(Integer.valueOf(TP.l3selector.getText()) == selectedMonth) {
            buttonPressL(TP.l3selector);
        }
        if(Integer.valueOf(TP.l4selector.getText()) == selectedMonth) {
            buttonPressL(TP.l4selector);
        }
        else if(Integer.valueOf(TP.l5selector.getText()) == selectedMonth) {
            buttonPressL(TP.l5selector);
        }
        else if(Integer.valueOf(TP.l6selector.getText()) == selectedMonth) {
            buttonPressL(TP.l6selector);
        }
    }

    public void leftButtonsText() {
        disableLeftButtons();
        TP.l1selector.setText(String.valueOf(1 + currentMonthOffset));
        TP.l2selector.setText(String.valueOf(1 + 1 + currentMonthOffset));
        TP.l3selector.setText(String.valueOf(1 + 2 + currentMonthOffset));
        TP.l4selector.setText(String.valueOf(1 + 3 + currentMonthOffset));
        TP.l5selector.setText(String.valueOf(1 + 4 + currentMonthOffset));
        TP.l6selector.setText(String.valueOf(1 + 5 + currentMonthOffset));
    }

    public void rightButtonsCheck() {
        if(Integer.valueOf(TP.r1selector.getText()) == selectedYear) {
            buttonPressR(TP.r1selector);
        }
        else if(Integer.valueOf(TP.r2selector.getText()) == selectedYear) {
            buttonPressR(TP.r2selector);
        }
        else if(Integer.valueOf(TP.r3selector.getText()) == selectedYear) {
            buttonPressR(TP.r3selector);
        }
    }

    public void rightButtonsText() {
        disableRightButtons();
        TP.r1selector.setText(String.valueOf(currentYear + currentYearOffset));
        TP.r2selector.setText(String.valueOf(currentYear - 1 + currentYearOffset));
        TP.r3selector.setText(String.valueOf(currentYear - 2 + currentYearOffset));
    }

}
