/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.produitinterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.produit;
import models.Categorie;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


import util.MyConnection;

/**
 *
 * @author Walid
 */
public class produitservice implements produitinterface{
    
    
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addproduit(produit p) {         
        try {
            String req = "INSERT INTO `produit`(`nom`, `description`, `statut`, `valeur`, `date`) VALUES (?,?,?,?,?)" ;
            
            //Statement st = cnx.createStatement();
            PreparedStatement st = cnx.prepareStatement(req);
            
             st.setString(1, p.getNom());
             st.setString(2, p.getDescription());
             st.setString(3, p.getStatut());
             st.setFloat(4, p.getValeur());
             st.setDate(5, new java.sql.Date(p.getDate().getTime()));
           
            st.executeUpdate();
            System.out.println("Produit Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public List<produit> fetchProduits() {
        List<produit> produits = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM `produit`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                produit p = new produit();
                p.setId_prod(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setStatut(rs.getString(4));
                p.setValeur(rs.getFloat("valeur"));
                p.setDate(rs.getDate("date"));
                
                produits.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return produits;
    }
    
    @Override
    public void affecterproduit(produit p, Categorie c) {
        try {
            String req ="UPDATE `produit` SET `catego`= ? WHERE id_prod = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId_catego());
            ps.setInt(2, p.getId_prod());
            ps.executeUpdate();
            System.out.println("Produit updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void deletebyIdproduit(int id){
        
        try {
            String req = "DELETE FROM `produit` WHERE id_prod = "+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void updateproduit(int id , String nom, String description, String statut, Float valeur, Date date){
        
        try {
              
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String req = "UPDATE `produit` SET `nom`='" + nom + "',`description`='" + description +"',`statut`='" + statut + "',`valeur`='"+valeur+"',`date`='"+sdf.format(date)+"'  WHERE id_prod = " + id;
            Statement st = cnx.createStatement();
           
            st.executeUpdate(req);
            System.out.println("Produit updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    @Override
    public produit rechercherbyIdproduit(int id){
        
        produit p = new produit();
        try {
            String req = "SELECT * FROM `produit` WHERE id_prod = "+id;
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
                
                p.setId_prod(res.getInt(1));
                p.setNom(res.getString(2));
              p.setDescription(res.getString(3));
              p.setStatut(res.getString(4));
              p.setValeur(res.getFloat(5));
              p.setDate(res.getDate(6));
            }
             } catch (SQLException ex) {
            Logger.getLogger(produitservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       return p;
    }   
        
    
    
    
    @Override
    public void afficherproduit(produit p){
        
        try {
            String req = "SELECT `id_prod`, `description`, `statut`, `valeur`, `date` FROM `produit` WHERE id_prod = "+p.getId_prod();
            Statement st = cnx.createStatement();
            ResultSet rst=st.executeQuery(req);
            rst.last();
            int nbrow = rst.getRow();
            if (nbrow!=0){
                
                   System.out.println(p.getId_prod()+","+p.getDescription()+"','"+p.getStatut()+"',"+p.getValeur()+",'"+p.getDate());
                   
            }else{
                System.out.println("Produit not found !");
            }       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
        
        
    @Override
    public void tribydate(){
        
        try {
            String req = "SELECT * FROM `produit` ORDER BY date DESC ";
            Statement st = cnx.createStatement();
            ResultSet rst= st.executeQuery(req);
            System.out.println("Produits triés !");
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    
    
}