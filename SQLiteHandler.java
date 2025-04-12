package com.mm;

import java.util.ArrayList;

public class SQLiteHandler {
    SQLite db;
    
    SQLiteHandler() {
        db = new SQLite("records");
    }
    public void add(String year, String date, String name, int amount, String category, String subcategory) {
        db.saveOrUpdateRecord(year, date, name, amount, category, subcategory);
    }

    public void remove() {

    }

    public ArrayList<Integer> loadIncomes(String year, String month) {
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<Integer> loadedIncomes = new ArrayList<Integer>();
        loadedAmounts = loadAmounts(year, month);
        loadedCategories = loadCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Income")) {
                loadedIncomes.add(Integer.valueOf(loadedAmounts.get(i)));
            }
        }
        return loadedIncomes;
    }

    public ArrayList<String> loadIncomesNames(String year, String month) {
        ArrayList<String> loadedNames = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedNamesOut = new ArrayList<String>();
        loadedNames = loadNames(year, month);
        loadedCategories = loadCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Income")) {
                loadedNamesOut.add(loadedNames.get(i));
            }
        }
        return loadedNamesOut;
    }

    public ArrayList<Integer> loadExpenses(String year, String month) {
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<Integer> loadExpenses = new ArrayList<Integer>();
        loadedAmounts = loadAmounts(year, month);
        loadedCategories = loadCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                loadExpenses.add(Integer.valueOf(loadedAmounts.get(i)));
            }
        }
        return loadExpenses;
    }

    public ArrayList<String> loadExpensesNames(String year, String month) {
        ArrayList<String> loadedNames = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedNamesOut = new ArrayList<String>();
        loadedNames = loadNames(year, month);
        loadedCategories = loadCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                loadedNamesOut.add(loadedNames.get(i));
            }
        }
        return loadedNamesOut;
    }

    public ArrayList<Integer> loadEInvestments(String year, String month) {
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        ArrayList<Integer> loadExpenses = new ArrayList<Integer>();
        loadedAmounts = loadAmounts(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Investments"))
                    loadExpenses.add(Integer.valueOf(loadedAmounts.get(i)));
                    //System.out.println(loadSubCategories(year, month));
            }
        }
        return loadExpenses;
    }

    public ArrayList<Integer> loadEMusts(String year, String month) {
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        ArrayList<Integer> loadExpenses = new ArrayList<Integer>();
        loadedAmounts = loadAmounts(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Musts"))
                    loadExpenses.add(Integer.valueOf(loadedAmounts.get(i)));
            }
        }
        return loadExpenses;
    }

    public ArrayList<Integer> loadEWants(String year, String month) {
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        ArrayList<Integer> loadExpenses = new ArrayList<Integer>();
        loadedAmounts = loadAmounts(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Wants"))
                    loadExpenses.add(Integer.valueOf(loadedAmounts.get(i)));
            }
        }
        return loadExpenses;
    }

    public ArrayList<String> loadInvestmentsNames(String year, String month) {
        ArrayList<String> loadedNames = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedNamesOut = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        loadedNames = loadNames(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Investments"))
                    loadedNamesOut.add(loadedNames.get(i));
            }
        }
        return loadedNamesOut;
    }

    public ArrayList<String> loadMustsNames(String year, String month) {
        ArrayList<String> loadedNames = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedNamesOut = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        loadedNames = loadNames(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Musts"))
                    loadedNamesOut.add(loadedNames.get(i));
            }
        }
        return loadedNamesOut;
    }

    public ArrayList<String> loadWantsNames(String year, String month) {
        ArrayList<String> loadedNames = new ArrayList<String>();
        ArrayList<String> loadedCategories = new ArrayList<String>();
        ArrayList<String> loadedNamesOut = new ArrayList<String>();
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        loadedNames = loadNames(year, month);
        loadedCategories = loadCategories(year, month);
        loadedSubCategories = loadSubCategories(year, month);
        for(int i = 0; i < loadedCategories.size(); i++) {
            if(loadedCategories.get(i).equals("Expense")) {
                if(loadedSubCategories.get(i).equals("Wants"))
                    loadedNamesOut.add(loadedNames.get(i));
            }
        }
        return loadedNamesOut;
    }
    

    public ArrayList<String> loadNames(String year, String month) {
        ArrayList<String> loadedData = db.getRecordsForMonth(year, month);
        ArrayList<String> loadedNames = new ArrayList<String>();
        for(int i = 0; i  < loadedData.size(); i += 4) {
            loadedNames.add(loadedData.get(i));
        }
        return loadedNames;
    }

    public ArrayList<String> loadAmounts(String year, String month) {
        ArrayList<String> loadedData = db.getRecordsForMonth(year, month);
        ArrayList<String> loadedAmounts = new ArrayList<String>();
        for(int i = 1; i  < loadedData.size(); i += 4) {
            loadedAmounts.add(loadedData.get(i));
        }

        return loadedAmounts;
    }

    public ArrayList<String> loadCategories(String year, String month) {
        ArrayList<String> loadedData = db.getRecordsForMonth(year, month);
        ArrayList<String> loadedCategories = new ArrayList<String>();
        for(int i = 2; i  < loadedData.size(); i += 4) {
            loadedCategories.add(loadedData.get(i));
        }

        return loadedCategories;
    }

    public ArrayList<String> loadSubCategories(String year, String month) {
        ArrayList<String> loadedData = db.getRecordsForMonth(year, month);
        ArrayList<String> loadedSubCategories = new ArrayList<String>();
        for(int i = 3; i  < loadedData.size(); i += 4) {
            loadedSubCategories.add(loadedData.get(i));
        }

        return loadedSubCategories;
    }
    
}
