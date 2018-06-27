/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Trigui
 */
public class DataSource {

    private static DataSource data;
    private Connection con;
    String username = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/projet";

    private DataSource() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;

    }
}
    


