package models;

/* Table Salles :

id_salle (Clé primaire, Entier)

nom_salle (Texte : ex: "Salle Chopin")

equipement (Texte : ex: "Piano à queue", "Batterie", "Vide") */


public class Classroom {
    private int id_room;
    private String name;
    private String equipment;

    public Classroom (int id_room, String name, String equipment){
        this.id_room=id_room;
        this.name=name;
        this.equipment=equipment;
        
    }

    public int getId(){
        return this.id_room;
    }

    public String getName(){
        return this.name;
    }

    public String getEquipment(){
        return this.equipment;
    }


}