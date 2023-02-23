/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.mycompany.pidev;
import interfaces.adminInterfaces;
import models.admin;
import models.client;
import util.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class adminServices implements adminInterfaces {
     Connection cnx = Myconnexion.getInstance().getCnx() ;
    private String req;

    /**
     *
     * @param u
     */
    @Override
    public void ajouter(admin u) {
         try {
         String req = "INSERT INTO admin (cin, nom,prenom,email,pwd) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,u.getCin());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       
    }

    public void supprimer(String cin) {
       try {
            String req = "DELETE FROM admin WHERE cin = " + cin;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("admin deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     @Override
    public void modifier (String cin,String nom,String prenom){
        
          try {
            String req = "UPDATE `admin` SET `nom`='"+nom+"',`prenom`='"+prenom+"' WHERE `cin`='"+cin;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("admin updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    };

    @Override
    public void supprimer(admin am) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getall(admin am) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        }
    

   /*public admin getOneById(String cin) {
        String query = "SELECT * FROM admin WHERE cin = " + cin + "";
        admin a  = new admin();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return a;
    }*/

    
   /* public List<admin> getall() {
        List<admin> listeadmin = new ArrayList<>();
        String query = "SELECT * FROM admin ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                admin a = new admin();
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));
               
                listeadmin.add(a);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
      /*  return listeadmin;*/
    

    
    

  /*  @Override
    public void getall(admin am) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(admin am) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   /* public void Modifier(admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

  /*  @Override
    public void modifier(admin am) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

  

   

    
    
