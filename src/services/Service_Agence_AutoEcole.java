/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karhabty_pidev_services;
import karhabty_pidev_entites.Agence_AutoEcole;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import karhabty_pidev_utils.DataSource;

/**
 *
 * @author Trigui
 */
public class Service_Agence_AutoEcole {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    public Service_Agence_AutoEcole(){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public List<Agence_AutoEcole> afficher() throws SQLException {
    List<Agence_AutoEcole> ls = new ArrayList();
    ResultSet rs = ste.executeQuery("select * from agence");
    Agence_AutoEcole a = null;
    while (rs.next()) {
      a = new Agence_AutoEcole(rs.getString(2), rs.getInt(3), rs.getString(5) ,rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(8), rs.getString(12) );
      a.setId_agence(rs.getInt(1));
      ls.add(a);
      }
    return ls;
    }

  
  public void ajouterAgence(Agence_AutoEcole a) throws SQLException {
      String req = "INSERT INTO `agence` ( `nom_agence`, `telephone_agence`, `horaire_travail`,`rue`, `code_postal`, `ville`,`piece_justificative`,`status`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
      PreparedStatement pre = con.prepareStatement(req);
      pre.setString(1, a.getNom_agence());
      pre.setInt(2, a.getTelephone_agence());
      pre.setString(3, a.getHoraire_travail());
      pre.setString(4, a.getRue());
      pre.setInt(5, a.getCode_postal());
      pre.setString(6, a.getVille());
      pre.setString(7, a.getPiece_justificative());
      pre.setString(8, a.getStatus());
      
      pre.executeUpdate();
  }
        
  
  public void deleteAgence(Agence_AutoEcole a) throws SQLException {
      String req = "delete from agence where nom_agence='" +a.getNom_agence()+"'";
      PreparedStatement pre = con.prepareStatement(req);
      pre.executeUpdate();
  }
  
  
  public void updateAgence(Agence_AutoEcole a) throws SQLException {
     String req = "UPDATE agence SET nom_agence='abdou' where nom_agence='" +a.getNom_agence()+"'";
     PreparedStatement pre = con.prepareStatement(req);
     pre.executeUpdate();
  }
    
    
}
