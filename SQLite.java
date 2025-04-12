package com.mm;
import java.sql.*;
import java.util.ArrayList;

public class SQLite {
    private Connection connection;

    // Konstruktor – inicializuje spojení s databází a vytvoří tabulku, pokud neexistuje
    public SQLite(String dbFileName) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
            System.out.println("Database connection established.");
            createTableIfNotExists();
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Vytvoří tabulku 'records', pokud ještě neexistuje
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS records (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "year TEXT," +
                     "date TEXT," +
                     "name TEXT," +
                     "amount INTEGER," +
                     "category TEXT," +
                     "subcategory TEXT" +
                     ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Uloží nový záznam nebo aktualizuje existující (podle kombinace year, date a name)
    public void saveOrUpdateRecord(String year, String date, String name, int amount, String category, String subcategory) {
        String selectSql = "SELECT id FROM records WHERE year = ? AND date = ? AND name = ?";
        String updateSql = "UPDATE records SET amount = ?, category = ?, subcategory = ? WHERE id = ?";
        String insertSql = "INSERT INTO records (year, date, name, amount, category, subcategory) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setString(1, year);
            selectStmt.setString(2, date);
            selectStmt.setString(3, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {  // Záznam existuje, provedeme update
                int id = rs.getInt("id");
                try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, amount);
                    updateStmt.setString(2, category);
                    updateStmt.setString(3, subcategory);
                    updateStmt.setInt(4, id);
                    updateStmt.executeUpdate();
                    System.out.println("Record updated.");
                }
            } else {  // Záznam neexistuje, vložíme nový
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setString(1, year);
                    insertStmt.setString(2, date);
                    insertStmt.setString(3, name);
                    insertStmt.setInt(4, amount);
                    insertStmt.setString(5, category);
                    insertStmt.setString(6, subcategory);
                    insertStmt.executeUpdate();
                    System.out.println("Record inserted.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving or updating record: " + e.getMessage());
        }
    }

    // Vrátí všechny záznamy pro daný měsíc a rok (vstupní formát "MM/YYYY")
    public ArrayList<String> getRecordsForMonth(String year, String month) {
        ArrayList<String> ALret = new ArrayList<String>();
        /*String[] parts = monthYear.split("/");
        if (parts.length != 2) {
            System.out.println("Invalid monthYear format. Use MM/YYYY.");
            return null;
        }
        String month = parts[0];
        String year = parts[1];*/
        String sql = "SELECT * FROM records WHERE date = ? AND year = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, month);
            pstmt.setString(2, year);
            ResultSet rs = pstmt.executeQuery();

            //System.out.println("Records for " + month + " " + year + ":");
            boolean found = false;
            while (rs.next()) {
                found = true;
                ALret.add(String.valueOf(rs.getString("name")));
                ALret.add(String.valueOf(rs.getInt("amount")));
                ALret.add(String.valueOf(rs.getString("category")));
                ALret.add(String.valueOf(rs.getString("subcategory")));

            }
            if (!found) {
                System.out.println("No records found for " + month + " " + year + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
        return ALret;
    }
    
    public void deleteRecordByMonthAndName(String year, String month, String name) {
        String sql = "DELETE FROM records WHERE date = ? AND year = ? AND name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, month);
            pstmt.setString(2, year);
            pstmt.setString(3, name);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Deleted " + affectedRows + " record(s) for " + month + "/" + year + " with name " + name + ".");
        } catch (SQLException e) {
            System.out.println("Error deleting record by month and name: " + e.getMessage());
        }
    }
    
    // Smaže všechny záznamy z tabulky
    public void deleteAllRecords() {
        String sql = "DELETE FROM records";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Deleted " + affectedRows + " records from the database.");
        } catch (SQLException e) {
            System.out.println("Error deleting all records: " + e.getMessage());
        }
    }

    // Smaže všechny záznamy pro konkrétní měsíc a rok (vstupní formát "MM/YYYY")
    public void deleteRecordsForMonth(String monthYear) {
        String[] parts = monthYear.split("/");
        if (parts.length != 2) {
            System.out.println("Invalid monthYear format. Use MM/YYYY.");
            return;
        }
        String month = parts[0];
        String year = parts[1];
        String sql = "DELETE FROM records WHERE date = ? AND year = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, month);
            pstmt.setString(2, year);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Deleted " + affectedRows + " records for " + monthYear + ".");
        } catch (SQLException e) {
            System.out.println("Error deleting records for month: " + e.getMessage());
        }
    }

    // Uzavře spojení s databází
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }

}