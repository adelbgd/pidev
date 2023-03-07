/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.mycompany.pidev;
import interfaces.adminInterfaces;
import models.admin;
import models.client;
import util.Myconnexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class adminServices implements adminInterfaces {

    Connection cnx = Myconnexion.getInstance().getCnx();
    private String req;

    /**
     *
     * @param u
     */
    @Override
    public void ajouter(admin u) {
        try {
             
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            String req = "INSERT INTO admin (cin, nom,prenom,email,pwd) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getCin());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(String cin) {
        try {
            String req = "DELETE FROM admin WHERE cin = '" + cin + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("admin deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String cin, String nom, String prenom, String email, String pwd) {
        try {
            // Connect to the database
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");

            // Construct the SQL statement
            String sql = "UPDATE admin SET nom=?, prenom=?, email=?, pwd=? WHERE cin=?";

            // Prepare the statement
            PreparedStatement stmt = cnx.prepareStatement(sql);

            // Set the parameter values
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, pwd);
            stmt.setString(5, cin);
            // Execute the statement
            stmt.executeUpdate();

            // Close the resources
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String authentification(String Email, String Password) {
        String cin = "";

        try {
            String req = "SELECT * from admin WHERE `admin`.`email` ='" + Email + "' && `admin`.`pwd` = '" + Password + "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                cin = rs.getString("cin");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cin;

    }

    @Override
   public admin getOneById(String cin) {
        String query = "SELECT * FROM admin WHERE cin = " + cin + "";
        admin a  = new admin();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return a;
    }
    @Override
    public List<admin> getall() {
        List<admin> listeadmin = new ArrayList<>();
        String query = "SELECT * FROM admin ";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                admin a = new admin();
                a.setCin(rs.getString("cin"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("Prenom"));
                a.setEmail(rs.getString("Email"));
                a.setPwd(rs.getString("pwd"));

                listeadmin.add(a);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return listeadmin;

    }
}
