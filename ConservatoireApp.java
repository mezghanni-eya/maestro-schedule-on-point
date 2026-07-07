package main;
// On importe les outils depuis les autres dossiers
import dao.ArchiveDAO;
import dao.ClassroomDAO;
import dao.ProfessorDAO;
import dao.StudentDAO;
import java.util.List;
import java.util.Scanner;
import models.Archive;
import models.Classroom;
import models.Professor;
import models.Student;

public class ConservatoireApp {
    
    // Le point d'entrée officiel du programme
    public static void main(String[] args) {
    
        System.out.println("--- Starting Conservatoire App ---");
        ProfessorDAO profDAO = new ProfessorDAO();
        System.out.println("Checking database structure...");
        profDAO.createTable();  
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.createTable();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        classroomDAO.createTable();
        ArchiveDAO archiveDAO = new ArchiveDAO();
        archiveDAO.createTable();     
        System.out.println("--- All systems initialized! ---");
        // IV. MENU INTERACTIF
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // La boucle qui maintient le programme éveillé
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("0. Exit");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Professors");
            System.out.println("3. Manage Classrooms");
            System.out.println( "4. View Archives");
            System.out.print("Tap your choice here : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Astuce : on vide la mémoire "Entrée" du clavier
            switch (choice) {
                
                case 0:
                    System.out.println("-> Closing the program. À bientôt !");
                    running = false; // On passe à faux pour casser la boucle
                    break;
                
                case 1:{
                    System.out.println("--- Managing students --- ");
                    System.out.println("0. Cancel");
                    System.out.println("1. Add a Student");
                    System.out.println("2. Find a Student");
                    System.out.println("3. Update a Student's level");
                    System.out.println("4. Update a student's instrument");
                    System.out.println("5. Delete a student");
                    System.out.println("6. Filter Students by Instrument");
                    System.out.println("Tap your choice here : ");
                    int choiceSTD=scanner.nextInt();
                    scanner.nextLine();

                    switch (choiceSTD){
                        case 0 :{
                            break;
                        }
                        case 1:{
                            System.out.println("--- Adding a new student ---");
                            // On pose les questions et on lit les réponses
                            System.out.print("Name : ");
                            String name = scanner.nextLine();
                                
                            System.out.print("Instrument : ");
                            String instrument = scanner.nextLine();
                                
                            System.out.print("Level : ");
                            String level = scanner.nextLine();
                                
                            // On assemble l'objet étudiant (l'ID 0 est temporaire, SQLite gère le vrai ID)
                            Student newStudent = new Student(0, name, instrument, level);
                                
                            // Le moment magique : on envoie à la base de données !
                            studentDAO.addStudent(newStudent);
                                
                            System.out.println("-> Student succesfully added !");
                            break;
                        }
                        case 2:{
                          System.out.println("--- 🔎 Find a Student ---");
                          System.out.print("Enter the student's name : ");
                          String searchName = scanner.nextLine();
                          List<Student> searchResults = studentDAO.searchStudentsByName(searchName);
                          System.out.println("\n--- 📋 Results ---");
                         if (searchResults.isEmpty()) {
                            System.out.println("No results found.");
                         } else {
                            System.out.println("✅ " + searchResults.size() + " match(es) found !");
                            System.out.println("-------------------------");
                            for (Student s : searchResults) {
                                System.out.println("[ID: " + s.getId() + "] " + s.getName() + " | " + s.getInstrument() + " (" + s.getLevel() + ")");
                            }
                        }
                        break;
                        }
                        case 3:{
                            System.out.println("--- Updating a student's level ---");
                            // On pose les questions et on lit les réponses
                            System.out.print("Enter the student's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            
                            List<Student> searchResults = studentDAO.searchStudentsByName(searchName);
                            
                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No student found with the name : " + searchName);
                                break; // On annule la suppression s'il n'y a personne
                            }
                            
                            // 2. L'affichage magnifique
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Student s : searchResults) {
                                // Affichage : [ID: X] Nom | Instrument (Niveau)
                                System.out.println("[ID: " + s.getId() + "] " + s.getName() + " | " + s.getInstrument() + " (" + s.getLevel() + ")");
                            }
                            System.out.println("-------------------------\n");
                            System.out.print("Enter the student's ID : ");
                            int id_stud = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New level of student : ");
                            String newLevel = scanner.nextLine();    
                            // Le moment magique : on envoie à la base de données !
                            studentDAO.updateStudentLevel(id_stud,newLevel);
                                
                            System.out.println("-> Student's level succesfully updated !");
                            break;
                        }
                        case 4:{
                            System.out.println("----Updating a student's instrument---");
                        
                            // On pose les questions et on lit les réponses
                            System.out.print("Enter the student's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            
                            List<Student> searchResults = studentDAO.searchStudentsByName(searchName);
                            
                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No student found with the name : " + searchName);
                                break; // On annule la suppression s'il n'y a personne
                            }
                            
                            // 2. L'affichage magnifique
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Student s : searchResults) {
                                // Affichage : [ID: X] Nom | Instrument (Niveau)
                                System.out.println("[ID: " + s.getId() + "] " + s.getName() + " | " + s.getInstrument() + " (" + s.getLevel() + ")");
                            }
                            System.out.println("-------------------------\n");
                            System.out.print("Enter the student's ID : ");
                            int id_stud = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New instrument of student : ");
                            String newInstru = scanner.nextLine();    
                            // Le moment magique : on envoie à la base de données !
                            studentDAO.updateStudentInstrument(id_stud,newInstru);
                                
                            System.out.println("-> Student's instrument successfully updated !");
                            break; 
                        }
                        case 5:{
                            System.out.println("--- Deleting a student ---");
                            System.out.print("Enter the student's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            
                            List<Student> searchResults = studentDAO.searchStudentsByName(searchName);
                            
                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No student found with the name : " + searchName);
                                break; // On annule la suppression s'il n'y a personne
                            }
                            
                            // 2. L'affichage magnifique
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Student s : searchResults) {
                                // Affichage : [ID: X] Nom | Instrument (Niveau)
                                System.out.println("[ID: " + s.getId() + "] " + s.getName() + " | " + s.getInstrument() + " (" + s.getLevel() + ")");
                            }
                            System.out.println("-------------------------\n");
                            System.out.print("Enter the student's ID : ");
                            int id_stud = scanner.nextInt();
                            scanner.nextLine();
                            Student studentToDelete = studentDAO.getStudentById(id_stud);
                            if (studentToDelete == null) {
                                System.out.println("⚠️ No student found with ID " + id_stud);
                            } else {
                            // 2. On demande le motif
                            System.out.print("Reason for departure (Graduated, Resigned, etc.) : ");
                            String reason = scanner.nextLine();

                            // 3. Astuce de Pro : On récupère la date d'aujourd'hui automatiquement !
                            String today = java.time.LocalDate.now().toString();
                            // 4. On prépare l'archive
                            Archive newArchive = new Archive(0, "STUDENT", studentToDelete.getName(), reason, today);    
                            // 5. Le transfert magique !
                            archiveDAO.addToArchives(newArchive);       // Étape A : Sauvegarde dans l'historique
                            studentDAO.deleteStudent(id_stud);
                            System.out.println("-> Student successfully archived and deleted !");}
                            break;  
                        }                   
                        case 6: {
                            System.out.println("--- 🔎 Filter Students by Instrument ---");
                            System.out.print("Enter the instrument (e.g., Piano, Guitar) : ");
                            String searchInst = scanner.nextLine();
                            List<Student> studentsByInst = studentDAO.getStudentsByInstrument(searchInst);
                            System.out.println("\n--- 📋 Results ---");
                            if (studentsByInst.isEmpty()) {
                                System.out.println("0 results found for : " + searchInst);
                            } else {
                                // Affichage du nombre de résultats trouvés
                                System.out.println("✅ " + studentsByInst.size() + " student(s) found !");
                                System.out.println("-------------------------");
                                for (Student s : studentsByInst) {
                                    System.out.println("->  [" + s.getId()+ "] "+ s.getName() + " | Level : " + s.getLevel());
                                }
                            }
                        break;
                        }
                    }  break;
                }            
                case 2:{
                    System.out.println("--- Managing Professors --- ");
                    System.out.println("0. Cancel");    
                    System.out.println("1. Add a Professor");
                    System.out.println("2. Find a Professor");
                    System.out.println("3. Filter professors by instruments");
                    System.out.println("4. Delete a Professor");
                    System.out.println("Tap your choice here : ");
                    int choixProf=scanner.nextInt();
                    scanner.nextLine();                    
                    switch(choixProf){
                        case 0 : {
                            break;
                        }
                        case 1 : {
                            System.out.println("--- Adding a new professor --- ");
                        
                            // On pose les questions et on lit les réponses
                            System.out.print("Name : ");
                            String nameP = scanner.nextLine();
                                
                            System.out.print("Instrument : ");
                            String instrumentP = scanner.nextLine();
                                
                                
                            // On assemble l'objet professeur (l'ID 0 est temporaire, SQLite gère le vrai ID)
                            Professor newProf = new Professor(0, nameP, instrumentP);
                                
                            // Le moment magique : on envoie à la base de données !
                            profDAO.addProfessor(newProf);
                                
                            System.out.println("-> Professor succesfully added !");
                            break;
                        }
                        case 2 : {
                            System.out.println("--- 🔎 Find a Professor ---");
                            System.out.print("Enter the professor's name : ");
                            String searchName = scanner.nextLine();
                            List<Professor> searchResults = profDAO.searchProfessorsByName(searchName);
                            System.out.println("\n--- 📋 Results ---");
                            if (searchResults.isEmpty()) {
                                System.out.println("No results found.");
                            } else {
                                System.out.println("✅ " + searchResults.size() + " match(es) found !");
                                System.out.println("-------------------------");
                                for (Professor p : searchResults) {
                                    System.out.println("[ID: " + p.getId() + "] " + p.getName() + " | Instrument: " + p.getInstrument());
                                }
                            }
                         break;
                        }   
                        case 3: {
                            System.out.println("--- 🔎 Filter Professors by Instrument ---");
                            System.out.print("Enter the instrument (e.g., Piano, Violin) : ");
                            String searchInst = scanner.nextLine();
                            List<Professor> profsByInst = profDAO.getProfessorsByInstrument(searchInst);
                            System.out.println("\n--- 📋 Results ---");
                            if (profsByInst.isEmpty()) {
                                System.out.println("0 results found for : " + searchInst);
                            } else {
                                System.out.println("✅ " + profsByInst.size() + " professor(s) found !");
                                System.out.println("-------------------------");
                                for (Professor p : profsByInst) {
                                    System.out.println("-> " + p.getName() + " | Instrument: " + p.getInstrument());
                                }
                            }
                            System.out.println("-------------------------\n");
                          break;
                        }
                        case 4 : {
                            System.out.println("--- Deleting a professor ---");
                            System.out.print("Enter the professor's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            List<Professor> searchResults = profDAO.searchProfessorsByName(searchName);

                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No professor found with the name : " + searchName);
                                break;
                            }
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Professor p : searchResults) {
                                System.out.println("[ID: " + p.getId() + "] " + p.getName() + " | Instrument: " + p.getInstrument());
                            }
                            System.out.println("-------------------------\n");                          
                            System.out.print("Enter the professor's ID : ");
                            int id_prof = scanner.nextInt();
                            scanner.nextLine();
                            Professor profToDelete = profDAO.getProfessorById(id_prof);
                            if (profToDelete == null) {
                                System.out.println("⚠️ No professor found with ID " + id_prof);
                            } else {
                                // 2. On demande le motif
                                System.out.print("Reason for departure (Retirement, Resigned, etc.) : ");
                                String reason = scanner.nextLine();
                                String today = java.time.LocalDate.now().toString();

                                // 3. On prépare l'archive (Attention, le type est bien "PROFESSOR")
                                Archive newArchive = new Archive(0, "PROFESSOR", profToDelete.getName(), reason, today);
                                
                                // 4. Le transfert
                                archiveDAO.addToArchives(newArchive);
                            profDAO.deleteProfessor(id_prof);    
                            System.out.println("-> Professor successfully archived and deleted !");
                            break; 
                        }  
                        } 
                      }break;
                    }   
                case 3:{
                    System.out.println("--- Managing Classrooms --- ");   
                    System.out.println("0. Cancel");
                    System.out.println("1. Add a classroom");
                    System.out.println("2. Update a classroom's equipment");
                    System.out.println("3. Find a classroom");
                    System.out.println("4. Filter classrooms by equipment");
                    System.out.println("5. Delete a classroom");
                    System.out.print("Tap your choice here : ");
                    int choixRoom=scanner.nextInt();
                    scanner.nextLine();

                    switch (choixRoom){
                        case 0 : {
                            break;
                        }
                        case 1 :{
                            System.out.println("--- Adding a new classroom --- ");
                        
                            // On pose les questions et on lit les réponses
                            System.out.print("Name : ");
                            String name = scanner.nextLine();
                                                    
                            System.out.print("Equipment : ");
                            String equipment = scanner.nextLine();
                                                    
                                                    
                            // On assemble l'objet professeur (l'ID 0 est temporaire, SQLite gère le vrai ID)
                            Classroom newRoom = new Classroom(0, name, equipment);
                                                    
                            // Le moment magique : on envoie à la base de données !
                            classroomDAO.addClassroom(newRoom);
                                                    
                            System.out.println("-> Classroom succesfully added !");
                            break;

                        }
                        case 2 : {
                            System.out.println("--- Updating a classroom's equipment --- ");
                            System.out.print("Enter the classroom's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            List<Classroom> searchResults = classroomDAO.searchClassroomsByName(searchName);

                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No classroom found with the name : " + searchName);
                                break;
                            }
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Classroom c : searchResults) {
                                System.out.println("[ID: " + c.getId() + "] " + c.getName() + " | Equipment: " + c.getEquipment());
                            }
                            System.out.println("-------------------------\n");
                            
                            System.out.print("Enter the classroom's ID : ");
                            int id_room = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New equipment of classroom : ");
                            String newEq = scanner.nextLine();    
                            // Le moment magique : on envoie à la base de données !
                            classroomDAO.updateClassroomEquipment(id_room,newEq);
                                
                            System.out.println("-> Classroom's equipment successfully updated !");
                            break; 
                        }
                        case 3 : {
                            System.out.println("--- 🔎 Find a Classroom ---");
                            System.out.print("Enter the classroom's name : ");
                            String searchName = scanner.nextLine();
                            List<Classroom> searchResults = classroomDAO.searchClassroomsByName(searchName);
                            System.out.println("\n--- 📋 Results ---");
                            if (searchResults.isEmpty()) {
                                System.out.println("0 results found for : " + searchName);
                            } else {
                                System.out.println("✅ " + searchResults.size() + " match(es) found !");
                                System.out.println("-------------------------");
                                for (Classroom c : searchResults) {
                                    System.out.println("[ID: " + c.getId() + "] " + c.getName() + " | Equipment: " + c.getEquipment());
                                }
                            }
                            break;
                        }
                        case 4 : {
                            System.out.println("--- 🔎 Filter Classrooms by Equipment ---");
                            System.out.print("Enter the equipment (e.g., Piano, Projector, Chairs) : ");
                            String searchEq = scanner.nextLine();
                            
                            // Appel de la méthode de filtrage par équipement
                            List<Classroom> roomsByEq = classroomDAO.searchClassroomsByEquipment(searchEq);
                            
                            System.out.println("\n--- 📋 Results ---");
                            if (roomsByEq.isEmpty()) {
                                System.out.println("0 results found for : " + searchEq);
                            } else {
                                System.out.println("✅ " + roomsByEq.size() + " classroom(s) found !");
                                System.out.println("-------------------------");
                                for (Classroom c : roomsByEq) {
                                    System.out.println("-> [ID: " + c.getId() + "] " + c.getName() + " | Equipment: " + c.getEquipment());
                                }
                            }
                            System.out.println("-------------------------\n");
                            break;
                        }
                        case 5 : {
                            System.out.println("--- Deleting a classroom ---");
                            System.out.print("Enter the classroom's name (or a part of it) : ");
                            String searchName = scanner.nextLine();
                            List<Classroom> searchResults = classroomDAO.searchClassroomsByName(searchName);

                            if (searchResults.isEmpty()) {
                                System.out.println("⚠️ No classroom found with the name : " + searchName);
                                break;
                            }
                            System.out.println("\n--- 🔍 Search Results ---");
                            for (Classroom c : searchResults) {
                                System.out.println("[ID: " + c.getId() + "] " + c.getName() + " | Equipment: " + c.getEquipment());
                            }
                            System.out.println("-------------------------\n");                          
                            System.out.print("Enter the classroom's ID : ");
                            int id_room = scanner.nextInt();
                            scanner.nextLine();
                            Classroom roomToDelete = classroomDAO.getClassroomById(id_room);
                            if (roomToDelete == null) {
                                System.out.println("⚠️ No classroom found with ID " + id_room);
                            } else {
                                // 2. On demande le motif (Travaux, Détruite, Changement d'usage, etc.)
                                System.out.print("Reason for removal (Renovation, Destroyed, etc.) : ");
                                String reason = scanner.nextLine();
                                String today = java.time.LocalDate.now().toString();
                                // 3. On prépare l'archive avec le type "CLASSROOM"
                                Archive newArchive = new Archive(0, "CLASSROOM", roomToDelete.getName(), reason, today);             
                                // 4. Le grand transfert
                                archiveDAO.addToArchives(newArchive);
                                classroomDAO.deleteClassroom(id_room);                           
                                System.out.println("-> Classroom successfully archived and deleted !");
                                }
                                break; 
                            }
                        }
                    break;
                }                     
                case 4: {
                    System.out.println("--- Opening Archives ---");
                    System.out.println("0. Cancel");
                    System.out.println("1. Show all archives");
                    System.out.println("2. Search a deleted student");
                    System.out.println("3. Search a deleted professor");
                    System.out.println("4. Search a deleted classroom");
                    System.out.print("Tap your choice here : ");
                    
                    int archiveChoice = scanner.nextInt();
                    scanner.nextLine(); // On nettoie le clavier
                    
                    switch (archiveChoice) {
                        case 0 :{
                            break;
                        }
                        case 1: {
                            System.out.println("\n--- All Archives ---");
                            List<Archive> allArch = archiveDAO.getAllArchives();
                            for (Archive a : allArch) {
                                System.out.println("[" + a.getType() + "] " + a.getName() + " | Date: " + a.getArchiveDate() + " | Reason: " + a.getReason());
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("\n--- Search Student Archive ---");
                            System.out.print("Enter the student's name : ");
                            String searchName = scanner.nextLine();
                            List<Archive> studentsArch = archiveDAO.getArchivesByNameAndType(searchName, "STUDENT");
                            if(studentsArch.isEmpty()) System.out.println("No archived student found with this name.");
                            for (Archive a : studentsArch) {
                                System.out.println("-> " + a.getName() + " | Left on: " + a.getArchiveDate() + " | Reason: " + a.getReason());
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("\n--- Search Professor Archive ---");
                            System.out.print("Enter the professor's name : ");
                            String searchName = scanner.nextLine();
                            List<Archive> profsArch = archiveDAO.getArchivesByNameAndType(searchName, "PROFESSOR");
                            if(profsArch.isEmpty()) System.out.println("No archived professor found with this name.");
                            for (Archive a : profsArch) {
                                System.out.println("-> " + a.getName() + " | Left on: " + a.getArchiveDate() + " | Reason: " + a.getReason());
                            }
                            break;
                        }
                        case 4: {
                            System.out.println("\n--- Search Classroom Archive ---");
                            System.out.print("Enter the classroom name : ");
                            String searchName = scanner.nextLine();
                            List<Archive> roomsArch = archiveDAO.getArchivesByNameAndType(searchName, "CLASSROOM");
                            if(roomsArch.isEmpty()) System.out.println("No archived classroom found.");
                            for (Archive a : roomsArch) {
                                System.out.println("-> " + a.getName() + " | Closed on: " + a.getArchiveDate() + " | Reason: " + a.getReason());
                            }
                            break;
        }
        default:
            System.out.println("-> Invalid choice for archives.");
    }
    break;
                }
                default:
                    System.out.println("-> Oops, this option doesn't exist! Try again");
            }
        }
    } 
}