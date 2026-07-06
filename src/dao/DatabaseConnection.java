package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class manages the lifecycle of the connection 
 * to the SQLite database. Implementing the Singleton design pattern, 
 * it ensures that only a single, unique connection instance is created 
 * and shared across the entire application, preventing resource leaks 
 * and maximizing performance.
 */

public class DatabaseConnection {
    
    // 1. Database address: specfies SQLite usage and the database file name
    private static final String URL = "jdbc:sqlite:conservatoire.db";
    
    // 2. The connection cable (unique instance shared across the entire program)
    private static Connection instance = null;

    // 3. Private constructor (prevents external instantiation of this class)
    private DatabaseConnection() {
    }

    // 4. The access gateway: this is the method the other files will call
    public static Connection getInstance() {
        try {
            // If the connection doesn't exist yet or was closed, open it
            if (instance == null || instance.isClosed()) {
                // Step A: Register the driver (wake up the translator from the .jar file)
                Class.forName("org.sqlite.JDBC");
                
                // Step B: Connect the cable to the database file
                instance = DriverManager.getConnection(URL);
                System.out.println("Successfully connected to SQLite! ");
            }
        } catch (SQLException e) {
            System.out.println("Database error : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Warning : Java didn't find driver SQLite (.jar) !");
        }
        
        return instance;
    }
}
