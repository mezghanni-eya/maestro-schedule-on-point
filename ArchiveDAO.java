package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Archive;

public class ArchiveDAO {

    public void createTable(){
        try{
        Connection conn=DatabaseConnection.getInstance();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS archives (id_archive INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, name TEXT, reason TEXT, archive_date DATE )");

        }catch (SQLException e){
            System.out.println("Error in the Data base : "+ e.getMessage());
        }
    }

    public List<Archive> getAllArchives() {
            
            List<Archive> archivesList = new ArrayList<>();
            
            try {
                Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                
                // On exécute la requête ET on rattrape le résultat dans "rs"
                ResultSet rs = stmt.executeQuery("SELECT * FROM archives ORDER BY type, archive_date");
                
                // La boucle magique : tant qu'il y a une ligne suivante dans le tableau
                while (rs.next()) {
                    // On extrait les données de la colonne correspondante
                    int id = rs.getInt("id_archive");
                    String type = rs.getString("type");
                    String name = rs.getString("name");
                    String reason = rs.getString("reason");
                    String archive_date = rs.getString("archive_date");
                    
                    // On fabrique l'objet Java et on l'ajoute à la liste
                    Archive arch = new Archive(id, type, name, reason, archive_date);
                    archivesList.add(arch);
                }
                
                // Ton excellente idée : vérifier si la base est vide
                if (archivesList.isEmpty()) {
                    System.out.println("Notice: The archives table is currently empty.");
                }
                
            } catch (SQLException e) {
                System.out.println("Error reading the Database : " + e.getMessage());
            }
            
            // On renvoie la liste (pleine, ou vide s'il n'y avait personne)
            return archivesList;
        }

    public void addToArchives(Archive arch){
        try{
            Connection conn=DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO archives (type, name, reason, archive_date) VALUES (?,?,?,?)");
            // Filling the placeholders (the 1st ?, then the 2nd ?)
            pstmt.setString(1, arch.getType());
            pstmt.setString(2, arch.getName());
            pstmt.setString(3, arch.getReason());
            pstmt.setString(4, arch.getArchiveDate());
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("🚨 [CRITICAL ARCHIVE ERROR] 🚨");
            e.printStackTrace();
            System.out.println("Error in the Data base : "+e.getMessage());
            }
        }

    public List<Archive> getArchivesByNameAndType(String name, String type) {
        List<Archive> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM archives WHERE name LIKE ? AND type = ? ORDER BY archive_date");
            pstmt.setString(1, "%" + name + "%"); // Le % permet de trouver même si on tape juste une partie du nom
            pstmt.setString(2, type);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_archive");
                String archiveType = rs.getString("type");
                String archiveName = rs.getString("name");
                String reason = rs.getString("reason");
                String date = rs.getString("archive_date");
                
                list.add(new Archive(id, archiveType, archiveName, reason, date));
            }
        } catch (SQLException e) {
            System.out.println("Error searching archives: " + e.getMessage());
        }
        return list;
    }

    }


    

