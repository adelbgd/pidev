/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.produit;
import models.Categorie;
import java.util.Date;

/**
 *
 * @author Walid
 */
import java.io.FileNotFoundException;
import java.io.IOException;
public interface produitinterface {
    
    public void addproduit(produit p);
    
    
    //list : select
    public List<produit> fetchProduits();
    
    //affectation
    public void affecterproduit(produit p, Categorie c); 
    
    public void deletebyIdproduit(int id);
    
    public void updateproduit(produit p);
    // * @param id 
    
    public produit rechercherbyNomproduit(String nom);
    
    public void afficherproduit(produit p);
    
    public void tribydate();
    
   // public List<produit> getRecommendations(produit product);
    
    
    
    
    
}

