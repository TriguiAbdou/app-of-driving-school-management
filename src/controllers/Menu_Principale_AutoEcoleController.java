/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Trigui
 */
public class Menu_Principale_AutoEcoleController implements Initializable {

    @FXML
    private Label lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void manager(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Menu_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        lab.getScene().setRoot(root);
    }

    @FXML
    private void admin(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Admin_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        lab.getScene().setRoot(root);
    }

    @FXML
    private void client(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/karhabty_pidev_views/Client_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        lab.getScene().setRoot(root);
    }
    
}
