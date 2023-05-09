/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author azizl
 */
public class Publication {
    
     private int id;
    private int id_user;
    private String titre_pub;
    private String contenu_pub;
    private Date date_pub;
    private int nb_signals ;
    private String image;

    public Publication(String titre_pub, String contenu_pub, String image) {
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.image = image;
    }

    
    
   
    public String getImage() {
        return image;
    }

    public Publication(int id_user, String titre_pub, String contenu_pub, Date date_pub, int nb_signals, String image) {
        this.id_user = id_user;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.date_pub = date_pub;
        this.nb_signals = nb_signals;
        this.image = image;
    }

    public Publication(int id, int id_user, String titre_pub, String contenu_pub, Date date_pub, int nb_signals, String image) {
        this.id = id;
        this.id_user = id_user;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.date_pub = date_pub;
        this.nb_signals = nb_signals;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Publication(String titre_pub, String contenu_pub, Date date_pub, int nb_signals) {
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.date_pub = date_pub;
        this.nb_signals = nb_signals;
    }

    
    
    public Publication(int id_user, String titre_pub, String contenu_pub, Date date_pub, int nb_signals) {
        this.id_user = id_user;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.date_pub = date_pub;
        this.nb_signals = nb_signals;
    }

    public Publication() {
    }

    public Publication(int id, int id_user, String titre_pub, String contenu_pub, Date date_pub, int nb_signals) {
        this.id = id;
        this.id_user = id_user;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.date_pub = date_pub;
        this.nb_signals = nb_signals;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", id_user=" + id_user + ", titre_pub=" + titre_pub + ", contenu_pub=" + contenu_pub + ", date_pub=" + date_pub + ", nb_signals=" + nb_signals + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitre_pub() {
        return titre_pub;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }

    public String getContenu_pub() {
        return contenu_pub;
    }

    public void setContenu_pub(String contenu_pub) {
        this.contenu_pub = contenu_pub;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public int getNb_signals() {
        return nb_signals;
    }

    public void setNb_signals(int nb_signals) {
        this.nb_signals = nb_signals;
    }

    public Publication(int id, String titre_pub, String contenu_pub, String image) {
        this.id = id;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.image = image;
    }

    public Publication(int id) {
        this.id = id;
    }
    

}
