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
public class voiture_AutoEcole {
    private String matricule;
    private String marque;
    private String couleur;
    private String carburant;
    private int age;
    private int id_agence;

    public voiture_AutoEcole() {
    }

    public voiture_AutoEcole(String matricule, String marque, String couleur, String carburant, int age, int id_agence) {
        this.matricule = matricule;
        this.marque = marque;
        this.couleur = couleur;
        this.carburant = carburant;
        this.age = age;
        this.id_agence = id_agence;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getCarburant() {
        return carburant;
    }

    public int getAge() {
        return age;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    @Override
    public String toString() {
        return "voiture_AutoEcole{" + "matricule=" + matricule + ", marque=" + marque + ", couleur=" + couleur + ", carburant=" + carburant + ", age=" + age + ", id_agence=" + id_agence + '}';
    }
    
    
    
}
