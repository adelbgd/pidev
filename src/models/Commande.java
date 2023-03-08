/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.Date;

/**
 *
 * @author adelb
 */
public class Commande {

 
    private int id_commande;
    private int id_client;
    private Date date_commande;
    private String nom_prenom;
    private int num;
    private String mail;

    public Commande() {
        
    }

    public Commande(int id_commande, int id_client, Date date_commande, String nom_prenom, int num, String mail) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.date_commande = date_commande;
        this.nom_prenom = nom_prenom;
        this.num = num;
        this.mail = mail;
    }

    public Commande(int id_client, Date date_commande, String nom_prenom, int num, String mail) {
        this.id_client = id_client;
        this.date_commande = date_commande;
        this.nom_prenom = nom_prenom;
        this.num = num;
        this.mail = mail;
    }

    

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", id_client=" + id_client + ", date_commande=" + date_commande + ", nom_prenom=" + nom_prenom + ", num=" + num + ", mail=" + mail + '}';
    }

    
   
    }

    