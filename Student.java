

/*Table Etudiants :

id_stud (Clé primaire, Entier)

name (Texte)

instru_stud (Texte)

niveau (Texte : Débutant, Intermédiaire, Avancé) */ 

package models;

public class Student {
    private int id_stud;
    private String name;
    private String instru_stud;
    private String level;

    public Student (int id_stud, String name, String instru_stud, String level){
        this.id_stud=id_stud;
        this.name=name;
        this.instru_stud=instru_stud;
        this.level=level;
    }

    public int getId(){
        return this.id_stud;
    }

    public String getName(){
        return this.name;
    }

    public String getInstrument(){
        return this.instru_stud;
    }

    public String getLevel(){
        return this.level;
    }

}