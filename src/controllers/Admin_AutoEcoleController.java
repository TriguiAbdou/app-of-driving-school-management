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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import karhabty_pidev_entites.Agence_AutoEcole;
import karhabty_pidev_entites.Email;
import karhabty_pidev_utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Admin_AutoEcoleController implements Initializable {
    public ObservableList<Agence_AutoEcole> ls=FXCollections.observableArrayList();
     public ObservableList<Agence_AutoEcole> ls1=FXCollections.observableArrayList();
    @FXML
    private TableView<Agence_AutoEcole> table;
    @FXML
    private TableColumn<Agence_AutoEcole, String> nom;
    @FXML
    private TableColumn<Agence_AutoEcole, Integer> tel;
    @FXML
    private TextField nom_agence;
    @FXML
    private TextField raisons;
    @FXML
    private TableColumn<Agence_AutoEcole, String> horaire;
    @FXML
    private TableColumn<Agence_AutoEcole, String> rue;
    @FXML
    private TableColumn<Agence_AutoEcole, Integer> code;
    @FXML
    private TableColumn<Agence_AutoEcole, String> ville;
    @FXML
    private TableColumn<Agence_AutoEcole, String> piece;
    @FXML
    private TableColumn<Agence_AutoEcole, String> status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom_agence.setEditable(false);
        String req = "select * from `agence`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            ls.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("nom_agence") );
        tel.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("Telephone_agence") );
        horaire.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("horaire_travail") );
        rue.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("rue") );
        code.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, Integer>("code_postal") );
        ville.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("ville") );
        piece.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("piece_justificative") );
        status.setCellValueFactory(new PropertyValueFactory<Agence_AutoEcole, String>("status") );
        table.setItems(ls);
         table.setOnMouseClicked(event->{
         nom_agence.setText(ls
                .get(table.getSelectionModel().getSelectedIndex())
                .getNom_agence());
         
    } );
    }
    
    public void refresh(){
        ls1.clear();
        nom_agence.setEditable(false);
        String req = "select * from `agence`";
        Connection con = DataSource.getInstance().getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            Agence_AutoEcole p = null;
            
            while (rs.next()) {
            p = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
            ls1.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Agence_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_agence") );
        tel.setCellValueFactory(new PropertyValueFactory<>("Telephone_agence") );
        horaire.setCellValueFactory(new PropertyValueFactory<>("horaire_travail") );
        rue.setCellValueFactory(new PropertyValueFactory<>("rue") );
        code.setCellValueFactory(new PropertyValueFactory<>("code_postal") );
        ville.setCellValueFactory(new PropertyValueFactory<>("ville") );
        piece.setCellValueFactory(new PropertyValueFactory<>("piece_justificative") );
        status.setCellValueFactory(new PropertyValueFactory<>("status") );
        table.setItems(ls1);
         table.setOnMouseClicked(event->{
         nom_agence.setText(ls1
                .get(table.getSelectionModel().getSelectedIndex())
                .getNom_agence());
         
    } );
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, MessagingException {
        String req = "delete from `agence` where nom_agence='" +nom_agence.getText()+"'";
         Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre = con.prepareStatement(req);
         
         if (raisons.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir les raisons de la suppression de l'agence!");
            alert1.show();}
         else{
         pre.executeUpdate();
         refresh();
         String msg="Votre agence à été supprimer ,les raisons de suppression sont:"+raisons.getText();
         Email.Email("abderrahmen.trigui@esprit.tn", "KARHABTY", msg);
         }
    }

    @FXML
    private void approuver(ActionEvent event) throws SQLException, MessagingException {
        String req1="update agence set status='Approuvée' where nom_agence='" +nom_agence.getText()+"'";
        String req2="update agence set approved='1' where nom_agence='" +nom_agence.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
          pre1.executeUpdate();
         pre2.executeUpdate();
         refresh();
         String msg="Félicitation!Votre agence à été approuvée avec succés !Bienvenu dans votre espace auto-ecole";
         Email.Email("abderrahmen.trigui@esprit.tn", "Agence approuvé", msg);
    }

    @FXML
    private void rejeter(ActionEvent event) throws SQLException, MessagingException {
        if (raisons.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous devez saisir les raisons du rejet de l'agence!");
            alert1.show();}
         else{
         String req1="update agence set status='Rejetée' where nom_agence='" +nom_agence.getText()+"'";
        String req2="update agence set approved='0' where nom_agence='" +nom_agence.getText()+"'";
        Connection con = DataSource.getInstance().getConnection();
         PreparedStatement pre1 = con.prepareStatement(req1);
         PreparedStatement pre2 = con.prepareStatement(req2);
          pre1.executeUpdate();
         pre2.executeUpdate();
         refresh();
         String msg="Malheuresement,la demande de l'approuvement de votre agence a été rejeté !merci d'être compréhensive .les raisons de rejet sont: "+raisons.getText();
         Email.Email("abderrahmen.trigui@esprit.tn", "Agence refusée", msg);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_Principale_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        raisons.getScene().setRoot(root);
    }
    
}
