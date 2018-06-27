/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.mail.MessagingException;
import karhabty_pidev_entites.Agence_AutoEcole;
import karhabty_pidev_entites.Email;
import karhabty_pidev_services.Service_Agence_AutoEcole;
import karhabty_pidev_utils.DataSource;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Gestion_Agence_AutoEcoleController implements Initializable {
    public ObservableList<Agence_AutoEcole> ls=FXCollections.observableArrayList();
     public ObservableList<Agence_AutoEcole> ls1=FXCollections.observableArrayList();
     public ObservableList<String> ls_ville=FXCollections.observableArrayList("Tunis","Sfax","Souse","Beja","Bizerte","Ariana","Nabeul","Monastir","Gabes","Kef","Jandouba","Tabarka");
    @FXML
    private TextField nom_ae;
    @FXML
    private TextField rue_ae;
    @FXML
    private TextField code_postal_ae;
    @FXML
    private TextField horaire_ae;
    @FXML
    private TextField tel_ae;
    @FXML
    private TextField piece_ae;
    @FXML
    private ComboBox<String> ville_ae;
    @FXML
    private Button add_edit_ae;
    @FXML
    private Button delete_ae;
    @FXML
    private TableView<Agence_AutoEcole> liste_ae;
    @FXML
    private TableColumn<Agence_AutoEcole, String> nom_list_ae;
    @FXML
    private TableColumn<Agence_AutoEcole, String> adresse_list_ae;
    @FXML
    private TableColumn<Agence_AutoEcole, String> ville_list_ae;
    @FXML
    private TableColumn<Agence_AutoEcole, Integer> tel_list_ae;
    @FXML
    private TableColumn<Agence_AutoEcole, String> status_list_ae;
     private Statement ste ;
    @FXML
    private Button modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ville_ae.getSelectionModel().select("Aucune");
        ville_ae.setItems(ls_ville);
        nom_ae.setPromptText("Entrer le nom de votre agence");
        rue_ae.setPromptText("Entrer le numero de local et la rue de votre agence");
        code_postal_ae.setPromptText("Entrer le code postal");
        horaire_ae.setPromptText("Entrer les horaires de travail");
        tel_ae.setPromptText("Entrer le numero de téléphone");
        piece_ae.setPromptText("importer la piéce justificative");
        
     
        String req = "select * from `agence`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom_list_ae.setCellValueFactory(new PropertyValueFactory<>("nom_agence") );
        tel_list_ae.setCellValueFactory(new PropertyValueFactory<>("Telephone_agence") );
        adresse_list_ae.setCellValueFactory(new PropertyValueFactory<>("rue") );
        ville_list_ae.setCellValueFactory(new PropertyValueFactory<>("Ville") );
        status_list_ae.setCellValueFactory(new PropertyValueFactory<>("Status") );
        liste_ae.setItems(ls);
        liste_ae.setOnMouseClicked(event->{
         nom_ae.setText(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getNom_agence());
          tel_ae.setText(String.valueOf(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getTelephone_agence()));
            rue_ae.setText(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getRue());
             code_postal_ae.setText(String.valueOf(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getCode_postal()));
             ville_ae.setValue(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getVille());
             horaire_ae.setText(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getHoraire_travail());
               
               piece_ae.setText(ls
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getPiece_justificative());
               nom_ae.setEditable(false);
               
               String a=null;
              a=ls.get(liste_ae.getSelectionModel().getSelectedIndex()).getStatus();
              if("Rejetée".equals(a)){
                   TrayNotification tray = new TrayNotification();
                   tray.setTitle("Malheuresement!");
                   tray.setMessage("votre agence a été rejeté!Pour plus d'informations"+"\n"+"veuillez consulter votre courriel éléctronique");
                   tray.setNotificationType(NotificationType.ERROR);
                   tray.setAnimationType(AnimationType.POPUP);
                   tray.showAndDismiss(Duration.seconds(8));
              }
        } );
        }
    
     
    
    
    public void refraichir(){
        String req = "select * from `agence` where nom_agence='"+nom_ae.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls.add(p);
            //System.out.println(p);
            
            
        }} catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom_list_ae.setCellValueFactory(new PropertyValueFactory<>("nom_agence") );
        tel_list_ae.setCellValueFactory(new PropertyValueFactory<>("Telephone_agence") );
        adresse_list_ae.setCellValueFactory(new PropertyValueFactory<>("rue") );
        ville_list_ae.setCellValueFactory(new PropertyValueFactory<>("Ville") );
        status_list_ae.setCellValueFactory(new PropertyValueFactory<>("Status") );
        liste_ae.setItems(ls);
    }
    
    
    public void refraichir1(){
        ls1.clear();
        String req = "select * from `agence` ";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls1.add(p);
            //System.out.println(p);
            
            
            
        }} catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom_list_ae.setCellValueFactory(new PropertyValueFactory<>("nom_agence") );
        tel_list_ae.setCellValueFactory(new PropertyValueFactory<>("Telephone_agence") );
        adresse_list_ae.setCellValueFactory(new PropertyValueFactory<>("rue") );
        ville_list_ae.setCellValueFactory(new PropertyValueFactory<>("Ville") );
        status_list_ae.setCellValueFactory(new PropertyValueFactory<>("Status") );
        liste_ae.setItems(ls1);
        liste_ae.setOnMouseClicked(event->{
         nom_ae.setText(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getNom_agence());
          tel_ae.setText(String.valueOf(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getTelephone_agence()));
            rue_ae.setText(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getRue());
             code_postal_ae.setText(String.valueOf(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getCode_postal()));
             ville_ae.setValue(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getVille());
             horaire_ae.setText(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getHoraire_travail());
               
               piece_ae.setText(ls1
                .get(liste_ae.getSelectionModel().getSelectedIndex())
                .getPiece_justificative());
               
            } );
    }
    
    public boolean controlecodepostal() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(code_postal_ae.getText());
        if (m.find() && m.group().equals(code_postal_ae.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean controletelephone() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tel_ae.getText());
        if (m.find() && m.group().equals(tel_ae.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean controlehoraire() {
        Pattern p = Pattern.compile("[0-2][0-9]:[0-6][0-9]-[0-2][0-9]:[0-6][0-9]");
        Matcher m = p.matcher(horaire_ae.getText());
        if (m.find() && m.group().equals(horaire_ae.getText())) {
            return true;
        } else {
            return false;
        }
    }
      
    
       

    @FXML
    private void ajouter_modifier_AutoEcole(ActionEvent event) throws SQLException, MessagingException {
     
            Agence_AutoEcole agence_AutoEcole=new Agence_AutoEcole();
            agence_AutoEcole.setNom_agence(nom_ae.getText());
            agence_AutoEcole.setRue(rue_ae.getText());
           //controle de saisie pour lr code postal
            if (("".equals(code_postal_ae.getText())) || (controlecodepostal()==false)){
                agence_AutoEcole.setCode_postal(0);
            }else{
                agence_AutoEcole.setCode_postal(Integer.valueOf(code_postal_ae.getText()));
            }
            agence_AutoEcole.setHoraire_travail(horaire_ae.getText());
            //controle de sasiie pour le numero de telephone
            if (("".equals(tel_ae.getText())) || (controletelephone()==false)){
               agence_AutoEcole.setTelephone_agence(0);  
             }else{
            agence_AutoEcole.setTelephone_agence(Integer.valueOf(tel_ae.getText()));
             }
            agence_AutoEcole.setPiece_justificative(piece_ae.getText());
            agence_AutoEcole.setVille(ville_ae.getValue());
            agence_AutoEcole.setStatus("En attente");
           //les contraintes
            if (agence_AutoEcole.getNom_agence().length() == 0) {//controle de saisie nom agence
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le nom de votre agence");
            alert1.show();}
           
            else if (String.valueOf(agence_AutoEcole.getTelephone_agence()).length() != 8) {//controle de saisie tel
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un numero de téléphone de 8 chiffres");
            alert1.show();}
            
            else if (controlehoraire()==false){//controle de saisie horaire de travail
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir les horaires de travail de votre agence sous la forme HH:MM-HH:MM");
            alert1.show();}
            
            else if (agence_AutoEcole.getRue().length() == 0) {//controle de saisie rue 
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le numero de local et le rue de votre agence");
            alert1.show();}
            else if (agence_AutoEcole.getPiece_justificative().length() == 0) { //controle de saisie piece
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez importer une piéce justificative");
            alert1.show();}
            else if (String.valueOf(agence_AutoEcole.getCode_postal()).length() != 4) { //controle de saisie code postal
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un correcte code postal !");
            alert1.show();}
            else if ("Aucune".equals(ville_ae.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir une ville !");
            alert1.show();}
            
            
           else{//ajouter normal
            Service_Agence_AutoEcole ser=new Service_Agence_AutoEcole();
            ser.ajouterAgence(agence_AutoEcole);
            refraichir();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Une demande d'approuvement de votre Auto-école est envoyée avec succés!");
            alert.show();
             String msg="l'agence sous le nom de "+nom_ae.getText()+ " demande votre approuvement d'ajout,Veuillez télécharger la piéce justificatif de cette agence";
         Email.Email("karhabty1234.karhabty@gmail.com", "Demande d'ajout", msg);
            
           }
        }

    
    
    @FXML
    private void supprimer_AutoEcole(ActionEvent event) throws SQLException {
        
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous vraiment supprimer cette agence?");
                Optional <ButtonType> action = alert.showAndWait();
                 if (action.get()==ButtonType.OK){
                      String req = "delete from `agence` where nom_agence='" +nom_ae.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre = con.prepareStatement(req);
         pre.executeUpdate();
         refraichir1();
         
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Information Dialog");
         alert1.setHeaderText(null);
         alert1.setContentText("Auto-école supprimée avec succés!");
         alert1.show();
                 }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, MessagingException {
        String req8 = "select status from `agence` where nom_agence='"+nom_ae.getText()+"'";
            Connection con1 = DataSource.getInstance().getConnection();
             PreparedStatement pre8;
          pre8 = con1.prepareStatement(req8);
          ResultSet rs8 = pre8.executeQuery();
         String stat = null;
          while (rs8.next()) {
        stat=(rs8.getString(1));  
         }
          System.out.println(stat)  ;
        if(("Approuvée".equals(stat))||("En attente".equals(stat))){
        String req1="update agence set telephone_agence='"+tel_ae.getText()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req2="update agence set horaire_travail='"+horaire_ae.getText()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req3="update agence set piece_justificative='"+piece_ae.getText()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req4="update agence set rue='"+rue_ae.getText()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req5="update agence set code_postal='"+code_postal_ae.getText()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req6="update agence set ville='"+ville_ae.getValue()+"' where nom_agence='" +nom_ae.getText()+"'";
        String req7="update agence set status='En attente' where nom_agence='" +nom_ae.getText()+"'";
       // String req7="update agence set status=`en cours` where nom_agence='" +nom_ae.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
         PreparedStatement pre3 = con.prepareStatement(req3);
         PreparedStatement pre4 = con.prepareStatement(req4);
         PreparedStatement pre5 = con.prepareStatement(req5);
         PreparedStatement pre6 = con.prepareStatement(req6);
         PreparedStatement pre7 = con.prepareStatement(req7);
        // PreparedStatement pre7 = con.prepareStatement(req7);
           if (nom_ae.getText().length() == 0) {//controle de saisie nom agence
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le nom de votre agence");
            alert1.show();}
           
            else if (String.valueOf(tel_ae.getText()).length() != 8) {//controle de saisie tel
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un numero de téléphone de 8 chiffres");
            alert1.show();}
            
            else if (controlehoraire()==false){//controle de saisie horaire de travail
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir les horaires de travail de votre agence");
            alert1.show();}
            
            else if (rue_ae.getText().length() == 0) {//controle de saisie rue 
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le numero de local et le rue de votre agence");
            alert1.show();}
            else if (piece_ae.getText().length() == 0) { //controle de saisie piece
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez importer une piéce justificative");
            alert1.show();}
            else if (String.valueOf(code_postal_ae.getText()).length() != 4) { //controle de saisie code postal
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un correcte code postal !");
            alert1.show();}
            else if ("Aucune".equals(ville_ae.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir une ville !");
            alert1.show();}
         else{
         pre1.executeUpdate();
         pre2.executeUpdate();
         pre3.executeUpdate();
         pre4.executeUpdate();
         pre5.executeUpdate();
         pre6.executeUpdate();
          pre7.executeUpdate();
         //pre7.executeUpdate();
         refraichir1();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Une demande de modification de votre Auto-école est envoyée avec succés!");
         alert.show();
           /*String msg="l'agence sous le nom de "+nom_ae.getText()+ "demande votre approuvement de modification,Veuillez télécharger la piéce justificatif de cette agence";
         Email.Email("karhabty1234.karhabty@gmail.com", "Demande de modification", msg);*/
            }
        }
        else if("Rejetée".equals(stat)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Votre agence a été rejetée par l'administration , vous ne pouvez pas faire aucune modification!");
         alert.show();
        }
         }
         

    @FXML
    private void refresh(ActionEvent event) {
        ville_ae.getSelectionModel().select("Aucune");
        nom_ae.setEditable(true);
        nom_ae.setText("");
         horaire_ae.setText("");
          piece_ae.setText("");
           rue_ae.setText("");
            code_postal_ae.setText("");
            tel_ae.setText("");
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        nom_ae.getScene().setRoot(root);
    }
    
    
    
}

