/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.categorieinterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Categorie;
import models.produit;
import util.MyConnection;

/**
 *
 * @author Walid
 */
public class categorieservice implements categorieinterface {
    
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addcategorie(Categorie c) {
        try {
            String req = "INSERT INTO `categorie`(`nom_catego`) VALUES('"+c.getNom_catego()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public List<Categorie> fetchcategories() {
        List<Categorie> Categories = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM `categorie`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Categorie c = new Categorie();
                c.setId_catego(rs.getInt(1));
                c.setNom_catego(rs.getString("nom_catego"));
                
                
                Categories.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Categories;
    }
    
    @Override
    public void affectercategorie(Categorie c, produit p) {
        try {
            String req ="UPDATE `categorie` SET `id_prod`= ? WHERE id_catego = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId_catego());
            ps.setInt(2, p.getId_prod());
            ps.executeUpdate();
            System.out.println("categorie updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void deletebyIdcategorie(int id){
        
        try {
            String req = "DELETE FROM `categorie` WHERE id_catego = "+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void updatecategorie(int id,String nom_catego){
        
        try {
            String req = "UPDATE `categorie` SET `nom_catego`='"+nom_catego+"' WHERE id_catego = "+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public Categorie rechercherbyIdcategorie(int id){
        Categorie c = new Categorie();
        
        try {
            String req = "SELECT * FROM `categorie` WHERE id_catego = "+id;
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
                c.setId_catego(res.getInt(1));
                c.setNom_catego(res.getString(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return c; 
    }
    
    
    @Override
    public void affichercategorie( Categorie c){
        
        try {
            String req = "SELECT `id_catego`, `nom_catego` FROM `categorie` WHERE id_catego = "+c.getId_catego();
            Statement st = cnx.createStatement();
            ResultSet rst=st.executeQuery(req);
            rst.last();
            int nbrow = rst.getRow();
            if (nbrow!=0){
                
                   System.out.println(c.getId_catego()+","+c.getNom_catego());
                   
            }else{
                System.out.println("categorie not found !");
            }       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
        
        
    @Override
    public void tribydate(){
        
        try {
            String req = "SELECT * FROM `categorie` ORDER BY nom_catego DESC ";
            Statement st = cnx.createStatement();
            ResultSet rst= st.executeQuery(req);
            System.out.println("categories triés !");
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
}
