/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.produit;
import models.Categorie;

/**
 *
 * @author Walid
 */
public interface categorieinterface {
    
    public void addcategorie(Categorie c);
    
    
    //list : select
    public List<Categorie> fetchcategories();
    
    //affectation
    public void affectercategorie(Categorie c, produit p); 
    
    public void deletebyIdcategorie(int id);
    
    public void updatecategorie(int id,String nom_catego); 
    
    public Categorie rechercherbyIdcategorie(int id);
    
    public void affichercategorie( Categorie c);
    
    public void tribydate();
    
    
}
