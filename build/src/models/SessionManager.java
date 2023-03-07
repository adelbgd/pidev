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
public class SessionManager {
    private static SessionManager instance;
    private client currentClient;
    private admin currentAdmin; 
    
    // other instance variables
    
    private SessionManager() {
        // constructor
    }
    
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public void setCurrentClient(client c) {
        this.currentClient = c;
    }
    
    public client getCurrentClient() {
        return this.currentClient;
    }
      public void setCurrentAdmin(admin a) {
        this.currentAdmin = a;
    }
    
    public admin getCurrentAdmin() {
        return this.currentAdmin;
    }
    
    // other methods
}