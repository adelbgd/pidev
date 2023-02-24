/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.clientInterfaces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.client;

import util.Myconnexion;

public class clientServices implements clientInterfaces {

    //var
    Connection cnx = Myconnexion.getInstance().getCnx();

    @Override

    public void addclient(client c) {
        try {
            String req = "INSERT INTO client (id_client, nom,prenom,num_tel,adresse,sexe,mail,password) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId_client());
            ps.setString(2, c.getNom());
            ps.setString(3, c.getPrenom());
            ps.setLong(4, c.getNum_tel());
            ps.setString(5, c.getAdresse());
            ps.setString(6, c.getSexe());
            ps.setString(7, c.getMail());
            ps.setString(8, c.getPassword());
            ps.executeUpdate();
            System.out.println("personne ajouter avec succes");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierclient(int id, String nom, String prenom, long num_tel, String adresse, String sexe, String mail,String password) {
        try {
            // Connect to the database
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");

            // Construct the SQL statement
            String sql = "UPDATE client SET nom=?, prenom=?, num_tel=?, adresse=?, sexe=?,mail=?, password = ?  WHERE id_client=?";

            // Prepare the statement
            PreparedStatement stmt = cnx.prepareStatement(sql);

            // Set the parameter values
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setLong(3, num_tel);
            stmt.setString(4, adresse);
            stmt.setString(5, sexe);
            stmt.setString(6, mail);
            stmt.setString(7, password);

            stmt.setInt(8, id);
            // Execute the statement
            stmt.executeUpdate();

            // Close the resources
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    

    @Override
    public void supprimerclient(int id) {
        try {
            String req = "DELETE FROM client WHERE id_client = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("client deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int authentification ( String Email , String Password )
    {
          int id = -1;

        try {
            String req = "SELECT * from client WHERE mail ='" + Email + "' && password = '" + Password + "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                id = rs.getInt("id_client");
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id client " + id);
        return id;

    }
    

 @Override
      public List<client> getall() 
    {
        List<client> client = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM client";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
            {                
                client c = new client();
                c.setId_client(rs.getInt("id_client"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setNum_tel(rs.getLong("num_tel"));
                c.setAdresse(rs.getString("adresse"));
                c.setSexe(rs.getString("sexe"));
                c.setMail(rs.getString("mail"));
               c.setPassword(rs.getString("password"));
                
                client.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return client;
    }

    @Override
    public client getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}