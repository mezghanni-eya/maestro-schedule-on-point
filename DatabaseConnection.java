package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // 1. L'adresse de l'entrepôt : on indique qu'on utilise SQLite et le nom du fichier
    private static final String URL = "jdbc:sqlite:conservatoire.db";
    
    // 2. Le câble de connexion (unique pour tout le programme)
    private static Connection instance = null;

    // 3. Le constructeur privé (personne ne peut instancier cette classe de l'extérieur)
    private DatabaseConnection() {
    }

    // 4. Le guichet d'accès : c'est cette méthode que tes autres fichiers appelleront
    public static Connection getInstance() {
        try {
            // Si la connexion n'existe pas encore ou a été fermée, on l'ouvre
            if (instance == null || instance.isClosed()) {
                // Étape A : On réveille le traducteur (le fichier .jar)
                Class.forName("org.sqlite.JDBC");
                
                // Étape B : On branche le câble au fichier
                instance = DriverManager.getConnection(URL);
                System.out.println("Pont sécurisé : Connexion à SQLite établie avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de base de données : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Alerte rouge : Java ne trouve pas le driver SQLite (.jar) !");
        }
        
        return instance;
    }
}