/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author adelb
 */
public class Commande {
    private int id;
    private String prenom;
    private String nom;
    private int num ;
    private String mail;
    private String region;
    private String ville;
    private String rue;
    private int code_postal;

    public Commande(int id, String prenom, String nom, int num, String mail, String region, String ville, String rue, int code_postal) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.num = num;
        this.mail = mail;
        this.region = region;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
    }

    public Commande(String prenom, String nom, int num, String mail, String region, String ville, String rue, int code_postal) {
        this.prenom = prenom;
        this.nom = nom;
        this.num = num;
        this.mail = mail;
        this.region = region;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public Commande(int id) {
        this.id = id;
    }


    
  
    
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", num=" + num + ", mail=" + mail + ", region=" + region + ", ville=" + ville + ", rue=" + rue + ", code_postal=" + code_postal + '}';
    }




    
}

