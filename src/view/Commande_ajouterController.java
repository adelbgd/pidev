/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import models.Livraison;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Commande_ajouterController implements Initializable {

    @FXML
    private TextField produit_c;
    @FXML
    private TextField client_c;
    private DatePicker text_date_c;
    @FXML
    private Button retour1;
    @FXML
    private Button ajouter_c;
    private TextField id_liv_com;
    @FXML
    private TextField mail;
    
    

    
    
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour1(ActionEvent event) throws IOException {
        // Charger l'interface suivante à partir de son fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_acceuil.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }

    @FXML
    private void ajouter_c(ActionEvent event) {
        Commande c = new Commande();
        if (produit_c.getText().isEmpty() || client_c.getText().isEmpty()|| mail.getText().isEmpty()    ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Commande ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }else{
       
      
        LocalDate d1 = LocalDate.now();
        c.setDate_commande(java.sql.Date.valueOf(d1));    
        c.setNom_prenom(client_c.getText());
        c.setNum(Integer.parseInt(produit_c.getText()));
        c.setMail(mail.getText());
        
        
           CommandeService fs= new CommandeService();
           fs.addCommande(c);
           
 
           
    }
  }  
}
  
