package rvt;

public class App {
    public static void main(String[] args) {
        // Inicializēt datubāzi
        DatabaseConnection.initializeDatabase();
        
        // Palaist konsolē saskarni
        ConsoleUI ui = new ConsoleUI();
        ui.start();
        
        // Slēgt savienojumu
        DatabaseConnection.closeConnection();
    }
}
