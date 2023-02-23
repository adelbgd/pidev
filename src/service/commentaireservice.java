/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.commentaireinterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.commentaire;

import util.MyConnection;
/**
 *
 * @author aziz1
 */
public class commentaireservice {
    //var
    Connection cnx = MyConnection.getInstance().getCnx();
//*
public void addCommentaire(commentaire c) {
    try {
       
        String req = "INSERT INTO commentaire (id_pub, id_user, username, suj_com, date_com, nb_reaction) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, c.getId_pub());
        ps.setInt(2, c.getId_user());
        ps.setString(3, c.getUsername());
        ps.setString(4, c.getSuj_com());
        ps.setDate(5, new Date (System.currentTimeMillis()));
        ps.setInt(6, c.getNb_reaction());
        
       
        ps.executeUpdate();

        System.out.println("New commentaire added ");
    } catch (SQLException ex) {
       ex.printStackTrace();
    }
}


    
    public List<commentaire> fetchCommentaire() {
        List<commentaire> commentaire = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                commentaire c = new commentaire();
                c.setId_com(rs.getInt(1));
                c.setId_pub(rs.getInt(2));
                c.setId_user(rs.getInt(3));
                c.setUsername(rs.getString(4));
                c.setSuj_com(rs.getString(5));
                c.setDate_com(rs.getDate(6));
                c.setNb_reaction(rs.getInt(7));
              
                
                commentaire.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(commentaire);
        return commentaire;
    }
    public List<commentaire> fetchCommentaireUser(int ID_user) {
        List<commentaire> commentaire = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commentaire WHERE id_user= ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ID_user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                commentaire c = new commentaire();
                c.setId_com(rs.getInt(1));
                c.setId_pub(rs.getInt(2));
                c.setId_user(rs.getInt(3));
                c.setUsername(rs.getString(4));
                c.setSuj_com(rs.getString(5));
                c.setDate_com(rs.getDate(6));
                c.setNb_reaction(rs.getInt(7));
              
                
                commentaire.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(commentaire);
        return commentaire;
    }
    public List<commentaire> fetchCommentairePublication(int ID_publication) {
        List<commentaire> commentaire = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commentaire WHERE id_pub= ?";
            PreparedStatement st = cnx.prepareStatement(req);
                        st.setInt(1, ID_publication);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                commentaire c = new commentaire();
                c.setId_com(rs.getInt(1));
                c.setId_pub(rs.getInt(2));
                c.setId_user(rs.getInt(3));
                c.setUsername(rs.getString(4));
                c.setSuj_com(rs.getString(5));
                c.setDate_com(rs.getDate(6));
                c.setNb_reaction(rs.getInt(7));
              
                
                commentaire.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(commentaire);
        return commentaire;
    }
     public List<commentaire> fetchCommentairePublicationUser(int ID_publication,int ID_user) {
        List<commentaire> commentaire = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commentaire WHERE id_pub= ? AND id_user=?";
            PreparedStatement st = cnx.prepareStatement(req);
                        st.setInt(1, ID_publication);
                         st.setInt(2, ID_user);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                commentaire c = new commentaire();
                c.setId_com(rs.getInt(1));
                c.setId_pub(rs.getInt(2));
                c.setId_user(rs.getInt(3));
                c.setUsername(rs.getString(4));
                c.setSuj_com(rs.getString(5));
                c.setDate_com(rs.getDate(6));
                c.setNb_reaction(rs.getInt(7));
              
                
                commentaire.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(commentaire);
        return commentaire;
    }
    
    
   
    public void deleteCommentaire(int id_com ){
        
        try {
            String req = "DELETE FROM `commentaire` WHERE id_com = "+id_com;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
    }
    
 public void updateCommentaire(commentaire c) {
    try {
        // on construit la requête SQL pour mettre à jour le commentaire
        String req = "UPDATE `commentaire` SET `suj_com`=?,`nb_reaction`=? WHERE id_com=?";
        PreparedStatement st = cnx.prepareStatement(req);
        
        // on définit les valeurs des paramètres de la requête
        
        st.setString(1, c.getSuj_com());
       
        st.setInt(2, c.getNb_reaction());
        
        st.setInt(3, c.getId_com());
        
        st.executeUpdate();
        System.out.println("commentaire updated successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}



        
        
    
    
    
   public void rechercheCommentaire(int id_com) {
    commentaire c = null;
    try {
        String req = "SELECT * FROM `commentaire` WHERE id_com = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id_com);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            c = new commentaire();
            c.setId_com(rs.getInt("id_com"));
            c.setId_pub(rs.getInt("id_pub"));
            c.setId_user(rs.getInt("id_user"));
            c.setUsername(rs.getString("username"));
            c.setSuj_com(rs.getString("suj_com"));
            c.setDate_com(rs.getDate("date_com"));
            c.setNb_reaction(rs.getInt("nb_reaction"));
            
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }System.out.println(c);
   
}

        
    
    
    
   public void afficherCommentaire(int id_com) {
    try {
        // on construit la requête SQL pour sélectionner le commentaire
        String req = "SELECT * FROM `commentaire` WHERE id_com=" + id_com;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        // on affiche les informations du commentaire
        while (rs.next()) {
            System.out.println("ID de publication : " + rs.getInt("id_pub"));
            System.out.println("ID d'utilisateur : " + rs.getInt("id_user"));
            System.out.println("Nom d'utilisateur : " + rs.getString("username"));
            System.out.println("Sujet du commentaire : " + rs.getString("suj_com"));
            System.out.println("Date du commentaire : " + rs.getDate("date_com"));
            System.out.println("Nombre de réactions : " + rs.getInt("nb_reaction"));
            
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}



   

   
    }