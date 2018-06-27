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
public class Agence_AutoEcole {
    private int id_agence;
    private String nom_agence;
    private int telephone_agence;
    private String type_agence;
    private String horaire_travail;
    private String rue;
    private int code_postal;
    private String ville;
    private String piece_justificative;
    private String status;
    

    public Agence_AutoEcole() {
    }

    public Agence_AutoEcole(String nom_agence, int telephone_agence, String horaire_travail, String rue, int code_postal, String ville, String piece_justificative, String status ) {
        this.nom_agence = nom_agence;
        this.telephone_agence = telephone_agence;
        this.horaire_travail = horaire_travail;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.piece_justificative = piece_justificative;
        this.status = status;
       
    }

    public Agence_AutoEcole(String nom_agence, int telephone_agence, String rue, String ville, String status) {
        this.nom_agence = nom_agence;
        this.telephone_agence = telephone_agence;
        this.rue = rue;
        this.ville = ville;
        this.status = status;
    }

    public Agence_AutoEcole(String nom_agence, int telephone_agence, String horaire_travail, String rue, int code_postal, String ville, String piece_justificative) {
        this.nom_agence = nom_agence;
        this.telephone_agence = telephone_agence;
        this.horaire_travail = horaire_travail;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.piece_justificative = piece_justificative;
        
       
    }

    
    public Agence_AutoEcole(String nom_agence, int telephone_agence, String rue, String status) {
        this.nom_agence = nom_agence;
        this.telephone_agence = telephone_agence;
        this.rue = rue;
        this.status = status;
    }
    
    

    
    public int getId_agence() {
        return id_agence;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public int getTelephone_agence() {
        return telephone_agence;
    }

    public String getType_agence() {
        return type_agence;
    }

    public String getHoraire_travail() {
        return horaire_travail;
    }

    public String getRue() {
        return rue;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public String getVille() {
        return ville;
    }

    public String getPiece_justificative() {
        return piece_justificative;
    }

    public String getStatus() {
        return status;
    }

    

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public void setTelephone_agence(int telephone_agence) {
        this.telephone_agence = telephone_agence;
    }

    public void setType_agence(String type_agence) {
        this.type_agence = type_agence;
    }

    public void setHoraire_travail(String horaire_travail) {
        this.horaire_travail = horaire_travail;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPiece_justificative(String piece_justificative) {
        this.piece_justificative = piece_justificative;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    @Override
    public String toString() {
        return "Agence_AutoEcole{" + "id_agence=" + id_agence + ", nom_agence=" + nom_agence + ", telephone_agence=" + telephone_agence + ", type_agence=" + type_agence + ", horaire_travail=" + horaire_travail + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", piece_justificative=" + piece_justificative + ", status=" + status + '}';
    }

   

    

  
    
    
   
    
    
    
    
   
    
    
}
