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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import karhabty_pidev_controllers.Gestion_Voiture_AutoEcoleController;
import karhabty_pidev_entites.voiture_AutoEcole;
import karhabty_pidev_services.Service_Voiture_AutoEcole;

import karhabty_pidev_utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Gestion_Voiture_AutoEcoleController implements Initializable {
  public ObservableList<voiture_AutoEcole> ls=FXCollections.observableArrayList();
  public ObservableList<String> ls1=FXCollections.observableArrayList();
  public ObservableList<voiture_AutoEcole> ls2=FXCollections.observableArrayList();
  public ObservableList<String> ls5=FXCollections.observableArrayList("Essence","Gazole");
    @FXML
    private TextField matricule;
    @FXML
    private TextField marque;
    @FXML
    private TextField couleur;
    @FXML
    private ComboBox<String> carburant;
    @FXML
    private TextField annee;
    @FXML
    private ComboBox<String> agence;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<voiture_AutoEcole> table;
    @FXML
    private TableColumn<voiture_AutoEcole, String> list_matricule;
    @FXML
    private TableColumn<voiture_AutoEcole, String> list_marque;
    @FXML
    private TableColumn<voiture_AutoEcole, String> list_couleur;
    @FXML
    private TableColumn<voiture_AutoEcole, Integer> list_agence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         carburant.getSelectionModel().select("Aucun");
          agence.getSelectionModel().select("Aucune");
        carburant.setItems(ls5);
        matricule.setPromptText("Entrer la matricule de la voiture");
        marque.setPromptText("Entrer la marque de la voiture");
        couleur.setPromptText("Entrer la couleur de la voiture");
        annee.setPromptText("Entrer l'année");
         String req = "select * from `voiture`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            voiture_AutoEcole p = null;
            while (rs.next()) {
            p = new voiture_AutoEcole(rs.getString(1),rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getInt(18) );
            ls.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        list_matricule.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("matricule") );
        list_marque.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("marque") );
        list_couleur.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("couleur") );
       list_agence.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("carburant") );
        
        table.setItems(ls);
        table.setOnMouseClicked(event->{
         matricule.setText(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getMatricule());
          marque.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getMarque()));
            couleur.setText(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getCouleur());
             carburant.setValue(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getCarburant()));
             annee.setText(String.valueOf(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getAge()));
              matricule.setEditable(false);
              
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
        String req1 = "select nom_agence from `agence` ";//where id_manager='07468678'";
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
        agence.setItems(ls1);
           
    }    

    
    public void refraichir(){
        String req = "select * from `voiture` where matricule='"+matricule.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            voiture_AutoEcole p = null;
            
            while (rs.next()) {
            p = new voiture_AutoEcole(rs.getString(1), rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getInt(18));
            //p1=new Agence_AutoEcole(rs.getString(2),rs.getInt(3), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9), rs.getString(11));
            ls.add(p);
            //System.out.println(p);
            
            
        }} catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        list_matricule.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("matricule") );
        list_marque.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("marque") );
        list_couleur.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("couleur") );
       list_agence.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("carburant") );
        table.setItems(ls);
    }
    
    
    public void refraichir1(){
        ls2.clear();
        String req = "select * from `voiture`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            voiture_AutoEcole p = null;
            while (rs.next()) {
            p = new voiture_AutoEcole(rs.getString(1),rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getInt(18) );
            ls2.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        list_matricule.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("matricule") );
        list_marque.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("marque") );
        list_couleur.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, String>("couleur") );
       list_agence.setCellValueFactory(new PropertyValueFactory<voiture_AutoEcole, Integer>("carburant") );
        
        table.setItems(ls2);
        table.setOnMouseClicked(event->{
         matricule.setText(ls2
                .get(table.getSelectionModel().getSelectedIndex())
                .getMatricule());
          marque.setText(String.valueOf(ls2
                .get(table.getSelectionModel().getSelectedIndex())
                .getMarque()));
            couleur.setText(ls2
                .get(table.getSelectionModel().getSelectedIndex())
                .getCouleur());
             carburant.setValue(String.valueOf(ls2
                .get(table.getSelectionModel().getSelectedIndex())
                .getCarburant()));
             annee.setText(String.valueOf(ls2
                .get(table.getSelectionModel().getSelectedIndex())
                .getAge()));
            } );
    }
    
    
    public boolean controleMatricule() {
        Pattern p = Pattern.compile("[0-9]+ TU [0-9]+");
        Matcher m = p.matcher(matricule.getText());
        if (m.find() && m.group().equals(matricule.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
     public boolean controleannee() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(annee.getText());
        if (m.find() && m.group().equals(annee.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        voiture_AutoEcole Voiture_AutoEcole=new voiture_AutoEcole();
            Voiture_AutoEcole.setMatricule(matricule.getText());
            Voiture_AutoEcole.setMarque(marque.getText());
            Voiture_AutoEcole.setCouleur(couleur.getText());
            Voiture_AutoEcole.setCarburant(carburant.getValue());
             if (("".equals(annee.getText())) || (controleannee()==false)){
               Voiture_AutoEcole.setAge(0);
            }else{
            Voiture_AutoEcole.setAge(Integer.valueOf(annee.getText()));
             }
           //récuperer l id de l agence correspond au nom de l agence choisi
            String req1 = "select id_agence from `agence` where nom_agence='"+agence.getValue()+"'";
            Connection con = DataSource.getInstance().getConnection();
             PreparedStatement pre1;
          pre1 = con.prepareStatement(req1);
          ResultSet rs1 = pre1.executeQuery();
         while (rs1.next()) {
         Voiture_AutoEcole.setId_agence(rs1.getInt(1));  
         }
            
         if(controleMatricule()==false){
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("La matricule doit étre de la forme ' *** TU **** ' !");
            alert1.show();
         }
         else if ((String.valueOf(Voiture_AutoEcole.getAge()).length() != 4) || ((Voiture_AutoEcole.getAge()) > 2017) || ((Voiture_AutoEcole.getAge()) < 1990)) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir l'année de mise en circulation correctement !");
            alert1.show();}
          else if (Voiture_AutoEcole.getMarque().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir la marque de votre voiture!");
            alert1.show();}
           else if (Voiture_AutoEcole.getMatricule().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir la matricule de votre voiture!");
            alert1.show();}
            else if (Voiture_AutoEcole.getCarburant().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le type du carburant de votre voiture!");
            alert1.show();}
            else if (Voiture_AutoEcole.getCouleur().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir la couleur de votre voiture!");
            alert1.show();}
             else if ("Aucun".equals(carburant.getValue())){ //controle de saisie carburant
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le type de carburant !");
            alert1.show();}
              else if ("Aucune".equals(agence.getValue())){ //controle de saisie agence 
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir une agence !");
            alert1.show();}
            else{
            Service_Voiture_AutoEcole ser=new Service_Voiture_AutoEcole();
            ser.ajouterVoiture(Voiture_AutoEcole);
            refraichir();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Voiture ajoutée avec succée!");
            alert.show();
    }
}

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        String req1="update voiture set marque='"+marque.getText()+"' where matricule='" +matricule.getText()+"'";
        String req2="update voiture set couleur='"+couleur.getText()+"' where matricule='" +matricule.getText()+"'";
        String req3="update voiture set carburant='"+carburant.getValue()+"' where matricule='" +matricule.getText()+"'";
        String req4="update voiture set age='"+annee.getText()+"' where matricule='" +matricule.getText()+"'";
        
       
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
         PreparedStatement pre3 = con.prepareStatement(req3);
         PreparedStatement pre4 = con.prepareStatement(req4);
         if(controleMatricule()==false){
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("La matricule doit étre de la forme ' ** TU *** ' !");
            alert1.show();
         }
         else if (String.valueOf(annee.getText()).length() != 4) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir l'année de mise en circulation (4 chiffres)!");
            alert1.show();}
          else if (marque.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir la marque de votre voiture!");
            alert1.show();}
           else if (matricule.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir la matricule de votre voiture!");
            alert1.show();}
            else if (carburant.getValue().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir le type du carburant de votre voiture!");
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
         alert.setContentText("Voiture modifiée avec succés!");
         alert.show();}
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous vraiment supprimer cette voiture ?");
                Optional <ButtonType> action = alert.showAndWait();
                 if (action.get()==ButtonType.OK){
        String req = "delete from `voiture` where matricule='" +matricule.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre = con.prepareStatement(req);
         pre.executeUpdate();
         refraichir1();
          Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Information Dialog");
         alert1.setHeaderText(null);
         alert1.setContentText("Voiture supprimée avec succés!");
         alert1.show();
                 }
    }

    @FXML
    private void refresh(ActionEvent event) {
         carburant.getSelectionModel().select("Aucun");
          agence.getSelectionModel().select("Aucune");
        matricule.setEditable(true);
        matricule.setText("");
         
          couleur.setText("");
           annee.setText("");
            marque.setText("");
            
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        marque.getScene().setRoot(root);
    }
    
}
