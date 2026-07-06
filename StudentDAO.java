package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Student;

public class StudentDAO{

    public void createTable(){
    try{
        Connection conn=DatabaseConnection.getInstance();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Students (id_stud INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, instrument TEXT, level TEXT)");

    }catch (SQLException e){
        System.out.println("Error in the Data base : "+ e.getMessage());
        }
    }
    
    public void addStudent(Student stud){
        try{
            Connection conn=DatabaseConnection.getInstance();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Students (name,instrument,level) VALUES (?,?,?)");
            // Filling the placeholders (the 1st ?, then the 2nd ?)
            pstmt.setString(1, stud.getName());
            pstmt.setString(2, stud.getInstrument());
            pstmt.setString(3, stud.getLevel());
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error in the Data base : "+e.getMessage());
            }
        }
    
    public List<Student> getAllStudents(){
        List<Student> studsList = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getInstance();
            Statement stmt = conn.createStatement();
            
            // On exécute la requête ET on rattrape le résultat dans "rs"
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            
            // La boucle magique : tant qu'il y a une ligne suivante dans le tableau
            while (rs.next()) {
                // On extrait les données de la colonne correspondante
                int id = rs.getInt("id_stud");
                String name = rs.getString("name");
                String instrument = rs.getString("instrument");
                String level = rs.getString("level");
                // On fabrique l'objet Java et on l'ajoute à la liste
                Student stud = new Student(id, name, instrument,level);
                studsList.add(stud);
            }
            
            // Ton excellente idée : vérifier si la base est vide
            if (studsList.isEmpty()) {
                System.out.println("Notice: The students table is currently empty.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error reading the Database : " + e.getMessage());
            }
        
        
        // On renvoie la liste (pleine, ou vide s'il n'y avait personne)
        return studsList;
        }

    public boolean updateStudentLevel(int id_stud,String level){
            try {
                Connection conn= DatabaseConnection.getInstance();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET level= ? WHERE id_stud=?");
                pstmt.setString(1, level);
                pstmt.setInt(2, id_stud);
                int modifiedLines =pstmt.executeUpdate();
                if (modifiedLines>0){
                    System.out.println("Student"+ id_stud+"'s level is successfully updated !");
                 }else{
                    System.out.println("No student with the id"+id_stud+"!");
                 }
                return (modifiedLines > 0);
            } catch (SQLException e) {
                System.out.println("Data base Error : "+e.getMessage());
                return false;
                }
            }
    
    public boolean updateStudentInstrument(int id_stud,String instrument){
            try {
                Connection conn= DatabaseConnection.getInstance();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET instrument= ? WHERE id_stud=?");
                pstmt.setString(1, instrument);
                pstmt.setInt(2, id_stud);
                int modifiedLines =pstmt.executeUpdate();
                if (modifiedLines>0){
                    System.out.println("Student"+ id_stud+"'s instrument is successfully updated !");
                 }else{
                    System.out.println("No student with the id"+id_stud+"!");
                 }
                return (modifiedLines > 0);
            } catch (SQLException e) {
                System.out.println("Data base Error : "+e.getMessage());
                return false;
                }
            }
    
    public Student getStudentById(int id) {
       
        String query = "SELECT * FROM students WHERE id_stud = ?";
        
        try {
            Connection conn = DatabaseConnection.getInstance();
            
            // Le "try" avec parenthèses fermera le PreparedStatement tout seul !
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                
                // Ce "try" fermera le ResultSet tout seul pour déverrouiller la base !
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new Student(
                            rs.getInt("id_stud"),
                            rs.getString("name"),
                            rs.getString("instrument"),
                            rs.getString("level")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
        return null; 
    }
    
    public boolean deleteStudent(int id_stud){
            try{
                Connection conn = DatabaseConnection.getInstance();
                PreparedStatement pstmt= conn.prepareStatement("DELETE FROM students WHERE id_stud= ?");
                pstmt.setInt(1,id_stud);
                int deletedLines = pstmt.executeUpdate();        
                // On vérifie si la base a trouvé l'identifiant
                if (deletedLines == 0) {
                    System.out.println("Attention : Aucun étudiant trouvé avec l'identifiant " + id_stud + ".");
                 } else {
                     System.out.println("Succès : L'étudiant numéro " + id_stud + " a été supprimé de la base.");
                         }
                return (deletedLines>0);
            } catch (SQLException e){
                System.out.println("Database error :"+e.getMessage());
                return false;
                }

        }

}
    
