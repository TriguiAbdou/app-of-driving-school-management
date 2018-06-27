/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_entites;

/**
 *
 * @author Trigui
 */
public class Exercice_AutoEcole {
    private int id_agent;
    private int id_client;
    private String matricule;
    private String type;
    private Float prix;
    private String date_heure;

    public Exercice_AutoEcole() {
    }
    
    

    public Exercice_AutoEcole(int id_agent, int id_client, String matricule, String type, Float prix, String date_heure) {
        this.id_agent = id_agent;
        this.id_client = id_client;
        this.matricule = matricule;
        this.type = type;
        this.prix = prix;
        this.date_heure = date_heure;
    }

    public int getId_agent() {
        return id_agent;
    }

    public int getId_client() {
        return id_client;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getType() {
        return type;
    }

    public Float getPrix() {
        return prix;
    }

    public String getDate_heure() {
        return date_heure;
    }

    public void setId_agent(int id_agent) {
        this.id_agent = id_agent;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setDate_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    @Override
    public String toString() {
        return "Exercice_AutoEcole{" + "id_agent=" + id_agent + ", id_client=" + id_client + ", matricule=" + matricule + ", type=" + type + ", prix=" + prix + ", date_heure=" + date_heure + '}';
    }
    
    
    
}
