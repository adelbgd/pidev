/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetswappy2;


import interfaces.categorieinterface;
import services.produitservice;
import interfaces.produitinterface;
import java.text.ParseException;
import models.produit;
import models.Categorie;
import java.util.Date;
import services.categorieservice;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Walid
 */
public class Projetswappy2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         produitinterface ps= new produitservice();
        
        produit p1 = new produit ();
        
        produit p2 = new produit ();
        
       // produit p4 = new produit ();
       /** p3.setNom("IPHONE");
        p3.setDescription("NEUF");
        p3.setStatut("dispo");
        p3.setValeur(2500);
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse("2010-03-2010");
            p1.setDate(date1);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
       
        ps.addproduit(p3);**/
       
       /** p2.setNom("aaaaaa");
        p2.setDescription("bbbbbbb importé");
        p2.setStatut("non disponible");
        p2.setValeur(760);
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse("23-03-2013");
            p2.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
       
        
        ps.addproduit(p2);**/
       
        
        /** SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse("2023-03-14");
            ps.updateproduit(17, "phone", "nouveau", "dispo", 570f, date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }**/
      
        
         //p3.setDescription("hoodie");
        //p3.setStatut("disponible");
        //p3.setValeur(600);
       // p3.setDate("29199/43949/404");
        
        
        
       // p4.setDescription("mercedes");
        //p4.setStatut("NON disponible");
        //p4.setValeur(900);
       // p4.setDate("2013/06/08");
        
        
        
       //ps.addproduit(p4);
        
        
        
        
        
        
        //ps.addproduit(p1);
        //System.out.println(ps.fetchProduits());
        
        
        //produit p2 = new produit();
        //p2.setId_prod(1);
        //Categorie c1 = new Categorie();
        //c1.setId_catego(2);
        
        //ps.affecterproduit(p2, c1);
        
        
        //ps.rechercherproduit(p1);
        
       // ps.deletebyIdproduit(4);
       // System.out.println(ps.fetchProduits());
        
        //ps.afficherproduit(p4);
        //ps.afficherproduit(p3);
        
        //ps.rechercherbyIdproduit(9);
        
       // ps.rechercherbyIdproduit(1);
       
        
         
       
       
       
        //categorieinterface ps1= new categorieservice();
        
        //Categorie c1 = new Categorie ();
        
        //Categorie c3 = new Categorie ();
        
       // c1.setNom_catego("Accesoires");
        
        //c3.setNom_catego("vetements");
        
       // ps1.addcategorie(c1);
        //ps1.addcategorie(c3);
        
        //System.out.println(ps1.fetchcategories());
        
        //Categorie c4 = new Categorie ();
        //c4.setId_catego(1);
        //ps1.addcategorie(c4);
        //produit p6 = new produit();
        //p6.setId_prod(2);
        //ps1.affectercategorie(c4, p6);
        
        //ps1.deletebyIdcategorie(1);
        
        //ps1.rechercherbyIdcategorie(9);
        
        //ps1.updatecategorie(2, "accesoires");
        
        
        
        
    
    }
}
  
    
    

