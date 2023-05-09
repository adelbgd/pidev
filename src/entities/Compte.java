/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP 
 */
import java.util.Date ;
import javafx.scene.image.Image;

public class Compte {

    private int id;
    private int id_utilisateur;
    private String nom;
    private Date date_de_creation;
    private int nbr_Followers;
    private int nbr_Followings;
    private int nbr_Produits_Publies;
    private String profile_Image;
    private int banned  ; 
    private String bio ; 

    public Compte(int id, String nom, String profile_Image, String bio) {
        this.id = id;
        this.nom = nom;
        this.profile_Image = profile_Image;
        this.bio = bio;
    }

    public Compte(int id) {
        this.id = id;
    }

    

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate_de_creation() {
        return date_de_creation;
    }

    public Compte(String nom, String profile_Image, String bio) {
        this.nom = nom;
        this.profile_Image = profile_Image;
        this.bio = bio;
    }

    public void setDate_de_creation(Date date_de_creation) {
        this.date_de_creation = date_de_creation;
    }

    public int getNbr_Followers() {
        return nbr_Followers;
    }

    public void setNbr_Followers(int nbr_Followers) {
        this.nbr_Followers = nbr_Followers;
    }

    public int getNbr_Followings() {
        return nbr_Followings;
    }

    public void setNbr_Followings(int nbr_Followings) {
        this.nbr_Followings = nbr_Followings;
    }

    public int getNbr_Produits_Publies() {
        return nbr_Produits_Publies;
    }

    public void setNbr_Produits_Publies(int nbr_Produits_Publies) {
        this.nbr_Produits_Publies = nbr_Produits_Publies;
    }

    public String getProfile_Image() {
        return profile_Image;
    }

    public void setProfile_Image(String profile_Image) {
        this.profile_Image = profile_Image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    
    public Compte() {
    }

    public Compte(int id, int id_utilisateur, String nom, Date date_de_creation, int nbr_Followers, int nbr_Followings, int nbr_Produits_Publies, String profile_Image, int banned, String bio) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.date_de_creation = date_de_creation;
        this.nbr_Followers = nbr_Followers;
        this.nbr_Followings = nbr_Followings;
        this.nbr_Produits_Publies = nbr_Produits_Publies;
        this.profile_Image = profile_Image;
        this.banned = 0;
        this.bio = bio;
    }

    public Compte(int id_utilisateur, String nom, Date date_de_creation, int nbr_Followers, int nbr_Followings, int nbr_Produits_Publies, String profile_Image, int banned, String bio) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.date_de_creation = date_de_creation;
        this.nbr_Followers = nbr_Followers;
        this.nbr_Followings = nbr_Followings;
        this.nbr_Produits_Publies = nbr_Produits_Publies;
        this.profile_Image = profile_Image;
        this.banned = banned;
        this.bio = bio;
    }

    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Compte{" + "id=" + id + ", id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", date_de_creation=" + date_de_creation + ", nbr_Followers=" + nbr_Followers + ", nbr_Followings=" + nbr_Followings + ", nbr_Produits_Publies=" + nbr_Produits_Publies + ", profile_Image=" + profile_Image + ", banned=" + banned + ", bio=" + bio + '}';
    }

    

    

    
}
