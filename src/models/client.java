/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



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
   
   
    
    //const
    
    public client()
    {
    }

    public client(String nom, String prenom, String sexe, String adresse, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.mail = mail;
      
        
        
    }
    
    //getters and setters
    public int getId() {
        return id_client;
    }

    public void setId(int id) {
        this.id_client = id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }
    public String getprenom() {
        return prenom;
    }

    public void setprenom(String prenom) 
    {
        this.prenom = prenom;
    }
 public String getsexe() {
        return sexe;
    }

    public void setsexe(String sexe) {
        this.sexe = sexe;
    }
     public String getadresse() {
        return adresse;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }


    public long getNumber() {
        return num_tel;
    }

    public void setNumber(long number) {
        this.num_tel = number;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }
 
    
    
    //display

    @Override
    public String toString() {
        return "client{" + "id=" + id_client + ", name=" + nom+ ",  prenom=" + prenom + ",sexe=" + sexe + ",sexe=" + adresse + ",number=" + num_tel + '}';
    }
    
}
