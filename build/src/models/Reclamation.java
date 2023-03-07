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
public class Reclamation {
    private int id_reclamation;
    private String email,sujet,message;
    private client c ;
    private int etat ; 

    public Reclamation() {
    }

    public Reclamation(int id_reclamation, String email, String sujet, String message, client c, int etat) {
        this.id_reclamation = id_reclamation;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.c = c;
        this.etat = etat;
    }

    public Reclamation(String email, String sujet, String message, client c, int etat) {
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.c = c;
        this.etat = etat;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public client getC() {
        return c;
    }

    public void setC(client c) {
        this.c = c;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "email=" + email + ", sujet=" + sujet + ", message=" + message + ", c=" + c + ", etat=" + etat + '}';
    }
    
 
    
    
}
