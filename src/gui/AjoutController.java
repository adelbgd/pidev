/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import models.client;
import services.clientServices;
import javafx.scene.image.ImageView;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField telephone;
    @FXML
    private TextField adresse;
    @FXML
    private TextField sexe;
    @FXML
    private TextField mail;

clientServices ps = new clientServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter (ActionEvent event) {
      
        client c = new client();
        c.setmail(mail.getText());
        c.setName(nom.getText());
        c.setprenom(prenom.getText());
        c.setsexe(sexe.getText());
        c.setadresse(adresse.getText());
        c.setNumber(Integer.parseInt(telephone.getText()));
        ps.addclient(c);
        System.out.println("personne ajouter avec succes");
    }
      /* @FXML
    private void Modifier (ActionEvent event){
        
        
        
        
        
        
        
        
        
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    

 
