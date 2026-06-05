package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:data/produkti.db";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("✓ Savienojums ar datubāzi izveidots");
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Kļūda savienojot ar datubāzi: " + e.getMessage());
            return null;
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Izveidot tabulas, ja tās neeksistē
            String createCategoriesTable = "CREATE TABLE IF NOT EXISTS categories (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL UNIQUE)";
            
            String createProductsTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "price REAL NOT NULL," +
                    "category_id INTEGER NOT NULL," +
                    "FOREIGN KEY (category_id) REFERENCES categories(id))";
            
            stmt.execute(createCategoriesTable);
            stmt.execute(createProductsTable);
            
            System.out.println("✓ Datubāze inicializēta");
        } catch (SQLException e) {
            System.err.println("Kļūda inicializējot datubāzi: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Savienojums ar datubāzi slēgts");
            }
        } catch (SQLException e) {
            System.err.println("Kļūda slēdzot savienojumu: " + e.getMessage());
        }
    }
}
