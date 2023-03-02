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
    private Date date_commande;
    private String client;
    private String produit;
    private Livraison id_livraison;

    public Commande() {
        
    }

    public Commande(int id_commande, Date date_commande, String client, String produit,Livraison id_livraison) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.client = client;
        this.produit = produit;
        this.id_livraison=id_livraison;
    }

    public Livraison getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(Livraison id_livraison) {
        this.id_livraison = id_livraison;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", date_commande=" + date_commande + ", client=" + client + ", produit=" + produit + ", id_livraison=" + id_livraison + '}';
    }

    

   
}

