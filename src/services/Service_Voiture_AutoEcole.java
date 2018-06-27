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
import karhabty_pidev_entites.Agence_AutoEcole;
import karhabty_pidev_entites.voiture_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 *
 * @author Trigui
 */
public class Service_Voiture_AutoEcole {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
     public Service_Voiture_AutoEcole(){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
     
     public List<voiture_AutoEcole> afficher() throws SQLException {
    List<voiture_AutoEcole> ls = new ArrayList();
    ResultSet rs = ste.executeQuery("select * from voiture");
    voiture_AutoEcole a = null;
    while (rs.next()) {
      a = new voiture_AutoEcole(rs.getString(1), rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getInt(18));
      ls.add(a);
      }
    return ls;
    }
     
     
     public void ajouterVoiture(voiture_AutoEcole a) throws SQLException {
      String req = "INSERT INTO `voiture` ( `matricule`, `marque`, `couleur`,`carburant`, `age`, `id_agence`) "
                + "VALUES (?,?,?,?,?,?)";
      PreparedStatement pre = con.prepareStatement(req);
      pre.setString(1, a.getMatricule());
      pre.setString(2, a.getMarque());
      pre.setString(3, a.getCouleur());
      pre.setString(4, a.getCarburant());
      pre.setInt(5, a.getAge());
      pre.setInt(6, a.getId_agence());
      pre.executeUpdate();
  }
        
     public void deleteVoiture(voiture_AutoEcole a) throws SQLException {
      String req = "delete from voiture where marque='" +a.getMarque()+"'";
      PreparedStatement pre = con.prepareStatement(req);
      pre.executeUpdate();
  } 
     
     
     public void updateVoiture(voiture_AutoEcole a) throws SQLException {
     String req = "UPDATE voiture SET couleur='vert' where marque='" +a.getMarque()+"'";
     PreparedStatement pre = con.prepareStatement(req);
     pre.executeUpdate();
  }
    
}
