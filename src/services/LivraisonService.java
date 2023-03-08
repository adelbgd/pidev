/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.LivraisonInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Commande;
import models.Livraison;
import util.MyConnection;



public class LivraisonService implements LivraisonInterface{
    //var
    Connection cnx = MyConnection.getInstance().getCnx();

 @Override
    public void addLivraison(Livraison t) {
            try {
                String req = "INSERT INTO `livraison`(`date_livraison`, `region`, `ville`, `compagnie`, `status_livraison`, `frais_livraison`, `id_commande`) VALUES ('"+ t.getDate_livraison()+"','"+t.getLieu_livraison()+"','"+t.getVille()+"','"+t.getComp()+"','"+t.getStatus_livraison()+"','"+t.getFrais_livraison()+"','"+t.getId_commande().getId_commande()+"')";
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Livraison ajouter avec succes");
            } catch (SQLException ex) {
                Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**@Override
    public void addLivraison(Livraison l) {
        try {

            String req = "INSERT INTO `livraison`(`date_livraison`, `region`, `ville`, `compagnie`, `status_livraison`, `frais_livraison`, `id_commande`) VALUES(?,?,?,?,?,?,?)";
            //Statement st = cnx.Statement();
            PreparedStatement st = cnx.prepareStatement(req);
            st.setDate(1, new java.sql.Date(l.getDate_livraison().getTime()));
            st.setString(2, l.getLieu_livraison());
            st.setString(3, l.getVille());
            st.setString(4, l.getComp());
            st.setString(5, l.getStatus_livraison());
            st.setFloat(6, l.getFrais_livraison());
            //st.setInt(7, l.getId_commande().getId_commande());
            

            st.executeUpdate();
            System.out.println("Livraison Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    **/
    @Override
    public List<Livraison> fetchLivraisons() {
        List<Livraison> Livraisons = new ArrayList<>();
        CommandeService ls = new CommandeService();
        try {
            
            String req = "SELECT * FROM livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Livraison l = new Livraison();
                l.setId_livraison(rs.getInt(1));
                l.setDate_livraison(rs.getDate("date_livraison"));
                l.setLieu_livraison(rs.getString("region"));
                l.setVille(rs.getString("ville"));
                l.setComp(rs.getString("compagnie"));
                l.setStatus_livraison(rs.getString("status_livraison"));
                //l.setFrais_livraison(rs.getFloat("frais_livraison"));
                l.setFrais_livraison(rs.getFloat("frais_livraison"));
                l.setId_commande(ls.readbyid(rs.getInt(8)));
                
                
                Livraisons.add(l);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Livraisons;
    }
    
    
   /* @Override
    public void affecterLivraison(Livraison l, Commande c) {
        try {
            String req ="UPDATE `livraison` SET `commande`= ? WHERE id_livraison = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, l.getId_livraison());
            ps.setInt(2, c.getId_commande());
            ps.executeUpdate();
            System.out.println("Livraison updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }*/
    
    @Override
    public void deleteLivraison(int id_livraison){
        
        try {
            String req = "DELETE FROM `livraison` WHERE id_livraison = "+id_livraison;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("livraison deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void updateLivraison(Livraison l){
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String req = "UPDATE livraison SET `date_livraison`='"+l.getDate_livraison()+"',`region`='"+l.getLieu_livraison()+"',`ville`='"+l.getVille()+"',`compagnie`='"+l.getComp()+"',`status_livraison`='"+l.getStatus_livraison()+"',`frais_livraison`='"+l.getFrais_livraison()+"',`id_commande`='"+l.getId_commande().getId_commande()+"' WHERE id_livraison = "+l.getId_livraison();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("livraison updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
   
    

   /* @Override
    public Livraison rechercherLivraisonbyid(int id_livraison){
             //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody      
    Livraison l = new Livraison();
        try {
            String req="SELECT * FROM commande WHERE `id_livraison`='"+id_livraison+"'";
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
              
              l.setId_livraison(res.getInt(1));
              l.setDate_livraison(res.getDate(2));
              l.setLieu_livraison(res.getString(3));
              l.setStatus_livraison(res.getString(4));
              l.setFrais_livraison(res.getFloat(5));
              
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return l;
    }*/
    
      @Override
    public Livraison rechercherLivraisonbydate(String ville){
             //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody      
    Livraison l = new Livraison();
        try {
            String req="SELECT * FROM livraison WHERE `ville`='"+ville+"'";
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
               CommandeService cs = new CommandeService();
              l.setId_livraison(res.getInt(1));
              l.setDate_livraison(res.getDate(2));
              l.setLieu_livraison(res.getString(3));
              l.setVille(res.getString(4));
              l.setComp(res.getString(5));
              l.setStatus_livraison(res.getString(6));
              l.setFrais_livraison(res.getFloat(7));
              Commande c = cs.readbyid(res.getInt(8));
              l.setId_commande(c);
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return l;
    }
    
    
      @Override
    public Livraison readbyid(int id_livraison){
        
        Livraison l = new Livraison();
        try {
            String req = "SELECT * FROM livraison WHERE `id_livraison` = '"+id_livraison+"'";
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
                CommandeService cs = new CommandeService();
                l.setId_livraison(res.getInt(1));
                l.setLieu_livraison(res.getString(3));
                l.setVille(res.getString(4));
                l.setComp(res.getString(5));
                l.setStatus_livraison(res.getString(6));
                l.setFrais_livraison(res.getFloat(7));
                Commande c = cs.readbyid(res.getInt(8));
                l.setId_commande(c);
              
              
            }
             } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return l;
    }
    
    
    
    
    
    @Override
    public void afficherLivraison(Livraison l){
        
        try {
            String req = "SELECT `id_livraison`, `date_livraison`, `lieu_livraison`, `status_livraison`, `frais_livraison` FROM `livraison` WHERE id_livraison = "+l.getId_livraison();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("livraison found successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
   
    @Override
     public Livraison getlivraison(Livraison l){
        l.getId_livraison();
        l.getLieu_livraison();
        l.getVille();
        l.getComp();
        l.getStatus_livraison();
        l.getFrais_livraison();
        return l;
    }

    @Override
    public void affecterLivraison(Livraison l, Commande c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livraison rechercherLivraisonbyid(int id_livraison) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
   
    
}
