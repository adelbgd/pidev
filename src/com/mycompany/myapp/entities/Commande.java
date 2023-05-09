/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author adelb
 */
public class Commande {
    private int id,num,code_postal;
    private String prenom,nom,mail,region,ville,rue;

    public Commande(int id, int num, int code_postal, String prenom, String nom, String mail, String region, String ville, String rue) {
        this.id = id;
        this.num = num;
        this.code_postal = code_postal;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.region = region;
        this.ville = ville;
        this.rue = rue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", num=" + num + ", code_postal=" + code_postal + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + ", region=" + region + ", ville=" + ville + ", rue=" + rue + '}';
    }
    
}
