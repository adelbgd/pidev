/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Myconnexion;
import interfaces.ReclamationInterfaces;

/**
 *
 * @author ASUS
 */
public class ReclamationService implements ReclamationInterfaces {

    Connection cnx;

    public ReclamationService() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation r) throws SQLException {
        String req = "INSERT INTO reclamation (sujet , message , email ,etat, id_client) VALUES(?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, r.getSujet());
        ps.setString(2, r.getMessage());
        ps.setString(3, r.getEmail());
        ps.setInt(4, 0);
        ps.setInt(5, r.getC().getId_client());
        ps.executeUpdate();
        System.out.println("Reclamation ajoutée");

    }

    @Override
    public void modifier(Reclamation r) throws SQLException {
        String req = "UPDATE reclamation SET id_client = ?,email = ?,sujet = ?,message = ? where id_reclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, r.getC().getId_client());
        ps.setString(2, r.getEmail());
        ps.setString(3, r.getSujet());
        ps.setString(4, r.getMessage());
        ps.setInt(5, r.getId_reclamation());
        ps.executeUpdate();
        System.out.println("Reclamation mise a jour ");
    }

    @Override
    public void supprimer(Reclamation r) throws SQLException {
        String req = "DELETE FROM reclamation where id_reclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, r.getId_reclamation());
        ps.executeUpdate();
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        clientServices sc = new clientServices();
        String s = "select * from reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Reclamation p = new Reclamation();
            p.setEmail(rs.getString("email"));
            p.setSujet(rs.getString("sujet"));
            p.setMessage(rs.getString("message"));
            p.setId_reclamation(rs.getInt("id_reclamation"));
            p.setEtat(rs.getInt("etat"));
            p.setC(sc.getOneById(rs.getInt("id_client")));

            reclamation.add(p);

        }
        return reclamation;
    }

    @Override
    public Reclamation recupererSingel(int id_reclamation) throws SQLException {
        Reclamation p = new Reclamation();
        clientServices sc = new clientServices();
        String s = "select * from reclamation where id_reclamation = " + id_reclamation;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            p.setEmail(rs.getString("email"));
            p.setSujet(rs.getString("sujet"));
            p.setMessage(rs.getString("message"));
            p.setId_reclamation(rs.getInt("id_reclamation"));
            p.setEtat(rs.getInt("etat"));
            p.setC(sc.getOneById(rs.getInt("id_client")));

        }
        return p;
    }

      public List<Reclamation> recupererParClient(int id_client) throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        clientServices sc = new clientServices();
        String s = "select * from reclamation where id_client = " + id_client;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Reclamation p = new Reclamation();
            p.setEmail(rs.getString("email"));
            p.setSujet(rs.getString("sujet"));
            p.setMessage(rs.getString("message"));
            p.setId_reclamation(rs.getInt("id_reclamation"));
            p.setEtat(rs.getInt("etat"));
            p.setC(sc.getOneById(rs.getInt("id_client")));

            reclamation.add(p);

        }
        return reclamation;
    }
}
