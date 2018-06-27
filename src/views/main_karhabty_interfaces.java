/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_views;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Trigui
 */
public class main_karhabty_interfaces extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Menu_Principale_AutoEcole.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
