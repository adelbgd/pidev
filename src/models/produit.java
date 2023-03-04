/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Image;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



/**
 *
 * @author Walid
 */
public class produit {
    private int id_prod;
    private String description, statut, nom;
    private float valeur;
    private Date date;
    private Categorie catego;
    private String image;
    private int id_catego;

    public int getId_catego() {
        return id_catego;
    }

    public void setId_catego(int id_catego) {
        this.id_catego = id_catego;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public produit(int id_prod, String description, String statut, float valeur, Date date, Categorie catego, String nom, String image) {
        this.id_prod = id_prod;
        this.description = description;
        this.statut = statut;
        this.valeur = valeur;
        this.date = date;
        this.catego = catego;
        this.nom = nom;
    }

    

    public produit() {
    }
    
    
    

   public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categorie getCatego() {
        return catego;
    }

    public void setCatego(Categorie catego) {
        this.catego = catego;
    }

    @Override
    public String toString() {
        return "produit{" + "id_prod=" + id_prod + ", description=" + description + ", statut=" + statut + ", valeur=" + valeur + ", date=" + date + ", catego=" + catego + '}';
    }

    public List<Integer> getRatings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAverageRating(double averageRating) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
    
    
    
    
    
    
}
