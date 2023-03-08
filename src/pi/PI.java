/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;
import services.CommandeService;
import interfaces.CommandeInterface;
import interfaces.LivraisonInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Commande;
import models.Livraison;
import services.LivraisonService;

/**
 *
 * @author adelb
 */
public class PI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    CommandeInterface ps= new CommandeService();
        
        
        Commande c1 = new Commande ();
        
        
        
      
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          
        try {
            Date date_commande = sdf.parse("2000-03-23");
            c1.setDate_commande(date_commande);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
         c1.setNom_prenom("ala");
         c1.setNum(290883510);
         //ps.addCommande(c1);
         System.out.println(ps.readbyid(89)); 
    
         //System.out.println(ps.fetchCommandes());
        
        
         //Commande c2 = new Commande();
         //c2.setId_commande(1);
         //Livraison l1 = new Livraison();
         //l1.setId_livraison(2);
         //ps.affecterCommande(c2, l1);
         
         
         /**SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
            c1.setDate_commande(java.sql.Date.valueOf("2023-11-12"));
            

            c1.setId_commande(21);
            c1.setClient("aziz");
            c1.setProduit("capuche");
           ps.updateCommande(c1);**/
       
           
        
         //System.out.println(ps.fetchCommandes());
        
         //ps.rechercherCommandebyid(3);
        
        //ps.deleteCommande(13);
          //System.out.println(ps.fetchCommandes());
          
          
          
         //ps.afficherCommande(p4);
         //ps.afficherCommande(p3);
        
         //ps.rechercherCommandebyid(9);
         //ps.rechercherCommandebyid(1);
    
        
       LivraisonInterface ps1= new LivraisonService();
       
       Livraison l2 = new Livraison ();
        
        
        
         
        /**try {
            Date date_livraison = format.parse("23-03-2000");
            l1.setDate_livraison(date_livraison);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }**/
         l2.setDate_livraison(java.sql.Date.valueOf("2023-12-10"));
         l2.setLieu_livraison("tunis");
         l2.setVille("gabes");
         l2.setComp("aze");
         l2.setStatus_livraison("en cours");
         l2.setFrais_livraison(7f);
         Commande c = new Commande();
         c.setId_commande(129);
         l2.setId_commande(c);
         //ps1.addLivraison(l2);
        
        
         
         
         //System.out.println(ps1.fetchLivraisons());
         
         
         //Livraison l2 = new Livraison();
         //l2.setId_livraison(1);
         //Commande c1 = new Commande();
         //c1.setId_commande(2);
         //ps1.affecterLivraison(l2, c1);
         
         
         /**SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format2.parse("2050-03-14");
           ps1.updateLivraison(10, date, "mourouj", "en cours", "a domicile", 7);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }**/
         
         
         
         //ps1.rechercherLivraisonbyid(5);
         //ps1.rechercherLivraisonbyid(1);
         
         //ps1.deleteLivraison(2);
        
         //ps1.afficherLivraison(l1);
         
       
         
        
        
}
    }
    

