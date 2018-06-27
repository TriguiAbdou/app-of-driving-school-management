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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import karhabty_pidev_utils.DataSource;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Menu_AutoEcoleController implements Initializable {

    @FXML
    private Button agence_ae;
    @FXML
    private Button voiture_ae;
    @FXML
    private Button agent_ae;
    @FXML
    private Button exercice_ae;
    @FXML
    private Label nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        String req1 = "select nom from `user` where id='07468678'";
            Connection con = DataSource.getInstance().getConnection();
             
        try {
            PreparedStatement pre1;
            pre1 = con.prepareStatement(req1);
            ResultSet rs1 = pre1.executeQuery();
            while (rs1.next()) {
         nom.setText("bienvenue "+rs1.getString(1));  
         }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_AutoEcoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
    }    

    @FXML
    private void gestion_agence(ActionEvent event) throws IOException {
         
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Gestion_Agence_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        agent_ae.getScene().setRoot(root);
    }

    @FXML
    private void gestion_voiture(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Gestion_Voiture_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        agent_ae.getScene().setRoot(root);
    }

    @FXML
    private void gestion_agent(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Gestion_Agent_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        agent_ae.getScene().setRoot(root);
    }

    @FXML
    private void gestion_exercice(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Gestion_Exercice_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        agent_ae.getScene().setRoot(root);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_Principale_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        agent_ae.getScene().setRoot(root);
    }

    
}
