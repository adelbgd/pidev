/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Walid
 */
public class MyConnection {
    
    static final String URL ="jdbc:mysql://localhost:3306/swappy";
    static final String USER ="root";
    static final String PASSWORD ="";
    
    private Connection cnx;
    
    
    static MyConnection instance;
    
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        
        return instance;
    }
    
}