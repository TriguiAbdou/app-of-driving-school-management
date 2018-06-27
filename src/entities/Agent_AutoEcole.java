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
public class Agent_AutoEcole {
    private int id_agence;
    private String nom;
    private String prenom;
    private String sexe;
    private int tel;
    private String type;

    public Agent_AutoEcole() {
    }

    public Agent_AutoEcole(int id_agence, String nom, String prenom, String sexe, int tel, String type) {
        this.id_agence = id_agence;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.tel = tel;
        this.type = type;
    }

    public int getId_agence() {
        return id_agence;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getTel() {
        return tel;
    }

    public String getType() {
        return type;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Agent_AutoEcole{" + "id_agence=" + id_agence + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", tel=" + tel + ", type=" + type + '}';
    }
    
    
    
    
}
