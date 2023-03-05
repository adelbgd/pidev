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
public class Livraison {
    private int id_livraison;
    private Date date_livraison;
    private String lieu_livraison;
    private String ville;
    private String status_livraison;
    private String comp;
    private int frais_livraison;
    

    public Livraison() {
    }

    public Livraison(int id_livraison, Date date_livraison, String lieu_livraison, String ville, String status_livraison, String comp, int frais_livraison) {
        this.id_livraison = id_livraison;
        this.date_livraison = date_livraison;
        this.lieu_livraison = lieu_livraison;
        this.ville = ville;
        this.status_livraison = status_livraison;
        this.comp = comp;
        this.frais_livraison = frais_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getLieu_livraison() {
        return lieu_livraison;
    }

    public void setLieu_livraison(String lieu_livraison) {
        this.lieu_livraison = lieu_livraison;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getStatus_livraison() {
        return status_livraison;
    }

    public void setStatus_livraison(String status_livraison) {
        this.status_livraison = status_livraison;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public int getFrais_livraison() {
        return frais_livraison;
    }

    public void setFrais_livraison(int frais_livraison) {
        this.frais_livraison = frais_livraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", date_livraison=" + date_livraison + ", lieu_livraison=" + lieu_livraison + ", ville=" + ville + ", status_livraison=" + status_livraison + ", comp=" + comp + ", frais_livraison=" + frais_livraison + '}';
    }

    
   
    
  
}
