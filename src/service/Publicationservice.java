/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.util.List;
import util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Publication;

public class Publicationservice {

    Connection con;

    public Publicationservice() {
        con = MyConnection.getInstance().getCnx();
    }

    public void ajouterPublication(Publication p) {
        try {
            String requete2 = "INSERT INTO publication (id_user,titre_publication,contenu_publication,date_publication)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete2);
            pst.setInt(1, p.getId_user());
            pst.setString(2, p.getTitre_publication());
            pst.setString(3, p.getContenu_publication());
            pst.setDate(4,new Date (System.currentTimeMillis()));
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerPublication (int  id_publication) {
        try {
            String requete = "DELETE FROM publication WHERE id_publication =?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id_publication);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public void modifierTitrePublication(int id_publication, String titre_publication) {
        try {
            String requete = "UPDATE publication SET titre_discussion = ?  WHERE id_publication= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, titre_publication);
            pst.setInt(2, id_publication);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierContenuPublication(int id_publication, String contenu_publication) {
        try {
            String requete = "UPDATE publication SET contenu_publication = ?  WHERE id_publication= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, contenu_publication);
            pst.setInt(2, id_publication);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public void modifierDatePublication(int id_publication, Date date_publication) {
        try {
            String requete = "UPDATE publication SET date_discussion = ?  WHERE id_publication= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setDate(1, date_publication);
            pst.setInt(2, id_publication);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Publication> afficherPublication() {
        List<Publication> publications = new ArrayList<>();
        try {
            String requete = "SELECT * FROM publication";
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Publication dis = new Publication();
                dis.setId_pub(rs.getInt(1));
                dis.setId_user(rs.getInt(2));
                dis.setTitre_publication(rs.getString(3));
                dis.setContenu_publication(rs.getString(4));
                dis.setDate_publication(rs.getDate(5));
                publications.add(dis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return publications;
    }

    public List<Publication> rechercherPublication(int id_user) {
        List<Publication> publicationUtilisateur = new ArrayList<>();
        try {

            String requete = "SELECT * FROM publication WHERE id_user = ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Publication dis = new Publication();
                dis.setId_pub(rs.getInt(1));
                dis.setId_user(rs.getInt(2));
                dis.setTitre_publication(rs.getString(3));
                dis.setContenu_publication(rs.getString(4));
                dis.setDate_publication(rs.getDate(5));
                publicationUtilisateur.add(dis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return publicationUtilisateur;

    }

    public List<Publication> rechercherPublication(String nom, String prenom) {
        List<Publication> publicationUtilisateur = new ArrayList<>();
        try {

            String requete = "SELECT * FROM user WHERE nom = ? AND prenom=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id_user = rs.getInt(1);
               publicationUtilisateur.addAll(0, rechercherPublication(id_user));
              
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return publicationUtilisateur;

    }
}