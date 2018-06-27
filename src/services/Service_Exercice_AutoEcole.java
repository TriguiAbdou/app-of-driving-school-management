/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import karhabty_pidev_entites.Exercice_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 *
 * @author Trigui
 */
public class Service_Exercice_AutoEcole {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    public Service_Exercice_AutoEcole(){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<Exercice_AutoEcole> afficher() throws SQLException {
    List<Exercice_AutoEcole> ls = new ArrayList();
    ResultSet rs = ste.executeQuery("select * from exercice");
    Exercice_AutoEcole a = null;
    while (rs.next()) {
      a = new Exercice_AutoEcole(rs.getInt(2), rs.getInt(3), rs.getString(4) ,rs.getString(5), rs.getFloat(6), rs.getString(7));
      ls.add(a);
      }
    return ls;
    }
    
    public void ajouterExercice(Exercice_AutoEcole a) throws SQLException {
      String req = "INSERT INTO `exercice` ( `id_agent`, `id_client`, `matricule`,`type`, `prix`, `date_heure`) "
                + "VALUES (?,?,?,?,?,?)";
      PreparedStatement pre = con.prepareStatement(req);
      pre.setInt(1, a.getId_agent());
      pre.setInt(2, a.getId_client());
      pre.setString(3, a.getMatricule());
      pre.setString(4, a.getType());
      pre.setFloat(5, a.getPrix());
      pre.setString(6, a.getDate_heure());
      pre.executeUpdate();
  }
    
    public void deleteExercice(Exercice_AutoEcole a) throws SQLException {
      String req = "delete from exercice where date_heure='" +a.getDate_heure()+"'";
      PreparedStatement pre = con.prepareStatement(req);
      pre.executeUpdate();
  } 
     
     
     public void updateExercice(Exercice_AutoEcole a) throws SQLException {
     String req = "UPDATE exercice SET prix='2000' where date_heure='" +a.getDate_heure()+"'";
     PreparedStatement pre = con.prepareStatement(req);
     pre.executeUpdate();
  }

    
}
