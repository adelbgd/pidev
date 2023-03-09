/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

public class Publication {
    private int id_pub;
    private int id_user;
    private String titre_publication;
    private String contenu_publication;
    private Date date_publication;
    private int nb_signals ;

    public Publication() {
    }

    public Publication(int id_user, String titre_publication, String contenu_publication, Date date_publication) {
        this.id_user = id_user;
        this.titre_publication = titre_publication;
        this.contenu_publication = contenu_publication;
        this.date_publication = date_publication;
    }

    public Publication(int id_pub, int id_user, String titre_publication, String contenu_publication, Date date_publication) {
        this.id_pub = id_pub;
        this.id_user = id_user;
        this.titre_publication = titre_publication;
        this.contenu_publication = contenu_publication;
        this.date_publication = date_publication;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitre_publication() {
        return titre_publication;
    }

    public void setTitre_publication(String titre_publication) {
        this.titre_publication = titre_publication;
    }

    public String getContenu_publication() {
        return contenu_publication;
    }

    public void setContenu_publication(String contenu_publication) {
        this.contenu_publication = contenu_publication;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public Publication(int id_pub, int id_user, String titre_publication, String contenu_publication, Date date_publication, int nb_signals) {
        this.id_pub = id_pub;
        this.id_user = id_user;
        this.titre_publication = titre_publication;
        this.contenu_publication = contenu_publication;
        this.date_publication = date_publication;
        this.nb_signals = nb_signals;
    }

    public int getNb_signals() {
        return nb_signals;
    }

    public void setNb_signals(int nb_signals) {
        this.nb_signals = nb_signals;
    }

}
