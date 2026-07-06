package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Professor;

/**
 * Data Access Object (DAO) for the Professor entity.
 * Manages persistence, retrieval, and relational mapping between the Professor model 
 * and the corresponding database storage.
 */

public class ProfessorDAO{

    public void createTable(){
    try{
        Connection conn=DatabaseConnection.getInstance();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS professors (id_prof INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, instrument TEXT)");

    }catch (SQLException e){
        System.out.println("Error in the Data base : "+ e.getMessage());
        }
    }
    
    public void addProfessor(Professor prof){
        try{
            Connection conn=DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO professors (name,instrument) VALUES (?,?)");
            // Filling the placeholders (the 1st ?, then the 2nd ?)
            pstmt.setString(1, prof.getName());
            pstmt.setString(2, prof.getInstrument());
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error in the Data base : "+e.getMessage());
            }
        }
    
    public List<Professor> getAllProfessors(){
        List<Professor> profsList = new ArrayList<>();
        try{
            Connection conn=DatabaseConnection.getInstance();
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM professors");
            while (rs.next()){
                // On extrait les données de la colonne correspondante
                int id = rs.getInt("id_prof");
                String name = rs.getString("name");
                String instrument = rs.getString("instrument");
                
                // On fabrique l'objet Java et on l'ajoute à la liste
                Professor prof = new Professor(id, name, instrument);
                profsList.add(prof);
                }
            if (profsList.isEmpty()){
                System.out.println("The professors table is currently empty.");
                }
        } catch (SQLException e){
            System.out.println("Database error :"+e.getMessage());        
            }
            return profsList;
        }
    
    public boolean deleteProfessor(int id_prof){
        try{
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement pstmt= conn.prepareStatement("DELETE FROM professors WHERE id_prof= ?");
            pstmt.setInt(1,id_prof);
            int deletedLines = pstmt.executeUpdate();        
            // On vérifie si la base a trouvé l'identifiant
            if (deletedLines == 0) {
                System.out.println("No professor find with the Id " + id_prof + "!");
             } else {
             System.out.println("Professor with ID" + id_prof + " has been successfully deleted.");
                     }
            return (deletedLines>0);
            } catch (SQLException e){
                System.out.println("Database error :"+e.getMessage());
                return false;
                }

        }

    public Professor getProfessorById(int id) {
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM professors WHERE id_prof = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                    rs.getInt("id_prof"),
                    rs.getString("name"),
                    rs.getString("instrument")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching professor: " + e.getMessage());
        }
        System.out.println("No professor with this ID found !");
        return null; 
    }

    }
