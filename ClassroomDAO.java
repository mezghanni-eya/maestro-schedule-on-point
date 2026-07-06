package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Classroom;

public class ClassroomDAO{

    public void createTable(){
    try{
        Connection conn=DatabaseConnection.getInstance();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS classrooms (id_room INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, equipment TEXT)");

    }catch (SQLException e){
        System.out.println("Error in the Data base : "+ e.getMessage());
        }
    }
    
    public void addClassroom(Classroom room){
        try{
            Connection conn=DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO classrooms (name,equipment) VALUES (?,?)");
            // Filling the placeholders (the 1st ?, then the 2nd ?)
            pstmt.setString(1, room.getName());
            pstmt.setString(2, room.getEquipment());
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error in the Data base : "+e.getMessage());
            }
        }
    
    public List<Classroom> getAllClassrooms() {
            // On crée une liste vide pour stocker nos professeurs
            List<Classroom> roomsList = new ArrayList<>();
            
            try {
                Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                
                // On exécute la requête ET on rattrape le résultat dans "rs"
                ResultSet rs = stmt.executeQuery("SELECT * FROM classrooms");
                
                // La boucle magique : tant qu'il y a une ligne suivante dans le tableau
                while (rs.next()) {
                    // On extrait les données de la colonne correspondante
                    int id = rs.getInt("id_room");
                    String name = rs.getString("name");
                    String equipment = rs.getString("equipment");
                    
                    // On fabrique l'objet Java et on l'ajoute à la liste
                    Classroom room = new Classroom(id, name, equipment);
                    roomsList.add(room);
                }
                
                // Ton excellente idée : vérifier si la base est vide
                if (roomsList.isEmpty()) {
                    System.out.println("Notice: The classrooms table is currently empty.");
                }
                
            } catch (SQLException e) {
                System.out.println("Error reading the Database : " + e.getMessage());
            }
            
            // On renvoie la liste (pleine, ou vide s'il n'y avait personne)
            return roomsList;
        }
    
    public boolean updateClassroomEquipment(int id_room,String equipment){
            try {
                Connection conn= DatabaseConnection.getInstance();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE classrooms SET equipment= ? WHERE id_room=?");
                pstmt.setString(1, equipment);
                pstmt.setInt(2, id_room);
                int modifiedLines =pstmt.executeUpdate();
                if (modifiedLines>0){
                    System.out.println("Classroom"+ id_room+"'s equipment is successfully updated !");
                 }else{
                    System.out.println("No classroom with the id"+id_room+"!");
                 }
                return (modifiedLines > 0);
            } catch (SQLException e) {
                System.out.println("Data base Error : "+e.getMessage());
                return false;
                }
            }
    
    public boolean deleteClassroom(int id_room){
            try{
                Connection conn = DatabaseConnection.getInstance();
                PreparedStatement pstmt= conn.prepareStatement("DELETE FROM classrooms WHERE id_room= ?");
                pstmt.setInt(1,id_room);
                int deletedLines = pstmt.executeUpdate();        
                // On vérifie si la base a trouvé l'identifiant
                if (deletedLines == 0) {
                    System.out.println("No classroom with the ID " + id_room + "!");
                 } else {
                     System.out.println(" Classroom number " + id_room + " has succesfully been deleted.");
                         }
                return (deletedLines>0);
            } catch (SQLException e){
                System.out.println("Database error :"+e.getMessage());
                return false;
                }

        }
    
    public Classroom getClassroomById(int id) {
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM classrooms WHERE id_room = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Classroom(
                    rs.getInt("id_room"),
                    rs.getString("name"),
                    rs.getString("equipment")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
        System.out.println("No classroom with this ID found !");
        return null; 
    }

    }