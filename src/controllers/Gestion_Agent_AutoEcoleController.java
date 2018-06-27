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
import karhabty_pidev_entites.Agent_AutoEcole;
import karhabty_pidev_services.Service_Agent_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Gestion_Agent_AutoEcoleController implements Initializable {
  public ObservableList<String> ls1=FXCollections.observableArrayList("Homme","Femme");
  public ObservableList<String> ls2=FXCollections.observableArrayList("Moniteur","Superviseur");
   public ObservableList<Agent_AutoEcole> ls=FXCollections.observableArrayList();
   public ObservableList<String> ls3=FXCollections.observableArrayList();
    public ObservableList<Agent_AutoEcole> ls4=FXCollections.observableArrayList();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TableView<Agent_AutoEcole> table;
    @FXML
    private TableColumn<Agent_AutoEcole, String> liste_nom;
    @FXML
    private TableColumn<Agent_AutoEcole, String> liste_prenom;
    @FXML
    private TableColumn<Agent_AutoEcole, Integer> liste_tel;
    @FXML
    private TableColumn<Agent_AutoEcole, String> liste_sexe;
    @FXML
    private TableColumn<Agent_AutoEcole, String> liste_type;
    @FXML
    private ComboBox<String> agence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sexe.setItems(ls1);
        type.setItems(ls2);
        nom.setPromptText("Entrer le nom de l'agent");
        prenom.setPromptText("Entrer le prenom de l'agent");
        tel.setPromptText("Entrer le numero de téléphone de l'agent");
        agence.getSelectionModel().select("Aucune");
        type.getSelectionModel().select("Aucun");
        sexe.getSelectionModel().select("Aucune");
        
        
        
        String req = "select * from `agent`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agent_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agent_AutoEcole(rs.getInt(2), rs.getString(3), rs.getString(4) ,rs.getString(5), rs.getInt(6), rs.getString(7));
            ls.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_nom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        liste_prenom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        liste_tel.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("tel") );
        liste_sexe.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        liste_type.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
        table.setItems(ls);
        table.setOnMouseClicked(event->{
         nom.setText(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getNom());
          prenom.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getPrenom()));
            tel.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getTel()));
             sexe.setValue(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getSexe()));
             type.setValue(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getType());
             //agence.setValue("agence mte3ek");
            nom.setEditable(false);
            
        int a;
        a=(ls.get(table.getSelectionModel().getSelectedIndex()).getId_agence());
        
        String req2 = "select nom_agence from `agence` where id_agence='"+a+"'";
       
            try {
                PreparedStatement pre2;
                pre2 = con.prepareStatement(req2);
                 ResultSet rs2 = pre2.executeQuery();
                while (rs2.next()) {
                   //System.out.println("abdou");
                    //System.out.println(rs2.getString(1));
                   agence.setValue(rs2.getString(1));   
                }
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_Agent_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
          
        
        } );
        String req1 = "select nom_agence from `agence`";// where id_manager='07468678'";
        try {
          PreparedStatement pre1;
          pre1 = con.prepareStatement(req1);
          ResultSet rs1 = pre1.executeQuery();
          while (rs1.next()) {
              //agence.getItems().add(rs1.getString(1));
              ls3.add(rs1.getString(1));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Gestion_Voiture_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
      }
        agence.setItems(ls3);
           
    }    
    
    public void refraichir(){
        String req = "select * from `agent` where nom='"+nom.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agent_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agent_AutoEcole(rs.getInt(2), rs.getString(3), rs.getString(4) ,rs.getString(5), rs.getInt(6), rs.getString(7));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls.add(p);
            //System.out.println(p);
            
            
        }} catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_nom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        liste_prenom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        liste_tel.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("tel") );
        liste_sexe.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        liste_type.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
        table.setItems(ls);
    }
    
    
    public void refraichir1(){
        ls4.clear();
        String req = "select * from `agent`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agent_AutoEcole p = null;
            while (rs.next()) {
            p = new Agent_AutoEcole(rs.getInt(2), rs.getString(3), rs.getString(4) ,rs.getString(5), rs.getInt(6), rs.getString(7));
            ls4.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_nom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("nom") );
        liste_prenom.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("prenom") );
        liste_tel.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, Integer>("tel") );
        liste_sexe.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("sexe") );
        liste_type.setCellValueFactory(new PropertyValueFactory<Agent_AutoEcole, String>("type") );
        table.setItems(ls4);
        table.setOnMouseClicked(event->{
         nom.setText(ls4
                .get(table.getSelectionModel().getSelectedIndex())
                .getNom());
          prenom.setText(String.valueOf(ls4
                .get(table.getSelectionModel().getSelectedIndex())
                .getPrenom()));
            tel.setText(String.valueOf(ls4
                .get(table.getSelectionModel().getSelectedIndex())
                .getTel()));
             sexe.setValue(String.valueOf(ls4
                .get(table.getSelectionModel().getSelectedIndex())
                .getSexe()));
             type.setValue(ls4
                .get(table.getSelectionModel().getSelectedIndex())
                .getType());
            } );
    }
    
     public boolean controletelephone() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tel.getText());
        if (m.find() && m.group().equals(tel.getText())) {
            return true;
        } else {
            return false;
        }
    }
       
    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         Agent_AutoEcole agent_AutoEcole=new Agent_AutoEcole();
         agent_AutoEcole.setNom(nom.getText());
            agent_AutoEcole.setPrenom(prenom.getText());
            agent_AutoEcole.setSexe(sexe.getValue());
            if (("".equals(tel.getText())) || (controletelephone()==false)){
              agent_AutoEcole.setTel(0);  
             }else{
            agent_AutoEcole.setTel(Integer.valueOf(tel.getText()));
            }
            agent_AutoEcole.setType(type.getValue());
             String req1 = "select id_agence from `agence` where nom_agence='"+agence.getValue()+"'";
            Connection con = DataSource.getInstance().getConnection();
             PreparedStatement pre1;
          pre1 = con.prepareStatement(req1);
          ResultSet rs1 = pre1.executeQuery();
         while (rs1.next()) {
         agent_AutoEcole.setId_agence(rs1.getInt(1));  
         }
         
         if (agent_AutoEcole.getNom().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un nom!");
            alert1.show();}
          else if (agent_AutoEcole.getPrenom().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un prenom!");
            alert1.show();}
           else if (String.valueOf(agent_AutoEcole.getTel()).length() != 8) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un numero de téléphone avec 8 chiffres");
            alert1.show();}
           else if ("Aucune".equals(sexe.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le sexe de l'agent !");
            alert1.show();}
           else if ("Aucun".equals(type.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le type de l'agent !");
            alert1.show();}
           else if ("Aucune".equals(agence.getValue())){ //controle de saisie ville 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir l'agence !");
            alert1.show();}
          else{
         Service_Agent_AutoEcole ser=new Service_Agent_AutoEcole();
            ser.ajouterAgent(agent_AutoEcole);
            refraichir();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Agent ajoutée avec succée!");
            alert.show();}
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        String req1="update agent set prenom='"+prenom.getText()+"' where nom='" +nom.getText()+"'";
        String req2="update agent set sexe='"+sexe.getValue()+"' where nom='" +nom.getText()+"'";
        String req3="update agent set telephone='"+tel.getText()+"' where nom='" +nom.getText()+"'";
        String req4="update agent set type='"+type.getValue()+"' where nom='" +nom.getText()+"'";
        
       
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
         PreparedStatement pre3 = con.prepareStatement(req3);
         PreparedStatement pre4 = con.prepareStatement(req4);
          if (nom.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un nom!");
            alert1.show();}
          else if (prenom.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un prenom!");
            alert1.show();}
           else if (String.valueOf(tel.getText()).length() != 8) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir un numero de téléphone avec 8 chiffres");
            alert1.show();}
          else{
          pre1.executeUpdate();
         pre2.executeUpdate();
         pre3.executeUpdate();
         pre4.executeUpdate();
         refraichir1();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Agent modifiée avec succée!");
            alert.show();}
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous vraiment supprimer cet agent?");
                Optional <ButtonType> action = alert.showAndWait();
                 if (action.get()==ButtonType.OK){
         String req = "delete from `agent` where nom='" +nom.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre = con.prepareStatement(req);
         pre.executeUpdate();
         refraichir1();
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Agent supprimée avec succée!");
            alert1.show();
                 }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        nom.getScene().setRoot(root);
    }

    @FXML
    private void refresh(ActionEvent event) {
        agence.getSelectionModel().select("Aucune");
        type.getSelectionModel().select("Aucun");
        sexe.getSelectionModel().select("Aucune");
       nom.setEditable(true);
        prenom.setText("");
         nom.setText("");
          tel.setText("");
    }
    
}
