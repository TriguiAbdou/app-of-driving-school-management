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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import karhabty_pidev_entites.Agence_AutoEcole;
import karhabty_pidev_entites.Agent_AutoEcole;
import karhabty_pidev_entites.Exercice_AutoEcole;
import karhabty_pidev_entites.voiture_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Client_AutoEcoleController implements Initializable {
     public ObservableList<Agence_AutoEcole> ls=FXCollections.observableArrayList();
     public ObservableList<String> ls1=FXCollections.observableArrayList("Nom","Ville");
      public ObservableList<Agence_AutoEcole> ls3=FXCollections.observableArrayList();
      public ObservableList<voiture_AutoEcole> ls4=FXCollections.observableArrayList();
      public ObservableList<Agent_AutoEcole> ls5=FXCollections.observableArrayList();
       public ObservableList<Exercice_AutoEcole> ls6=FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> type_recherche;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Agence_AutoEcole> agence;
    @FXML
    private TableView<voiture_AutoEcole> voiture;
    @FXML
    private TableView<Agent_AutoEcole> agent;
    @FXML
    private TableView<Exercice_AutoEcole> exercice;
    @FXML
    private TableColumn<Agence_AutoEcole, String> nom_agence;
    @FXML
    private TableColumn<Agence_AutoEcole, Integer> tel_agence;
    @FXML
    private TableColumn<Agence_AutoEcole, String> horaire_agence;
    @FXML
    private TableColumn<Agence_AutoEcole, String> rue_agence;
    @FXML
    private TableColumn<Agence_AutoEcole, Integer> code_agence;
    @FXML
    private TableColumn<Agence_AutoEcole, String> ville_agence;
    @FXML
    private TableColumn<voiture_AutoEcole, String> matricule_voiture;
    @FXML
    private TableColumn<voiture_AutoEcole, String> marque_voiture;
    @FXML
    private TableColumn<voiture_AutoEcole, String> couleur_voiture;
    @FXML
    private TableColumn<voiture_AutoEcole, String> carburant_voiture;
    @FXML
    private TableColumn<voiture_AutoEcole, Integer> annee_voiture;
    @FXML
    private TableColumn<Agent_AutoEcole, String> nom_agent;
    @FXML
    private TableColumn<Agent_AutoEcole, String> prenom_agent;
    @FXML
    private TableColumn<Agent_AutoEcole, String> sexe_agent;
    @FXML
    private TableColumn<Agent_AutoEcole, Integer> tel_agent;
    @FXML
    private TableColumn<Agent_AutoEcole, String> type_agent;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> agent_exercice;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> type_exercice;
    @FXML
    private TableColumn<Exercice_AutoEcole, Float> prix_exercice;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> date_heure_exercice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type_recherche.setItems(ls1);
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
        nom_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("nom_agence") );
        tel_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("telephone_agence") );
        horaire_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("horaire_travail") );
        rue_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("rue") );
        code_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("code_postal") );
         ville_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("ville") );
        agence.setItems(ls);
        recherche.setPromptText("Saisir le nom");
        agence.setOnMouseClicked(event1->{
          String nom_ag = (ls.get(agence.getSelectionModel().getSelectedIndex()).getNom_agence()); 
          String req3 = "select id_agence from `agence` where nom_agence='"+nom_ag+"'";
            Connection con3 = DataSource.getInstance().getConnection();
             PreparedStatement pre3;
            try {
                pre3 = con.prepareStatement(req3);
                ResultSet rs3 = pre3.executeQuery();
                while (rs3.next()) {
                   int id=rs3.getInt(1);
           
         //table voiture
             String req4 = "select * from `voiture`  where id_agence='"+id+"'";;
        Connection con4 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre4 = con.prepareStatement(req4);
            ResultSet rs4 = pre4.executeQuery();
            voiture_AutoEcole p = null;
            
            while (rs4.next()) {
            p = new voiture_AutoEcole(rs4.getString(1),rs4.getString(2), rs4.getString(3) ,rs4.getString(4), rs4.getInt(5), rs4.getInt(18) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls4.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       matricule_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Matricule") );
        marque_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Marque") );
        couleur_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Couleur") );
        carburant_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Carburant") );
        annee_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("age") );
         
        voiture.setItems(ls4);
        
        //remplir le tableau des agents
        String req5 = "select * from `agent`  where id_agence='"+id+"'";;
        Connection con5 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre5 = con.prepareStatement(req5);
            ResultSet rs5 = pre5.executeQuery();
            Agent_AutoEcole p = null;
            
            while (rs5.next()) {
            p = new Agent_AutoEcole(rs5.getInt(2), rs5.getString(3), rs5.getString(4) ,rs5.getString(5), rs5.getInt(6), rs5.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls5.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
      nom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        prenom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        sexe_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        tel_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("telephone") );
        type_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
         
        agent.setItems(ls5);
          } }catch (SQLException ex) {
                Logger.getLogger(Client_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               String req6 = "select * from `exercice`  where id_client='07468678'";;
        Connection con6 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre6 = con.prepareStatement(req6);
            ResultSet rs6 = pre6.executeQuery();
            Exercice_AutoEcole p = null;
            
            while (rs6.next()) {
            p = new Exercice_AutoEcole(rs6.getInt(2), rs6.getInt(3), rs6.getString(4) ,rs6.getString(5), rs6.getFloat(6), rs6.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls6.add(p);
            //System.out.println(p);
            }
            if(ls6.isEmpty()==false){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous avez un exercice dans cette agence!");
            alert1.show();
            }
            else if(ls6.isEmpty()==true){
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez aucun exercice  dans cette agence!");
            alert1.show();
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       agent_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       // voiture_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       /////////PROBLEME!!!!comment afficher le nom et la matricule de l exercice !qui ne sont pas un element de l obket exercice 
       type_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("type") );
        prix_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, Float>("prix") );
        date_heure_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("date_heure") );
        
        exercice.setItems(ls6);
           
    });
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        
        //table agence
         if(type_recherche.getValue()=="Ville"){
            String req = "select * from `agence`  where ville='"+recherche.getText()+"'";;
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls3.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("nom_agence") );
        tel_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("telephone_agence") );
        horaire_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("horaire_travail") );
        rue_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("rue") );
        code_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("code_postal") );
         ville_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("ville") );
        agence.setItems(ls3);
        agence.setOnMouseClicked(event1->{
          String nom_ag = (ls3.get(agence.getSelectionModel().getSelectedIndex()).getNom_agence()); 
          String req3 = "select id_agence from `agence` where nom_agence='"+nom_ag+"'";
            Connection con3 = DataSource.getInstance().getConnection();
             PreparedStatement pre3;
            try {
                pre3 = con.prepareStatement(req3);
                ResultSet rs3 = pre3.executeQuery();
                while (rs3.next()) {
                   int id=rs3.getInt(1);
           
         //table voiture
             String req4 = "select * from `voiture`  where id_agence='"+id+"'";;
        Connection con4 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre4 = con.prepareStatement(req4);
            ResultSet rs4 = pre4.executeQuery();
            voiture_AutoEcole p = null;
            
            while (rs4.next()) {
            p = new voiture_AutoEcole(rs4.getString(1),rs4.getString(2), rs4.getString(3) ,rs4.getString(4), rs4.getInt(5), rs4.getInt(18) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls4.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       matricule_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Matricule") );
        marque_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Marque") );
        couleur_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Couleur") );
        carburant_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Carburant") );
        annee_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("age") );
         
        voiture.setItems(ls4);
        
        //remplir le tableau des agents
        String req5 = "select * from `agent`  where id_agence='"+id+"'";;
        Connection con5 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre5 = con.prepareStatement(req5);
            ResultSet rs5 = pre5.executeQuery();
            Agent_AutoEcole p = null;
            
            while (rs5.next()) {
            p = new Agent_AutoEcole(rs5.getInt(2), rs5.getString(3), rs5.getString(4) ,rs5.getString(5), rs5.getInt(6), rs5.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls5.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
      nom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        prenom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        sexe_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        tel_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("telephone") );
        type_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
         
        agent.setItems(ls5);
          } }catch (SQLException ex) {
                Logger.getLogger(Client_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               String req6 = "select * from `exercice`  where id_client='11861810'";;
        Connection con6 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre6 = con.prepareStatement(req6);
            ResultSet rs6 = pre6.executeQuery();
            Exercice_AutoEcole p = null;
            
            while (rs6.next()) {
            p = new Exercice_AutoEcole(rs6.getInt(2), rs6.getInt(3), rs6.getString(4) ,rs6.getString(5), rs6.getFloat(6), rs6.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls6.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
      agent_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       // voiture_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       /////////PROBLEME!!!!comment afficher le nom et la matricule de l exercice !qui ne sont pas un element de l obket exercice 
       type_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("type") );
        prix_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, Float>("prix") );
        date_heure_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("date_heure") );
        
        exercice.setItems(ls6);
           
            
         
        }); 
        }
        else{
        String req = "select * from `agence`  where nom_agence='"+recherche.getText()+"'";;
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls3.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("nom_agence") );
        tel_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("telephone_agence") );
        horaire_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("horaire_travail") );
        rue_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("rue") );
        code_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("code_postal") );
         ville_agence.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("ville") );
        agence.setItems(ls3);
        agence.setOnMouseClicked(event1->{
          String nom_ag = (ls3.get(agence.getSelectionModel().getSelectedIndex()).getNom_agence()); 
          String req3 = "select id_agence from `agence` where nom_agence='"+nom_ag+"'";
            Connection con3 = DataSource.getInstance().getConnection();
             PreparedStatement pre3;
            try {
                pre3 = con.prepareStatement(req3);
                ResultSet rs3 = pre3.executeQuery();
                while (rs3.next()) {
                   int id=rs3.getInt(1);
           
         //table voiture
             String req4 = "select * from `voiture`  where id_agence='"+id+"'";;
        Connection con4 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre4 = con.prepareStatement(req4);
            ResultSet rs4 = pre4.executeQuery();
            voiture_AutoEcole p = null;
            
            while (rs4.next()) {
            p = new voiture_AutoEcole(rs4.getString(1),rs4.getString(2), rs4.getString(3) ,rs4.getString(4), rs4.getInt(5), rs4.getInt(18) );
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls4.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       matricule_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Matricule") );
        marque_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Marque") );
        couleur_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Couleur") );
        carburant_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("Carburant") );
        annee_voiture.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("age") );
         
        voiture.setItems(ls4);
        
        //remplir le tableau des agents
        String req5 = "select * from `agent`  where id_agence='"+id+"'";;
        Connection con5 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre5 = con.prepareStatement(req5);
            ResultSet rs5 = pre5.executeQuery();
            Agent_AutoEcole p = null;
            
            while (rs5.next()) {
            p = new Agent_AutoEcole(rs5.getInt(2), rs5.getString(3), rs5.getString(4) ,rs5.getString(5), rs5.getInt(6), rs5.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls5.add(p);
            //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
      nom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        prenom_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        sexe_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        tel_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("telephone") );
        type_agent.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
         
        agent.setItems(ls5);
          } }catch (SQLException ex) {
                Logger.getLogger(Client_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //remplir le table des exercices
               String req6 = "select * from `exercice`  where id_client='11861810'";
        Connection con6 = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre6 = con.prepareStatement(req6);
            ResultSet rs6 = pre6.executeQuery();
            Exercice_AutoEcole p = null;
            
            while (rs6.next()) {
            p = new Exercice_AutoEcole(rs6.getInt(2), rs6.getInt(3), rs6.getString(4) ,rs6.getString(5), rs6.getFloat(6), rs6.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls6.add(p);
            //System.out.println(p);
           }
            if(ls6.isEmpty()==false){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous avez un exercice dans cette agence!");
            alert1.show();
            }
            else if(ls6.isEmpty()==true){
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez aucun exercice  dans cette agence!");
            alert1.show();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        agent_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       // voiture_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("matricule") );
       /////////PROBLEME!!!!comment afficher le nom et la matricule de l exercice !qui ne sont pas un element de l obket exercice 
       type_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("type") );
        prix_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, Float>("prix") );
        date_heure_exercice.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("date_heure") );
        
        exercice.setItems(ls6);
           
            
         
        });
        
       
         }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_Principale_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        exercice.getScene().setRoot(root);
    }
    

    
}
