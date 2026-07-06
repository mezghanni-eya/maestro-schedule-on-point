package models;

/* On va effectuer un "soft delete" : au lieu de supprimer définitivement les anciens élèves ou profs, on va les stocker dans
 une table archive avec le motif de suppression et la date.
 Table Archive : 

 id_archive : (Clé primaire, entier).
 type : Text ("STUDENT", "PROFESSOR", "CLASSROOM").

 name : Text (student's name, classroom's name...)

 reason : Text : Le motif (ex: "Graduated", "Resigned", "Renovations", or "N/A").

 archive_date : Text, La date de la suppression .
 
 */

public class Archive {
    private final int id_archive;
    private final String type;
    private final String name;
    private final String reason;
    private final String archive_date;    

    public Archive(int id_archive, String type, String name, String reason, String archive_date){
        this.id_archive=id_archive;
        this.type=type;
        this.name=name;
        this.reason=reason;
        this.archive_date=archive_date;

    }
    
    public int getId(){
        return this.id_archive;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public String getReason(){
        return this.reason;
    }

    public String getArchiveDate(){
        return this.archive_date;
    }




}
