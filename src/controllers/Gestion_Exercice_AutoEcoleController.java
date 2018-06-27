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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import karhabty_pidev_entites.Exercice_AutoEcole;
import karhabty_pidev_services.Service_Exercice_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Gestion_Exercice_AutoEcoleController implements Initializable {
     public ObservableList<Exercice_AutoEcole> ls=FXCollections.observableArrayList();
     public ObservableList<Exercice_AutoEcole> ls5=FXCollections.observableArrayList();
      public ObservableList<String> ls1=FXCollections.observableArrayList();
       public ObservableList<String> ls2=FXCollections.observableArrayList();
        public ObservableList<String> ls3=FXCollections.observableArrayList();
        public ObservableList<String> ls4=FXCollections.observableArrayList("Code","Conduite");
    @FXML
    private ComboBox<String> agent;
    @FXML
    private ComboBox<String> client;
    @FXML
    private ComboBox<String> matricule;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField prix;
    @FXML
    private TextField date_heure;
    @FXML
    private TableView<Exercice_AutoEcole> table;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> liste_client;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> liste_agent;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> liste_type;
    @FXML
    private TableColumn<Exercice_AutoEcole, String> liste_date_heure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        agent.getSelectionModel().select("Aucun");
        client.getSelectionModel().select("Aucun");
        matricule.getSelectionModel().select("Aucune");
        type.getSelectionModel().select("Aucun");
         type.setItems(ls4);
        String req = "select * from `exercice`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Exercice_AutoEcole p = null;
            while (rs.next()) {
            p = new Exercice_AutoEcole(rs.getInt(2), rs.getInt(3), rs.getString(4) ,rs.getString(5), rs.getFloat(6), rs.getString(7));
            ls.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_client.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Matricule") );
        liste_agent.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Prix") );
        liste_type.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Type") );
        liste_date_heure.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Date_heure") );
        
        table.setItems(ls);
        table.setOnMouseClicked(event->{
         type.setValue(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getType());
          date_heure.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getDate_heure()));
           prix.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getPrix()));
            matricule.setValue(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getMatricule());
            date_heure.setEditable(false);
            
            //recuperer la valeur l agent dans le combobox
             int a;
        a=(ls.get(table.getSelectionModel().getSelectedIndex()).getId_agent());
        
        String req2 = "select nom from `agent` where id_agent='"+a+"'";
       
            try {
                PreparedStatement pre2;
                pre2 = con.prepareStatement(req2);
                 ResultSet rs2 = pre2.executeQuery();
                while (rs2.next()) {
                   //System.out.println("abdou");
                    //System.out.println(rs2.getString(1));
                   agent.setValue(rs2.getString(1));   
                }
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_Agent_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            //recuperer la valeur de client dans le combobox 
            
                  int b;
        b=(ls.get(table.getSelectionModel().getSelectedIndex()).getId_client());
        
        String req3 = "select nom from `user` where id='"+b+"'";
       
            try {
                PreparedStatement pre3;
                pre3 = con.prepareStatement(req3);
                 ResultSet rs3 = pre3.executeQuery();
                while (rs3.next()) {
                   //System.out.println("abdou");
                    //System.out.println(rs2.getString(1));
                   client.setValue(rs3.getString(1));   
                }
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_Agent_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } );   
       //combobox de nom de l agent 
        String req1 = "select nom from `agent`";
        try {
          PreparedStatement pre1;
          pre1 = con.prepareStatement(req1);
          ResultSet rs1 = pre1.executeQuery();
          while (rs1.next()) {
              //agence.getItems().add(rs1.getString(1));
              ls1.add(rs1.getString(1));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Gestion_Voiture_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
      }
        agent.setItems(ls1);
        
        //combobox de nom des client
        String req2 = "select nom from `user`";
        try {
          PreparedStatement pre2;
          pre2 = con.prepareStatement(req2);
          ResultSet rs2 = pre2.executeQuery();
          while (rs2.next()) {
              //agence.getItems().add(rs1.getString(1));
              ls2.add(rs2.getString(1));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Gestion_Voiture_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
      }
        client.setItems(ls2);
        
        //combobox des matricules des voitures
        String req3 = "select matricule from `voiture`";
        try {
          PreparedStatement pre3;
          pre3 = con.prepareStatement(req3);
          ResultSet rs3 = pre3.executeQuery();
          while (rs3.next()) {
              //agence.getItems().add(rs1.getString(1));
              ls3.add(rs3.getString(1));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Gestion_Voiture_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
      }
        matricule.setItems(ls3);
                }

    
       public void refraichir(){
        String req = "select * from `exercice` where date_heure='"+date_heure.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Exercice_AutoEcole p = null;
            
            while (rs.next()) {
           p = new Exercice_AutoEcole(rs.getInt(2), rs.getInt(3), rs.getString(4) ,rs.getString(5), rs.getFloat(6), rs.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls.add(p);
            //System.out.println(p);
            
            
        }} catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_type.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Type") );
        liste_date_heure.setCellValueFactory(new PropertyValueFactory<Exercice_AutoEcole, String>("Date_heure") );
        table.setItems(ls);
    }
    
    
    public void refraichir1(){
        ls5.clear();
        String req = "select * from `exercice`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Exercice_AutoEcole p = null;
            while (rs.next()) {
           p = new Exercice_AutoEcole(rs.getInt(2), rs.getInt(3), rs.getString(4) ,rs.getString(5), rs.getFloat(6), rs.getString(7));
            ls5.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       liste_type.setCellValueFactory(new PropertyValueFactory<>("Type") );
        liste_date_heure.setCellValueFactory(new PropertyValueFactory<>("Date_heure") );
        table.setItems(ls5);
        table.setOnMouseClicked(event->{
         type.setValue(ls5
                .get(table.getSelectionModel().getSelectedIndex())
                .getType());
          date_heure.setText(String.valueOf(ls5
                .get(table.getSelectionModel().getSelectedIndex())
                .getDate_heure()));
           prix.setText(String.valueOf(ls5
                .get(table.getSelectionModel().getSelectedIndex())
                .getPrix()));
    } );   
    }
    
     public boolean controleMatricule() {
        Pattern p = Pattern.compile("Le [0-3][0-9]/[0-1][0-9]/[0-9]+ à [0-2][0-9]:[0-6][0-9]");
        Matcher m = p.matcher(date_heure.getText());
        if (m.find() && m.group().equals(date_heure.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Exercice_AutoEcole exercice_AutoEcole=new Exercice_AutoEcole();
         exercice_AutoEcole.setType(type.getValue());
         if ("".equals(prix.getText())){
                exercice_AutoEcole.setPrix(0f);
            }else{
         exercice_AutoEcole.setPrix(Float.valueOf(prix.getText()));
         }
         exercice_AutoEcole.setDate_heure(date_heure.getText());
         exercice_AutoEcole.setMatricule(matricule.getValue());
         //recuêrer l ed de nom de l agent
          String req1 = "select id_agent from `agent` where nom='"+agent.getValue()+"'";
            Connection con = DataSource.getInstance().getConnection();
             PreparedStatement pre1;
          pre1 = con.prepareStatement(req1);
          ResultSet rs1 = pre1.executeQuery();
         while (rs1.next()) {
         exercice_AutoEcole.setId_agent(rs1.getInt(1));  
         }
         
         //recuperer l id du nom de client 
          String req2 = "select id from `user` where nom='"+client.getValue()+"'";
            Connection con1 = DataSource.getInstance().getConnection();
             PreparedStatement pre2;
          pre2 = con.prepareStatement(req2);
          ResultSet rs2 = pre2.executeQuery();
         while (rs2.next()) {
         exercice_AutoEcole.setId_client(rs2.getInt(1));  
         }
         
             if ("Aucun".equals(agent.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un agent !");
            alert1.show();}
                 else if ("Aucun".equals(client.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un client !");
            alert1.show();}
                else if ((matricule.getValue()!= "Aucune")&&("Code".equals(type.getValue()))){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("l'exercice de type code ne peut pas avoir une matricule de voiture !");
            alert1.show();}
                 else if ("Aucun".equals(type.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un type !");
            alert1.show();}
         else if(controleMatricule()==false){
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("ce champs doit etre de la forme 'Le JJ/MM/YYYY à HH:MM !'");
            alert1.show();
         }
        else if (exercice_AutoEcole.getPrix() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un prix!");
            alert1.show();}
         
         else{
         Service_Exercice_AutoEcole ser=new Service_Exercice_AutoEcole();
            ser.ajouterExercice(exercice_AutoEcole);
            refraichir();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Exercice ajouté avec succés!");
         alert.show();
         }
         

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         String req1="update exercice set type='"+type.getValue()+"' where date_heure='" +date_heure.getText()+"'";
         String req2="update exercice set prix='"+prix.getText()+"' where date_heure='" +date_heure.getText()+"'";
           Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
         pre1.executeUpdate();
         pre2.executeUpdate();
         refraichir1();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Exercice modifié avec succés!");
         alert.show();
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous vraiment supprimer cet exercice?");
                Optional <ButtonType> action = alert.showAndWait();
                 if (action.get()==ButtonType.OK){
        String req = "delete from `exercice` where date_heure='" +date_heure.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre = con.prepareStatement(req);
         pre.executeUpdate();
         refraichir1();
          Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Information Dialog");
         alert1.setHeaderText(null);
         alert1.setContentText("Exercice supprimé avec succés!");
         alert1.show();
                 }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        prix.getScene().setRoot(root);
    }

    @FXML
    private void refresh(ActionEvent event) {
         agent.getSelectionModel().select("Aucun");
        client.getSelectionModel().select("Aucun");
        matricule.getSelectionModel().select("Aucune");
        type.getSelectionModel().select("Aucun");
        date_heure.setEditable(true);
        date_heure.setText("");
         prix.setText("");
          
    }
    
}
