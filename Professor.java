package models;

/* Table Professor :
- id_prof : Primary key, integer
- name : string
- instrument : string (piano, guitar, violin...)
 */


public class Professor{

    private int id_prof;
    private String name;
    private String instrument;
    
    public Professor(int id_prof, String name, String instrument) {
        this.id_prof=id_prof;
        this.name=name;
        this.instrument=instrument;

    }
    
    public String getName(){
        return this.name;
    }
    
    public int getId(){
        return this.id_prof;
    }
    
    public String getInstrument(){
        return this.instrument;
    }
}