/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Walid
 */
public class Categorie {
    private String nom_catego;
    private int id_catego;

    public Categorie() {
    }

    public Categorie(String nom_catego, int id_catego) {
        this.nom_catego = nom_catego;
        this.id_catego = id_catego;
    }

    

    public String getNom_catego() {
        return nom_catego;
    }

    public void setNom_catego(String nom_catego) {
        this.nom_catego = nom_catego;
        
    }

    public int getId_catego() {
        return id_catego;
    }

    public void setId_catego(int id_catego) {
        this.id_catego = id_catego;
    }

    @Override
    public String toString() {
        return "Categorie{" + "nom_catego=" + nom_catego + ", id_catego=" + id_catego + '}';
    }

   
    
    
    
}
