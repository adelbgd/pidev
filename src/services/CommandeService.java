/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import interfaces.CommandeInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.Commande;
import models.Livraison;
import util.MyConnection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author adelb
 */
public class CommandeService implements CommandeInterface{
    //var
    Connection cnx = MyConnection.getInstance().getCnx();
    
    public static final String ACCOUNT_SID = "AC614916aafd426f5b1381b56b63312de0";     /// 
    public static final String AUTH_TOKEN = "168bb11f6d7edfd5ba58926e2e7a33a5"; ///   
    public static final String TWILIO_NUMBER = "+15673131084";
 
    @Override
    public void addCommande(Commande c) {
        try {

            String req = "INSERT INTO `commande`(`date_commande`, `nom_prenom`, `num`, `mail`) VALUES(?,?,?,?)";
            //Statement st = cnx.Statement();
            PreparedStatement st = cnx.prepareStatement(req);
            st.setDate(1, new java.sql.Date(c.getDate_commande().getTime()));
            st.setString(2, c.getNom_prenom());
            st.setInt(3, c.getNum());
            st.setString(4, c.getMail());
            //st.setInt(4, c.getId_livraison().getId_livraison());

            st.executeUpdate();
            System.out.println("Commande Added successfully!");
            
                      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Remplacez le numéro de téléphone ci-dessous par le numéro de téléphone tunisien que vous voulez envoyer le SMS
       String phoneNumber = "+21629083510";
      
      Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(TWILIO_NUMBER),"Commande ajouter").create();
       // Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER),"Felicitation! la conventient avec votre garage a été accepté.").create();
        System.out.println(message.getSid());
            
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public List<Commande> fetchCommandes() {
        List<Commande> Commandes = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Commande c = new Commande();
                c.setId_commande(rs.getInt(1));
                c.setDate_commande(rs.getDate("date_commande"));
                c.setNom_prenom(rs.getString("nom_prenom"));
                c.setNum(rs.getInt("num"));
                c.setMail(rs.getString("mail"));
                /**Livraison l = new Livraison();
                    l.setId_livraison(rs.getInt(5));
                    c.setId_livraison(l);**/
                
                
                Commandes.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Commandes;
    }
    
    
    @Override
    public void affecterCommande(Commande c, Livraison l) {
        try {
            String req ="UPDATE `commande` SET `livraison`= ? WHERE id_commande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, l.getId_livraison());
            ps.setInt(2, c.getId_commande());
            ps.executeUpdate();
            System.out.println("commande updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void deleteCommande(int id_commande){
        
        try {
            String req = "DELETE FROM `commande` WHERE id_commande = "+id_commande;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void updateCommande(Commande c){
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String req = "UPDATE `commande` SET `date_commande`='"+c.getDate_commande()+"',`nom_prenom`='"+c.getNom_prenom()+"',`num`='"+c.getNum()+"',`mail`='"+c.getMail()+"' WHERE id_commande = "+c.getId_commande();
            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public Commande rechercherCommandebyid(int id_commande){
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody      
    Commande c = new Commande();
        try {
            String req="SELECT * FROM commande WHERE `id_commande`='"+id_commande+"'";
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(req);
            while(res.next()){
               LivraisonService se = new LivraisonService();
              c.setId_commande(res.getInt(1));
              c.setDate_commande(res.getDate(2));
              c.setNom_prenom(res.getString(3));
              c.setNum(res.getInt(4));
              c.setMail(res.getString(5));
              //Livraison l = se.rechercherLivraisonbyid(res.getInt(5));
              //c.setId_livraison(l);
              
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return c;
    }
    
    @Override
    public void afficherCommande(Commande c){
        
        try {
            String req = "SELECT `id_commande`, `date_commande`, `nom_prenom`, `num`, `mail` FROM `commande` WHERE id_commande = "+c.getId_commande();
            Statement st = cnx.createStatement();
            st.executeQuery(req);
            System.out.println("commande found successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
   

   
    }