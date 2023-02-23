/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.admin;
import services.adminServices;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifclientController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField pwd;
    @FXML
    private TextField cin;
    @FXML
    private Button modifier;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
         if ( nom.getText().isEmpty() | prenom.getText().isEmpty() |pwd.getText().isEmpty() | email.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!email.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (nom.getText().equals(pwd.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
            adminServices ss= new adminServices();
            admin a = new admin(cin.getText(), nom.getText(), prenom.getText(), email.getText(), pwd.getText());
            ss.modifier(  cin.getText(),nom.getText(), prenom.getText());

            JOptionPane.showMessageDialog(null,"User modified");

           /* FXMLLoader loader2= new FXMLLoader(getClass().getResource("modifclient.fxml"));
            Parent root0= loader2.load();
            modifier.getScene().setRoot(root0);*/

           /* ProfilController pc= loader2.getController();
            pc.setPrenom(prenom.getText());
            pc.setEmail(email.getText());*/
            
        }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        admin u = new admin();
        adminServices ss= new adminServices();
        
        u.setCin(cin.getText());
        ss.supprimer(u.getCin());
       
       Alert alert = new Alert (AlertType.INFORMATION);
       alert.setTitle("admin supprimé ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("admin supprimé avec succés !!");
       alert.show();
    }
    }
    

