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
import karhabty_pidev_entites.Agent_AutoEcole;
import karhabty_pidev_utils.DataSource;

/**
 *
 * @author Trigui
 */
public class Service_Agent_AutoEcole {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    public Service_Agent_AutoEcole(){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<Agent_AutoEcole> afficher() throws SQLException {
    List<Agent_AutoEcole> ls = new ArrayList();
    ResultSet rs = ste.executeQuery("select * from agent");
    Agent_AutoEcole a = null;
    while (rs.next()) {
      a = new Agent_AutoEcole(rs.getInt(2), rs.getString(3), rs.getString(4) ,rs.getString(5), rs.getInt(6), rs.getString(7));
      ls.add(a);
      }
    return ls;
    }

public void ajouterAgent(Agent_AutoEcole a) throws SQLException {
      String req = "INSERT INTO `agent` ( `id_agence`, `nom`, `prenom`,`sexe`, `telephone`, `type`) "
                + "VALUES (?,?,?,?,?,?)";
      PreparedStatement pre = con.prepareStatement(req);
      pre.setInt(1, a.getId_agence());
      pre.setString(2, a.getNom());
      pre.setString(3, a.getPrenom());
      pre.setString(4, a.getSexe());
      pre.setInt(5, a.getTel());
      pre.setString(6, a.getType());
      pre.executeUpdate();
  }

 public void deleteAgent(Agent_AutoEcole a) throws SQLException {
      String req = "delete from agent where nom='" +a.getNom()+"'";
      PreparedStatement pre = con.prepareStatement(req);
      pre.executeUpdate();
  } 
     
     
     public void updateAgent(Agent_AutoEcole a) throws SQLException {
     String req = "UPDATE agent SET prenom='chihemek' where nom='" +a.getNom()+"'";
     PreparedStatement pre = con.prepareStatement(req);
     pre.executeUpdate();
  }

}

