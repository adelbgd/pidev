/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.lang.Long;
/**
 *
 * @author user
 */
public class client {
     //att
    private int id_client;
    private String nom;
    private String prenom;
    private String sexe ;
    private String adresse;
    private long num_tel;
    private String mail ;
    private String password ; 
   
   
    
    //const
    
    public client()
    {
    }

    public client(int id_client, String nom, String prenom, String sexe, String adresse, long num_tel, String mail, String password) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.mail = mail;
        this.password = password;
    }

    public client(String nom, String prenom, String sexe, String adresse, long num_tel, String mail, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.mail = mail;
        this.password = password;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(long num_tel) {
        this.num_tel = num_tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

 
    
    
    //display

    @Override
    public String toString() {
        return "client{" + "id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", adresse=" + adresse + ", num_tel=" + num_tel + ", mail=" + mail + ", password=" + password  + '}';
    }

 
    
}
